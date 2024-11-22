import java.io.*;
import java.lang.*;
import java.util.*;

class Job {
   int id, profit, deadline;
   Job(int x, int y, int z) {
      this.id = x;
      this.deadline = y;
      this.profit = z;
   }
}

class solve {
   // return an array of size 2 having the 0th element equal to the count
   // and 1st element equal to the maximum profit
   int[] JobScheduling(Job arr[], int n) {
      // Sort jobs based on profit in descending order
      Arrays.sort(arr, (a, b) -> (b.profit - a.profit));

      int maxi = 0;
      // Find the maximum deadline
      for (int i = 0; i < n; i++) {
         if (arr[i].deadline > maxi) {
            maxi = arr[i].deadline;
         }
      }

      int result[] = new int[maxi + 1];
      // Initialize the result array with -1 to indicate no job is scheduled
      for (int i = 1; i <= maxi; i++) {
         result[i] = -1;
      }

      int countJobs = 0, jobProfit = 0;
      List<Integer> scheduledJobs = new ArrayList<>();

      // Loop through all the jobs
      for (int i = 0; i < n; i++) {
         // Find a free slot for the job
         for (int j = arr[i].deadline; j > 0; j--) {
            if (result[j] == -1) {
               // Schedule the job in the free slot
               result[j] = i;
               countJobs++;
               jobProfit += arr[i].profit;
               scheduledJobs.add(arr[i].id); // Add job ID to the list of scheduled jobs
               break;
            }
         }
      }

      // Print the scheduled jobs
      System.out.println("Scheduled jobs: " + scheduledJobs);

      int[] ans = new int[2];
      ans[0] = countJobs;
      ans[1] = jobProfit;

      return ans;
   }
}

class JobSchedule {  // Main class name should match the file name
    public static void main(String[] args) throws IOException {
 
       // Read input for number of jobs
       Scanner sc = new Scanner(System.in);
       System.out.print("Enter number of jobs: ");
       int n = sc.nextInt();
 
       Job[] arr = new Job[n];
 
       // Read details for each job
       for (int i = 0; i < n; i++) {
          System.out.print("Enter job " + (i+1) + " details (id, deadline, profit): ");
          int id = sc.nextInt();
          int deadline = sc.nextInt();
          int profit = sc.nextInt();
          arr[i] = new Job(id, deadline, profit);
       }
 
       solve ob = new solve();
 
       // Function call
       int[] res = ob.JobScheduling(arr, n);
 
       // Print the result (count of jobs and total profit)
       System.out.println("Number of jobs: " + res[0]);
       System.out.println("Total profit: " + res[1]);
    }
 }