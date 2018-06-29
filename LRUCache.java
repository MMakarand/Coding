import java.util.HashMap;

class LRUCache {

    class DLinkedNode{
        int key;
        int val;
        DLinkedNode prev;
        DLinkedNode next;

        public DLinkedNode(int key,int val)
        {
            this.val = val;
            this.key = key;
            prev = null;
            next = null;
        }
    }

    public void insert(DLinkedNode node)
    {
        if(node != null)
        {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }
    }

    public void remove(DLinkedNode node)
    {
        if(node != null)
        {
            DLinkedNode before = node.prev;
            DLinkedNode after = node.next;

            before.next = after;
            after.prev = before;
        }
    }

    public void moveToFront(DLinkedNode node)
    {
        remove(node);
        insert(node);
    }

    public DLinkedNode removeLast()
    {
        DLinkedNode last = tail.prev;
        if(last != head)
        {
            last.prev.next = tail;
            tail.prev = last.prev;
            last.prev = null;
            last.next = null;
        }
        return last;
    }



    HashMap<Integer, DLinkedNode> hMap;// = new HashMap<Integer, Integer>();
    //ArrayList<Integer> accessList;// = new ArrayList<Integer>();
    int c = 0;
    int count = 0;

    DLinkedNode head;// = new DoublyLinkedList(0);
    DLinkedNode tail;// = new DoublyLinkedList(0);

    public LRUCache(int capacity) {
        hMap = new HashMap<Integer, DLinkedNode>();
        // accessList = new ArrayList<Integer>();
        head = new DLinkedNode(0,0);
        tail = new DLinkedNode(0,0);
        head.next = tail;
        tail.prev = head;

        c = capacity;
    }

    public int get(int key) {
        if(hMap.containsKey(key))
        {
            // removeElement(key);
            //insertAtFirst(key,hMap.get(key));
            //return hMap.get(key);
            DLinkedNode node = hMap.get(key);
            moveToFront(node);
            return node.val;

        }
        else{
            return -1;
        }
    }

    public void put(int key, int val)
    {
        if(hMap.containsKey(key))
        {
            //removeElement(key);
            //insertAtFirst(key,value);
            DLinkedNode node = hMap.get(key);
            node.val = val;
            moveToFront(node);
        }
        else{
            DLinkedNode node = new DLinkedNode(key,val);
            hMap.put(key, node);
            if(count < c)
            {
                count++;
                insert(node);
            }
            else{
                DLinkedNode node1 = removeLast();
                hMap.remove(node1.key);
                insert(node);
            }
        }
    }

    public static void main(String[] args)
    {
        LRUCache obj = new LRUCache(2);
        obj.put(1,1);
        obj.put(2,2);
        System.out.println(obj.get(1));
        obj.put(3,3);
        System.out.println(obj.get(2));
        obj.put(4,4);
        System.out.println(obj.get(1));
        System.out.println(obj.get(3));
        System.out.println(obj.get(4));
    }
}

