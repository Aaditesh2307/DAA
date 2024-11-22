//import the necessary resources 
import java.util.Arrays;
import java.util.Scanner;
//make the main class
public class KnightTour1 {
    //spcify the moves for the knight
    private static final int[] moveX = {2,1,-1,-2,-2,-1,1,2};
    private static final int[] moveY = {1,2,2,1,-1,-2,-2,-1};

    //function to print the board after solving is done
    private static void printBoard (int[][] board){
        //Iterate over the board
        for(int[] row :board){
            //Iterate over the board 
            for(int cell : row){
                System.out.print(String.format("%2d",cell)+"");
            }
            System.out.println();
        }
    }
    //Function to check is next postion is safe for the knight
    private static boolean isSafe(int[][] board,int x,int y){
        int size= board.length;
        return x >= 0 && y >= 0 && x < size && y < size && board[x][y] == -1;
    }
    //Actual algorithm for the knight tour problem
    private static boolean solveKnigtTour(int[][] board,int x,int y, int moveCount){
        //write the base case of recursion
        int size = board.length;
        if(moveCount == size  * size) return true;
        //check for all the 8 moves possible for the knight
        for(int i = 0; i < 8;i++){
            int nextX = x + moveX[i];
            int nextY = y + moveY[i];
            //use isSafe
            if(isSafe(board, nextX, nextY)){
                board[nextX][nextY] = moveCount;
                if(solveKnigtTour(board, nextX, nextY, moveCount + 1)) return true;
                board[nextX][nextY] = -1;
            }
        }
        return false;
    }
    //Write the main method
    public static void main(String[] args) {
        //Initlize the scanner object 
        Scanner obj = new Scanner(System.in);
        //Take the input for the chessboard size
        System.out.println("Enter the size of the chessboard (N) : ");
        int size = obj.nextInt();
        //Take the input for the starting postion
        System.out.println("Enter the starting position (x,y)");
        int startX = obj.nextInt();
        int startY = obj.nextInt();
        //check the validity for the input
        if(size <= 0 || startX < 0 || startX >= size || startY < 0 || startY >= size){
            System.out.println("Invalid board size or starting position !");
            return;
        }
        //Intilize the chessboard with the size and fill slots of it with -1
        int[][] board = new int[size][size];

        for(int[] row : board){
            Arrays.fill(row,-1);
        }
        //Initilize the first postion
        board[startX][startY] = 0;
        //call the function for the knight tour
        if(solveKnigtTour(board, startX, startY, 1)){
            printBoard(board);                  
        }else{
            System.out.println("No solution exists!!! ");
        }
    }
}