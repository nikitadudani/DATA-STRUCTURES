//{ Driver Code Starts
import java.util.*;
import java.io.*;
import java.lang.*;

class OptimalStrategy
{
    public static void main (String[] args) {
        
        //taking input using Scanner class
        Scanner sc = new Scanner(System.in);
        
        //taking total number of testcases
        int t = sc.nextInt();
        
        while(t-- > 0)
        {
            //taking number of elements
            int n = sc.nextInt();
            int arr[] = new int[n];
            
            //inserting the elements
            for(int i = 0; i < n; i++)
                arr[i] = sc.nextInt();
                
           //calling the countMaximum() method of class solve
           System.out.println(new solve().countMaximum(n, arr)); 
        }
    }
    
    
}
// } Driver Code Ends

class solve
{
    //Function to find the maximum possible amount of money we can win.
    static long countMaximum(int n, int arr[])
    {
        // Your code here
        // Create a DP array to store the maximum amount that can be obtained from
        // subarrays arr[i..j], where 0 <= i <= j < n
        long[][] dp = new long[n][n];

        // Iterate over subarray lengths
        for (int len = 0; len < n; len++) {
            // Iterate over starting index of subarray
            for (int i = 0; i + len < n; i++) {
                // Calculate ending index of subarray
                int j = i + len;

                // Base case: If there is only one coin, the player can only pick that coin
                if (i == j) {
                    dp[i][j] = arr[i];
                } else if (i + 1 == j) {
                    // If there are only two coins, the player will pick the maximum valued coin
                    dp[i][j] = Math.max(arr[i], arr[j]);
                } else {
                    // Case 1: If the player picks the first coin, the opponent can either pick
                    // the first or last coin from the remaining subarray. The player gets the
                    // remaining maximum amount from the subarray after the opponent's turn.
                    long pickFirst = arr[i] + Math.min(dp[i + 2][j], dp[i + 1][j - 1]);

                    // Case 2: If the player picks the last coin, the opponent can either pick
                    // the first or last coin from the remaining subarray. The player gets the
                    // remaining maximum amount from the subarray after the opponent's turn.
                    long pickLast = arr[j] + Math.min(dp[i + 1][j - 1], dp[i][j - 2]);

                    // Choose the maximum amount the player can get from the two cases
                    dp[i][j] = Math.max(pickFirst, pickLast);
                }
            }
        }

        // The maximum amount the player can win will be stored at dp[0][n-1]
        return dp[0][n - 1];
    }
}