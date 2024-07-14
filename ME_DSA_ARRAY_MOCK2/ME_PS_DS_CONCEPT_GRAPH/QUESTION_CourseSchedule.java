import java.util.*;

class CourseSchedule {
    public static boolean courseSchedule(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0;i<numCourses;i++){
            adj.add(new ArrayList<Integer>());
        }
        for(int[] edge : prerequisites){
            adj.get(edge[1]-1).add(edge[0]-1);
        }
        int[] visited = new int[numCourses];
        for(int i=0;i<numCourses;i++){
            if(visited[i] == 0 && hasCycle(adj, visited, i)){
                return false;
            }
        }
        return true;
    }

    private static boolean hasCycle(ArrayList<ArrayList<Integer>> adj, int[] visited, int v) {
        visited[v] = 1;
        for(int i : adj.get(v)){
            if(visited[i] == 1 || (visited[i] == 0 && hasCycle(adj, visited, i))){
                return true;
            }
        }
        visited[v] = 2;
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int [][] edgeList = new int[m][2];
        for(int i=0;i<m;i++)
        {
            edgeList[i][0] = sc.nextInt();
            edgeList[i][1] = sc.nextInt();
        }
        boolean ans=courseSchedule(n,edgeList);
        if(ans)
            System.out.println("true");
        else
            System.out.println("false");
    }
}
