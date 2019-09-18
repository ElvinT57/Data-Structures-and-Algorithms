import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * Purpose: Pascal's triangle, iterative and recursive binomial coefficient, and factorials.
 * Last update: 03/24/19
 *
 * @author: Elvin Torres
 */
public class Driver {
    static BufferedReader std = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringBuilder input = new StringBuilder("");

        while (true) {
            //Prompt for which section of problem 3 to execute
            System.out.print("Selection problem 3 section (a), (c), (d), (e), exit(x): ");
            getInput(input);
            switch (input.toString()) {
                case "a":
                    a(input);
                    break;
                case "c":
                    c(input);
                    break;
                case "d":
                    d(input);
                    break;
                case "e":
                    e(input);
                    break;
                case "x":
                    System.out.println("Good bye. Exiting program...");
                    System.exit(0);
                    break;
            }
            System.out.println();
        }
    }

    /**
     * Calculate the given (n,k) binomial coefficient using
     * the recursive method
     */
    public static void a(StringBuilder input) throws IOException {
        int n = 0, k = 0;    //initialize variables for BC

        //prompt for n and k
        System.out.print("n: ");
        getInput(input);
        n = Integer.parseInt(input.toString());

        System.out.print("k: ");
        getInput(input);
        k = Integer.parseInt(input.toString());

        //display coefficient
        System.out.println("(" + n + ", " + k + "): " + rCoefficient(n, k));
    }

    /**
     * Method for problem C; Pascal triangle
     *
     * @param input
     * @throws IOException
     */
    public static void c(StringBuilder input) throws IOException {
        //prompt for the rows(n)
        System.out.print("Enter n rows: ");
        getInput(input);
        int n = Integer.parseInt(input.toString());
        //initialize first previous array
        int[] previous = new int[2];
        previous[0] = 1;
        previous[1] = 1;
        //create first current array
        int[] current = new int[3];
        //display row 0 and 1
        System.out.println("1");
        printArray(previous);
        //Compute the pascal triangle!
        for (int row = 2; row <= n; row++) {
            //set the first element of the row as 1
            current[0] = 1;
            //calculate up to the center of the row
            for (int i = 1; i <= (current.length / 2); i++) {
                //enquiry the previous two values and add
                current[i] = previous[i - 1] + previous[i];
                //reflect value on the other side of the array
                current[current.length - (1 + i)] = current[i];
            }
            //set the last element of the row to 1
            current[current.length - 1] = 1;
            //print the new current row
            printArray(current);
            //set current to previous and create a new current for
            //the next iteration
            previous = current;
            current = new int[current.length + 1]; //increment the curr size
        }
    }

    /**
     * Calculate the given (n,k) binomial coefficient using
     * the iterative method
     */
    public static void d(StringBuilder input) throws IOException {
        int n = 1, k = 1;
        BigInteger result;
        //prompt for n and k
        System.out.print("n: ");
        getInput(input);
        n = Integer.parseInt(input.toString());

        System.out.print("k: ");
        getInput(input);
        k = Integer.parseInt(input.toString());

        if (k == 0 || n == k)
            result = BigInteger.ONE;
        else {
            // Calculate each part of the fraction separately
            //          n(n-1)...(n-k+1)
            // nCr =    ----------------
            //             k(k-1)...1
            BigInteger numerator = new BigInteger(Integer.toString(n));
            for (int i = 1; i < k; i++) {
                numerator = numerator.multiply(new BigInteger(Integer.toString(n - i)));
            }
            BigInteger denominator = new BigInteger(Integer.toString(k));
            for (int i = 1; i < k; i++) {
                denominator = denominator.multiply(new BigInteger(Integer.toString(k - i)));
            }
            //now divide them to compute nCr
            result = numerator.divide(denominator);
        }
        //display result
        System.out.println("(" + n + ", " + k + "): " + result.toString());
    }

    /**
     * Calculate the given (n,k) binomial coefficient using
     * the formulaic method
     */
    public static void e(StringBuilder input) throws IOException {
        int n = 1, k = 1;
        BigInteger result;
        //prompt for n and k
        System.out.print("n: ");
        getInput(input);
        n = Integer.parseInt(input.toString());

        System.out.print("k: ");
        getInput(input);
        k = Integer.parseInt(input.toString());

        if (k == 0 || n == k)
            result = BigInteger.ONE;
        else {
            //check if k > n - k to decide on the
            //appropriate formula
            if (k > n - k)
                result = binomialCoe1(k, n);
            else
                result = binomialCoe2(k, n);
        }
        //display result
        System.out.println("(" + n + ", " + k + "): " + result.toString());
    }

    /**
     * Binomial Coefficient formula #1
     *
     * (k+1)(k+2) ... n
     * -----------------
     *     (n-k)!
     */
    private static BigInteger binomialCoe1(int k, int n) {
        BigInteger numerator = BigInteger.ONE, denominator = BigInteger.ONE;
        //calculate the numerator
        for (int i = k + 1; i <= n; i++) {
            numerator = numerator.multiply(new BigInteger(Integer.toString(i)));
        }
        //calculate the denominator by using the iterative factorial method
        denominator = iFactorial(n - k);    //(n-k)!

        return numerator.divide(denominator);
    }

    /**
     * Binomial Coefficient formula #2
     *
     * (n-k+1)(n-k+2) ... n
     * --------------------
     *        k!
     */
    private static BigInteger binomialCoe2(int k, int n) {
        BigInteger numerator = BigInteger.ONE, denominator = BigInteger.ONE;
        //calculate the numerator
        for (int i = n - k + 1; i <= n; i++) {
            numerator = numerator.multiply(new BigInteger(Integer.toString(i)));
        }
        //calculate the denominator by using the iterative factorial method
        denominator = iFactorial(k);    //(k)!

        return numerator.divide(denominator);
    }

    /**
     * Iterative factorial method
     */
    private static BigInteger iFactorial(int n) {
        BigInteger result = BigInteger.ONE;

        for (int i = 1; i <= n; i++) {
            result = result.multiply(new BigInteger(Integer.toString(i)));
        }
        return result;
    }

    /**
     * Recursive coefficient method
     */
    public static BigInteger rCoefficient(int n, int k) {
        BigInteger result = BigInteger.ZERO;
        //Base case of (n k)
        if (k == 0 || n == k)
            result = BigInteger.ONE;
        else
            result = rCoefficient(n - 1, k - 1).add(rCoefficient(n - 1, k));
        return result;
    }

    ///////////////////////////HELPER METHODS///////////////////////////
    private static void getInput(StringBuilder input) throws IOException {
        //change the stringbuilder in place
        input.replace(0, input.length(), std.readLine().toString());
        System.out.println(input);  //echo
    }

    /**
     * Prints given array
     *
     * @param arr
     */
    private static void printArray(int[] arr) {
        StringBuilder str = new StringBuilder("");
        for (int i = 0; i < arr.length; i++)
            str.append(arr[i] + " ");
        System.out.println(str.toString());
    }
}
