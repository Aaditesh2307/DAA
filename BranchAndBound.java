import java.util.Scanner;
public class BranchAndBound {
    public static int n; // Number of students and clubs
    public static int[][] costMatrix; // Cost matrix
    public static int[] finalAssignment; // Final assignment of students to clubs
    public static int finalCost = Integer.MAX_VALUE; // Final minimum cost
    // Calculate the lower bound for a given partial assignment
    public static int bound(int mask, int[] assignment) {
        int cost = 0;
        int count = 0;
        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) == 0) { // If student i is not assigned
                int minCost = Integer.MAX_VALUE;
                for (int j = 0; j < n; j++) {
                    if ((mask & (1 << j)) == 0) { // If club j is not assigned
                        minCost = Math.min(minCost, costMatrix[i][j]);
                    }
                }
                cost += minCost;
                count++;
            }
        }
        return cost;
    }
    // Depth-first search to explore all possible assignments
    public static void dfs(int mask, int[] assignment, int level, int currentCost) {
        if (level == n) {
            // All students assigned, check if current cost is less than final cost
            if (currentCost < finalCost) {
                finalCost = currentCost;
                System.arraycopy(assignment, 0, finalAssignment, 0, n);
            }
            return;
        }
        // Bound the search space to avoid unnecessary exploration
        int boundVal = bound(mask, assignment);
        if (currentCost + boundVal < finalCost) {
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) == 0) { // If student i is not yet assigned
                    assignment[level] = i;
                    dfs(mask | (1 << i), assignment, level + 1, currentCost + costMatrix[level][i]);
                }
            }
        }
    }
    // Main method to take input and solve the problem
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        // Take input for number of students and clubs
        System.out.print("Enter the number of students (and clubs): ");
        n = obj.nextInt();

        // Initialize the cost matrix
        costMatrix = new int[n][n];

        // Input the cost matrix
        System.out.println("Enter the cost matrix:");
        for (int i = 0; i < n; i++) {
            System.out.println("Enter the costs for Student " + (i + 1) + " (cost for each club):");
            for (int j = 0; j < n; j++) {
                System.out.print("Cost for Student " + (i + 1) + " to Club " + (j + 1) + ": ");
                costMatrix[i][j] = obj.nextInt();
            }
        }
        // Initialize the final assignment array
        finalAssignment = new int[n];
        // Start solving the problem
        dfs(0, new int[n], 0, 0);
        // Output the optimal assignment and total minimum cost
        System.out.println("\nOptimal Assignment:");
        for (int i = 0; i < n; i++) {
            System.out.println("Student " + (i + 1) + " is assigned to Club " + (finalAssignment[i] + 1));
        }
        System.out.println("\nTotal minimum cost: " + finalCost);
        obj.close();
    }
}