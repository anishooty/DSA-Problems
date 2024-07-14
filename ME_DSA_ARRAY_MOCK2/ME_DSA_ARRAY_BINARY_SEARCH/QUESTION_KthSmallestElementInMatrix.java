import java.util.*;

class KthSmallestElementInMatrix {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        List<ArrayList<Integer>> Matrix = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(sc.nextInt());
            }
            Matrix.add(row);
        }
        int ans = kthSmallestElementInMatrix(Matrix, k);
        System.out.print(ans);
    }

    static int kthSmallestElementInMatrix(List<ArrayList<Integer>> matrix, int k) {
        int n = matrix.size();
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Add the first element of each row to the min-heap
        for (int i = 0; i < n; i++) {
            minHeap.add(new int[]{matrix.get(i).get(0), i, 0});
        }

        // Extract the minimum element and add the next element from the same row
        for (int i = 0; i < k - 1; i++) {
            int[] min = minHeap.poll();
            int value = min[0];
            int row = min[1];
            int col = min[2];

            if (col < n - 1) {
                minHeap.add(new int[]{matrix.get(row).get(col + 1), row, col + 1});
            }
        }

        return minHeap.poll()[0];
    }
}
