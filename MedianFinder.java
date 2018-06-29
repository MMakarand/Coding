import java.util.Collection.*;
import java.util.*;
class MedianFinder {

    double median;
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;
    int minSize;
    int maxSize;

    /** initialize your data structure here. */
    public MedianFinder() {
         median= 0.0;
         minHeap = new PriorityQueue<>();
         maxHeap = new PriorityQueue<>(Collections.reverseOrder());
         minSize = 0;
         maxSize = 0;
    }

    public void addNum(int num) {
        if(minHeap == null && maxHeap == null)
        {
            maxHeap.add(num);
            median = num;
            maxSize = 1;
            return;
        }
        if((double)num >  median)
        {
            minHeap.add(num);
            minSize++;
            if(minSize - maxSize > 1)
            {
                maxHeap.add(minHeap.poll());
                maxSize++; minSize--;
            }
        }
        else{
            maxHeap.add(num);
            maxSize++;
            if(maxSize - minSize > 1)
            {
                minHeap.add(maxHeap.poll());
                minSize++; maxSize--;
            }
        }

        updateMedian();
    }

    private void updateMedian() {
        if(maxSize == minSize)
        {
            median = (maxHeap.peek() + minHeap.peek())/2.0;
        }
        else if(maxSize > minSize)
            median = (double)maxHeap.peek();
        else
            median = (double)minHeap.peek();
    }

    public double findMedian() {
        return median;
    }

    public static void main(String args[])
    {
        MedianFinder obj = new MedianFinder();
        obj.addNum(2);
        System.out.println(obj.findMedian());
        obj.addNum(3);
        System.out.println(obj.findMedian());
        obj.addNum(4);
        System.out.println(obj.findMedian());
    }
}