import java.math.BigInteger;
import java.util.Scanner;

public class KaratsubaSquare {

    // Karatsuba multiplication to calculate the square of a number
    static BigInteger karatsuba(BigInteger X) {
        // Base case: if the number is small enough (single digit), simply square it
        if (X.compareTo(BigInteger.TEN) < 0) {
            return X.multiply(X);
        }

        // Determine the size of X
        int size = X.toString().length();
        int halfSize = (size / 2) + (size % 2);  // Ceiling division of size by 2
        
        // Create a multiplier to split the number
        BigInteger multiplier = BigInteger.TEN.pow(halfSize);
        
        // Split the number X into two parts
        BigInteger a = X.divide(multiplier);  // The higher part
        BigInteger b = X.subtract(a.multiply(multiplier));  // The lower part (X - a * multiplier)

        // Recursively calculate three products
        BigInteger p1 = karatsuba(a);  // a^2
        BigInteger p2 = karatsuba(b);  // b^2
        BigInteger p3 = karatsuba(a.add(b));  // (a + b)^2
        
        // The final square is p1 * (10^2 * p3 - p2) + p2
        return p1.multiply(BigInteger.TEN.pow(2 * halfSize))
                .add(p3.subtract(p1).subtract(p2).multiply(BigInteger.TEN.pow(halfSize)))
                .add(p2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Input a 20-digit number
        System.out.print("Enter a 20-digit number: ");
        BigInteger x = sc.nextBigInteger();
        
        // Calculate and print the square using the Karatsuba algorithm
        BigInteger result = karatsuba(x);
        System.out.println("The square of the number is: " + result);
    }
}
