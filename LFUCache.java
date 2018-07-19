package LeetCode;

import java.util.HashMap;

public class LFUCache {
    class Node{
        private int val;
        private int freq;
        private Node next;
        private Node prev;
        private int key;

        public Node(int key,int val)
        {
            this.val = val;
            this.key = key;
            this.freq = 1;
            next = null;
            prev = null;
        }


        public void setFreq(int count) {
            this.freq = count;
        }

        public void setPrevious(Node previous) {
            this.prev = previous;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public int getKey() {
            return this.key;
        }

        public int getValue() {
            return this.val;
        }

        public Node getPrevious() {
            return this.prev;
        }

        public Node getNext() {
            return this.next;
        }

        public int getCount() {
            return this.freq;
        }

        public void increment() {
            this.freq++;
        }

        public void setValue(int value) {
            this.val = value;
        }

    };

    int gC = 0;
    int pC = 0;
    int size;
    int capacity;

    HashMap<Integer, Node> NodeChecker;
    HashMap<Integer, Node> FreqCounter;

    public static Node head;
    Node tail;

    public LFUCache(int capacity) {

        head = null;
        tail = null;
        size = 0;
        this.capacity = capacity;

        NodeChecker = new HashMap<Integer, Node>();
        FreqCounter = new HashMap<Integer, Node>();
    }

    public void nodeLinksSetup(Node node)
    {
        if(node.next != null)
        {
            node.next.prev = node.prev;
        }
        if(node.prev != null)
        {
            node.prev.next = node.next;
        }
        node.next = null;
        node.prev = null;

    }

    public Node moveHead(Node head)
    {
        if(head == null)
            return head;

        Node temp = head;
        head = head.next;

        if(head != null)
            head.prev = null;

        temp.prev = null;
        temp.next = null;

        return head;
    }

    public void makeNodeNeighborOfFreqNode(Node node,Node newFreqRecent)
    {
        node.next = newFreqRecent.next;
        node.prev = newFreqRecent;
        newFreqRecent.next = node;
        if(node.next != null)
        {
            node.next.prev = node;
        }
        FreqCounter.put(node.freq + 1,node);
    }

    public void updateNodeNeighbors(Node node){
        if(node == null)
            return;

        if(node.prev != null)
        {
            node.prev.next = node.next;
        }
        if(node.next != null)
        {
            node.next.prev = node.prev;
        }
    }

    public int get(int key) {
        gC++;
        if(capacity <= 0 || !NodeChecker.containsKey(key))
            return -1;


            Node node = NodeChecker.get(key);
            int newFreq = node.freq + 1;


            Node currFreqRecent = FreqCounter.get(node.freq);
            Node newFreqRecent =  FreqCounter.get(node.freq+1);

            //currFreqRecent can not be null. It can be either node or something different
            if(currFreqRecent == node)
            {
                if(node.prev != null && node.prev.freq == node.freq)
                    FreqCounter.put(node.freq, node.prev);
                else
                    FreqCounter.remove(node.freq);

                if(head == node)
                {
                    //it means we need to move node when its frequency count changes and append it next to newFreqRecent
                    if(newFreqRecent != null) {
                        head =    moveHead(head);

                        makeNodeNeighborOfFreqNode(node, newFreqRecent);
                    }
                    //there is no elemnt of new frequency, so head will remain same, node will change frequency but not change position
                    else{
                        FreqCounter.put(node.freq + 1,node);
                    }

                }
                // node is not @ head position but its recent most element of given frequency
                else{
                    //if newFreqRecent is null, node will change only frequency but not position
                    if(newFreqRecent == null)
                    {
                        FreqCounter.put(newFreq,node);
                    }
                    else{

                        updateNodeNeighbors(node);
                        makeNodeNeighborOfFreqNode(node,newFreqRecent);
                    }
                }
            }
            //node is in between head(included) and curFreqRecent. need to adjust pointer and move node
            else{

                //create recent elemnt with nodes freqency
                    if(newFreqRecent != null)
                    {

                        if(node == head)
                        {
                            head = moveHead(head);
                        }
                        else{
                            updateNodeNeighbors(node);
                        }
                        makeNodeNeighborOfFreqNode(node, newFreqRecent);

                    }
                    //newFreqRecent is null, so add elemnt after currFreqRecent
                    else{
                        if(node == head)
                        {
                            head = moveHead(head);
                        }
                        else{
                            updateNodeNeighbors(node);
                        }
                        makeNodeNeighborOfFreqNode(node, currFreqRecent);
                    }
            }
            node.freq = node.freq + 1;
            return node.val;
    }

    public void put(int key, int value) {

        pC++;
        if(capacity <=0)
            return;

       // if(NodeChecker.containsKey(key) && (NodeChecker.get(key).val != value))
        if(NodeChecker.containsKey(key))
        {
            Node existing = NodeChecker.get(key);
            //if(existing.val != value)

            existing.val = value;
            this.get(key);

            return;
        }

        Node newNode = new Node(key, value);
        //NodeChecker.put(key,newNode);
        if(NodeChecker.size() == 0 || (head == null  && (size+1) <= capacity))
        {
            head = newNode;
            FreqCounter.put(1, newNode);
            NodeChecker.put(key,newNode);
            size++;
        }
        else
        {
            //if there is space to add one more element
            if(size+1 <= capacity)
            {
                //Case: At present cache contains only one elemnt.
                if(NodeChecker.size()==1)
                {
                    //Subcase 1: Frequency of use of elemnt present is 1, head need not be updated
                    if(head.freq == 1)
                    {
                        head.next = newNode;
                        newNode.prev = head;
                        FreqCounter.put(1,newNode);
                    }
                    //Subcase 1: Frequency of use of elemnt present is greater than 1, head needs to be updated
                    else{
                        FreqCounter.put(1,newNode);
                        head.prev = newNode;
                        newNode.next = head;
                        head = newNode;
                    }
                    NodeChecker.put(key,newNode);
                }
                //There are multiple elements in cache and we wish to insert new elemnt
                else
                {
                    Node temp = FreqCounter.get(1);

                    //no element of frequency 1, make new element as head
                    if(temp == null)
                    {
                        FreqCounter.put(1, newNode);
                        head.prev = newNode;
                        newNode.next = head;
                        head = newNode;
                    }
                    //add newNode as adjacent to this freq 1 node. update newNode as current last node of frequency 1
                    else{
                        newNode.next = temp.next;
                        newNode.prev = temp;
                        temp.next = newNode;
                        if(newNode.next != null)
                        {
                            newNode.next.prev = newNode;
                        }
                        FreqCounter.put(1, newNode);
                    }
                    NodeChecker.put(key,newNode);

                }
                size++;

            }
            //cache is full
            //delete least frequently used cache entry, which is at head, add new entry at next to frequency entry;
            else{
                //cache is of size 1 and cache alreay has 1 element, head will be assigned to null before inserting elemnt
                if(NodeChecker.size()==1)
                {
                    // if(head != null) {
                    {
                        NodeChecker.remove(head.key);
                        FreqCounter.remove(head.freq);
                        //head = null;
                        size--;

                        this.put(key, newNode.val);
                    }
                }
                //cache has more than 1 element and we need to remove element at head
                else
                {
                    //if(head != null) {
                    {
                        NodeChecker.remove(head.key);
                        if (FreqCounter.get(head.freq) == head) {
                            FreqCounter.remove(head.freq);
                        }
                        head = moveHead(head);

                        size--;
                        this.put(key, newNode.val);
                    }
                }
            }
        }

    }

    public static void printList(Node head)
    {
        System.out.println();
        while(head != null)
        {
            System.out.print("\t" + head.key);
            head = head.next;

        }
    }
    public static  void main(String[] args)throws Exception
    {
        LFUCache cache = new LFUCache(10);
        /*cache.put(3,1);
        cache.put(2,1);
        cache.put(2,2);
        cache.put(4,4);

        System.out.println(cache.get(2));
        */


        cache.put(10,13);
 //       printList(head);
        cache.put(3,17);
   //     printList(head);
        cache.put(6,11);
     //   printList(head);
        cache.put(10,5);
       // printList(head);
        cache.put(9,10);
       // printList(head);
        System.out.println(cache.get(13));
        //printList(head);
        cache.put(2,19);
        //printList(head);
        System.out.println(cache.get(2));
        //printList(head);
        System.out.println(cache.get(3));
        //printList(head);
        cache.put(5,25);
        //printList(head);
        System.out.println(cache.get(8));
        //printList(head);
        cache.put(9,22);
       // printList(head);
        cache.put(5,5);
        //printList(head);
        cache.put(1,30);
        //printList(head);
        System.out.println(cache.get(11));
        //printList(head);
        cache.put(9,12);
        //printList(head);
        System.out.println(cache.get(7));
        //printList(head);
        System.out.println(cache.get(5));
        //printList(head);
        System.out.println(cache.get(8));
        //printList(head);
        System.out.println(cache.get(9));
        //printList(head);
        cache.put(4,30);
        //printList(head);
        cache.put(9,3);
        //printList(head);
        System.out.println(cache.get(9));
        //printList(head);
        System.out.println(cache.get(10));
       // printList(head);
        System.out.println(cache.get(10));
        //printList(head);
        cache.put(6,14);
        //printList(head);
        cache.put(3,1);
       // printList(head);
        System.out.println(cache.get(3));
        //printList(head);
        cache.put(10,11);
        //printList(head);
        System.out.println(cache.get(8));
        //printList(head);
        cache.put(2,14);
      //  printList(head);
        System.out.println(cache.get(1));
        //printList(head);
        System.out.println(cache.get(5));
        //printList(head);
        System.out.println(cache.get(4));
        //printList(head);
        cache.put(11,4);
        //printList(head);
        cache.put(12,24);
        //printList(head);
        cache.put(5,18);
        //printList(head);
        System.out.println(cache.get(13));
        //printList(head);
        cache.put(7,23);
        //printList(head);
        System.out.println(cache.get(8));
        //printList(head);
        System.out.println(cache.get(12));
        //printList(head);
        cache.put(3,27);
        //printList(head);
        cache.put(2,12);
        //printList(head);
        System.out.println(cache.get(5));
       // printList(head);
        cache.put(2,9);
        //printList(head);
        cache.put(13,4);
        //printList(head);
        cache.put(8,18);
        //printList(head);
        cache.put(1,7);
        //printList(head);
        System.out.println(cache.get(6));
       // printList(head);
        cache.put(9,29);
       // printList(head);
        cache.put(8,21);
        //printList(head);
        System.out.println(cache.get(5));
        //printList(head);
        cache.put(6,30);
       // printList(head);
        cache.put(1,12);
        //printList(head);
        System.out.println(cache.get(10));
        //printList(head);
        cache.put(4,15);
        //printList(head);
        cache.put(7,22);
        //printList(head);

        cache.put(11,26);
        //printList(head);
        cache.put(8,17);
        printList(head);
        cache.put(9,29);
        printList(head);
        System.out.println(cache.get(5));
        printList(head);
        cache.put(3,4);
        printList(head);
        cache.put(11,30);
        printList(head);
        System.out.println(cache.get(12));
        printList(head);
        cache.put(4,29);
        printList(head);
        System.out.println(cache.get(3));
        printList(head);
        System.out.println(cache.get(9));
        printList(head);
        System.out.println(cache.get(6));
        printList(head);







        /*System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        cache.put(3,3);
        cache.put(4,4);
        System.out.println(cache.get(3));
        System.out.println(cache.get(2));
        System.out.println(cache.get(1));
        System.out.println(cache.get(4));
*/

        //Base testcase
        /*
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        System.out.println(cache.get(3));      // returns 3.
        cache.put(4, 4);    // evicts key 1.
        System.out.println(cache.get(1));// returns -1 (not found)
        System.out.println(cache.get(3));// returns 3
        System.out.println(cache.get(4));// returns 4
        */
    }
}

