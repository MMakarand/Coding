import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;

public class MinimumCostToHire {
    static class Worker implements Comparable<Worker> {

        public int quality, wages, pos;
        public double wageToQuality;

        public Worker(int quality, int wages, int pos) {
            this.quality = quality;
            this.wages = wages;
            this.pos = pos;
            this.wageToQuality = (double) wages / quality;
        }

        public int compareTo(Worker worker) {
            if(this.wageToQuality == worker.wageToQuality)
                return 0;
            else if(this.wageToQuality > worker.wageToQuality)
                return 1;
            else
                return -1;


        }
    }

    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {


        Worker workers[] = new Worker[wage.length];

        for (int i = 0; i < wage.length; i++) {
            workers[i] = new Worker(quality[i], wage[i], i);
        }
        Arrays.sort(workers);

       for(int i=0; i < workers.length; i++)
        {
            System.out.print(workers[i].quality + "\t");
        }

        double minCost = Double.MAX_VALUE;
        int minIndex = -1;

        //problem with this following for loop is it assumes in sorted workers array, elements are in continuous group of size k
        // This is not case. when we want to minimize cost, we need to lower ratio and elements group whose quantity sum is less
        /*for(int i=0; i <= wage.length - K; i++)
        {
            double cost = 0;
            double ratio = workers[i+K-1].wageToQuality;
            for(int j=i; j < i+K; j++)
            {
                cost += (ratio * workers[j].quality);
            }
            if(cost < minCost)
            {
                minCost = cost;
                minIndex = i;
            }
        }*/
        PriorityQueue<Double> pq = new PriorityQueue<>();
        int quantitySum = 0;

        for(int i=0; i < wage.length; i++)
        {
            pq.add((double)-workers[i].quality);
            quantitySum += workers[i].quality;

            if(pq.size() > K)
            {
                quantitySum += pq.poll();
            }
            if(pq.size()== K)
            {
                minCost = Double.min(minCost,quantitySum* (workers[i].wageToQuality ));
            }
        }
        return minCost;
    }

    public static void main(String[] args)throws IOException
    {
        MinimumCostToHire obj = new MinimumCostToHire();
        int quality[] = {10,20,5};//{25,68,35,62,52,57,35,83,40,51};//{3,1,10,10,1};//
        int wages[] = {70,50,30};//{147,97,251,129,438,443,120,366,362,343};//{4,8,2,2,7};//
        int K = 2;
        System.out.println(obj.mincostToHireWorkers(quality,wages,K));
    }
}
