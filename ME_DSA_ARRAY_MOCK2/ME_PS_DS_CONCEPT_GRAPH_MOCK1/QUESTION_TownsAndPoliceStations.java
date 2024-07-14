import java.util.*;

public class TownsAndPoliceStations {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n,m;
        n = sc.nextInt();
        m = sc.nextInt();
        ArrayList<ArrayList<Integer> > edges = new ArrayList<ArrayList<Integer> >();
        for(int i=0;i<n;i++){
            edges.add(new ArrayList<Integer>());
        }
        for(int i=0;i<m;i++){
            int x,y;
            x= sc.nextInt();
            y = sc.nextInt();
            edges.get(x-1).add(y-1);
            edges.get(y-1).add(x-1);
        }
        int s = sc.nextInt();
        ArrayList<Integer> sources = new ArrayList<>();
        for(int i=0;i<s;i++){
            sources.add(sc.nextInt()-1);
        }
        ArrayList<Integer> ans = townsAndPoliceStations(n,edges,sources);
        for(int distance:ans){
            System.out.println(distance);
        }
        sc.close();
    }

    public static ArrayList<Integer> townsAndPoliceStations(int n,ArrayList<ArrayList<Integer> >  edges,ArrayList<Integer> sources){
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        Queue<Integer> queue = new LinkedList<>();
        for (int source : sources) {
            dist[source] = 0;
            queue.add(source);
        }
        while (!queue.isEmpty()) {
            int city = queue.poll();
            for (int neighbor : edges.get(city)) {
                if (dist[neighbor] == -1) {
                    dist[neighbor] = dist[city] + 1;
                    queue.add(neighbor);
                }
            }
        }
        ArrayList<Integer> result = new ArrayList<>();
        for (int d : dist) {
            result.add(d);
        }
        return result;
    }
}
