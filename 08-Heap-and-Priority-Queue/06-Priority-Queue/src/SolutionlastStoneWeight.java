import java.util.Comparator;
import java.util.PriorityQueue;

class SolutionlastStoneWeight {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });
        for (int i = 0; i < stones.length; i++) {
            pq.add(stones[i]);
        }
        while (pq.size() > 1) {
            Integer y = pq.poll();
            Integer x = pq.poll();
            if (!x.equals(y)) {
                pq.add(y - x);
            }
        }
        if (pq.isEmpty()) {
            return 0;
        }
        return pq.poll();

    }


    public static void main(String[] args) {
        SolutionlastStoneWeight solutionlastStoneWeight = new SolutionlastStoneWeight();
        solutionlastStoneWeight.lastStoneWeight(new int[]{2, 2});
    }

}
