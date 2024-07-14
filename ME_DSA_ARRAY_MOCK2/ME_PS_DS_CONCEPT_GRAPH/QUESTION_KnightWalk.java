import java.util.*;

class Pair {
    int x, y;
    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class KnightWalk {
    static boolean isInside(int x, int y, int N) {
        if (x >= 1 && x <= N && y >= 1 && y <= N)
            return true;
        return false;
    }

    static int knightWalk(int[] starting_pos, int[] ending_pos, int N) {
        
        int dx[] = { -2, -1, 1, 2, -2, -1, 1, 2 };
        int dy[] = { -1, -2, -2, -1, 1, 2, 2, 1 };

        
        Queue<Pair> q = new LinkedList<>();

        q.add(new Pair(starting_pos[0], starting_pos[1]));

        int visit[][] = new int[N + 1][N + 1];

        visit[starting_pos[0]][starting_pos[1]] = 0;

        while (!q.isEmpty()) {
            Pair t = q.poll();

            if (t.x == ending_pos[0] && t.y == ending_pos[1])
                return visit[t.x][t.y];

            for (int i = 0; i < 8; i++) {
                int x = t.x + dx[i];
                int y = t.y + dy[i];

                if (isInside(x, y, N) && visit[x][y] == 0) {
                    visit[x][y] = visit[t.x][t.y] + 1;
                    q.add(new Pair(x, y));
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] starting_pos = new int[2];
        int[] ending_pos = new int[2];
        starting_pos[0] = sc.nextInt();
        starting_pos[1] = sc.nextInt();
        ending_pos[0] = sc.nextInt();
        ending_pos[1] = sc.nextInt();
        System.out.println(knightWalk(starting_pos, ending_pos, n));
    }
}
