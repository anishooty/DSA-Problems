import java.util.*;

class TrieNode {
    TrieNode[] children;

    TrieNode() {
        children = new TrieNode[2];
    }
}

public class MaximumXor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(sc.nextInt());
        }
        int ans = maximumXor(n, a);
        System.out.println(ans);
        sc.close();
    }

    public static int maximumXor(int n, ArrayList<Integer> a) {
        TrieNode root = new TrieNode();
        int maxXor = Integer.MIN_VALUE;

        for (int num : a) {
            insert(root, num);
        }

        for (int num : a) {
            maxXor = Math.max(maxXor, findMaxXor(root, num));
        }

        return maxXor;
    }

    private static void insert(TrieNode root, int num) {
        TrieNode curr = root;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (curr.children[bit] == null) {
                curr.children[bit] = new TrieNode();
            }
            curr = curr.children[bit];
        }
    }

    private static int findMaxXor(TrieNode root, int num) {
        TrieNode curr = root;
        int maxXor = 0;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            int oppositeBit = bit == 1 ? 0 : 1;
            if (curr.children[oppositeBit] != null) {
                maxXor |= (1 << i);
                curr = curr.children[oppositeBit];
            } else {
                curr = curr.children[bit];
            }
        }
        return maxXor;
    }
}
