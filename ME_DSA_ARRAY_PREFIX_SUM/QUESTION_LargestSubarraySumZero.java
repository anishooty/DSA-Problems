import java.util.*;

class LargestSubarraySumZero {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int arr[] = new int[n];

        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        ArrayList<Integer> res = largestSubarraySumZero(n, arr);

        if (res.size() == 0) {
            System.out.println("-1");
        } else {
            for (int j : res)
                System.out.print(j + " ");
        }
    }

    static ArrayList<Integer> largestSubarraySumZero(int n, int arr[]) {
        ArrayList<Integer> result = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int maxLength = 0;
        int endIndex = -1;

        map.put(0, -1);

        for (int i = 0; i < n; i++) {
            sum += arr[i];

            if (map.containsKey(sum)) {
                int currentLength = i - map.get(sum);
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    endIndex = i;
                }
            } else {
                map.put(sum, i);
            }
        }

        if (endIndex != -1) {
            for (int i = endIndex - maxLength + 1; i <= endIndex; i++) {
                result.add(arr[i]);
            }
        }

        return result;
    }
}
