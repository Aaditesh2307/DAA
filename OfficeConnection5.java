import java.util.Scanner;

public class OfficeConnection5 {
    final static int INF = Integer.MAX_VALUE;
    public static void floydWarshall(int graph[][],int V){
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if(graph[i][k] != INF && graph[k][j] != INF && graph[i][j] > graph[i][k] + graph[k][j]){
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }
        printSolution(graph,V);
    }
    public static void printSolution(int graph[][],int V){
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if(graph[i][j] == INF){
                    System.out.print("INF");
                }else{
                    System.out.print(graph[i][j]+" ");
                }
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);

        System.out.println("Enter the number of vertex : ");
        int V = obj.nextInt();
        
        int[][] graph = new int[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = (i==j)?0:INF;
            }
        }

        System.out.println("Enter the number of the connections : ");
        int E = obj.nextInt();
        System.out.println("Enter the input in the form <office 1> <office 2> <cost>");
        for (int i = 0; i < E; i++) {
            int u = obj.nextInt();
            int v = obj.nextInt();
            int cost = obj.nextInt();

            if(u < V && v < V){
                graph[u][v] = cost;
                graph[v][u] = cost;
            }else{
                System.out.println("Invalid Input !!!");
                return;
            }
        }
        floydWarshall(graph, V);
    }
}