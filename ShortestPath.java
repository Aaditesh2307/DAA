import java.util.Scanner;
public class ShortestPath {
    static int minDistance(int dist[], Boolean vis[]) {
        int V = dist.length;
        int min = Integer.MAX_VALUE, min_idx = -1;
        
        for (int v = 0; v < V; v++) {
            if (!vis[v] && dist[v] <= min) {
                min = dist[v];
                min_idx = v;
            }
        }
        return min_idx;
    }
    static void dijkstra(int graph[][], int src) {   
        int V = graph.length;
        int dist[] = new int[V];
        Boolean vis[] = new Boolean[V];
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            vis[i] = false;
        }
        dist[src] = 0;
        for (int count = 0; count < V-1; count++) {
            int u = minDistance(dist, vis);
            vis[u] = true;
            for (int v = 0; v < V; v++)
                if ( !vis[v] && graph[u][v] != 0 
                && dist[u] != Integer.MAX_VALUE 
                && dist[v] > graph[u][v] + dist[u] )
                    dist[v] = dist[u] + graph[u][v];
        }
        System.out.println(
                "Node \t\t TravelTime from Source -> " + src);
        for (int i = 0; i < V; i++)
            System.out.println(i + " \t\t " + dist[i]);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of Nodes : ");
        int n = sc.nextInt();
        System.out.print("Enter number of Edges : ");
        int m = sc.nextInt();
        int[][] graph = new int[n][n];
        System.out.println("Enter edges in the format (u v w): ");
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            //Unidirected Graph
            graph[u][v] = graph[v][u] = w;
        }
        System.out.print("Enter the source Node (K) : ");
        int src = sc.nextInt();
        dijkstra(graph, src);
        sc.close();
    }
}