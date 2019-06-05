/*
Chapter 13 Problem 16
Christopher Sutton
4/12/2019
Rational Number Calculator Program
Write a program similar to listing 7.9, Calculator.java.  Instead of using integers,
use rationals, as showin in figure 13.10.  You will need to use the split method in the
String class to retrieve the numerator string and denominator string, and convert strings
into integers using the Integer.parseInt method.
 */

import java.util.*;
public class RationalNumberCalculator {
    static Scanner kbd = new Scanner(System.in);
    
    public static void main(String[] args){
        String[] rationalNum = new String[2];
        System.out.println("Welcome to my Rational Numbers Calculator!");
        System.out.println("Enter two rational numbers separated by a space: ");
        rationalNum[0] = kbd.next();
        rationalNum[1] = kbd.next();
        
        //splitting first Rational number
        String[] parts = rationalNum[0].split("/");
        int numerator = Integer.parseInt(parts[0]);
        int denominator = Integer.parseInt(parts[1]);
        Rational r1 = new Rational(numerator,denominator);
        
        //splitting second Rational number
        parts = rationalNum[1].split("/");
        numerator = Integer.parseInt(parts[0]);
        denominator = Integer.parseInt(parts[1]);
        Rational r2 = new Rational(numerator,denominator);
        
        //user choice of operator and output of results
        System.out.print("Press + to add, - to subtract, * to multiply, and / to divide: ");
        char choice = kbd.next().charAt(0);
        
        switch(choice){
            case '+':
                System.out.println(r1+" + "+r2+" = "+r1.add(r2));
                break;
            case '-':
                System.out.println(r1+" - "+r2+" = "+r1.subtract(r2));
                break;
            case '*':
                System.out.println(r1+" * "+r2+" = "+r1.multiply(r2));
                break;
            case '/':
                System.out.println(r1+" / "+r2+" = "+r1.divide(r2));
                break;
        }
    }
    //##########################################################################
    // : THIS CLASS IS GIVEN IN THE TEXTBOOK
    public static class Rational extends Number implements Comparable<Rational> {
        // Data fields for numerator and denominator
        private long numerator = 0;
        private long denominator = 1;

        /** Construct a rational with default properties */
        public Rational() {
            this(0, 1);
        }

        /** Construct a rational with specified numerator and denominator */
        public Rational(long numerator, long denominator) {
          long gcd = gcd(numerator, denominator);
          this.numerator = (denominator > 0 ? 1 : -1) * numerator / gcd;
          this.denominator = Math.abs(denominator) / gcd;
        }

        /** Find GCD of two numbers */
        private long gcd(long n, long d) {
          long n1 = Math.abs(n);
          long n2 = Math.abs(d);
          int gcd = 1;
            
          for (int k = 1; k <= n1 && k <= n2; k++) {
            if (n1 % k == 0 && n2 % k == 0)
              gcd = k;
          }
          return gcd;
        }

        /** Return numerator */
        public long getNumerator() {
          return numerator;
        }
        /** Return denominator */
        public long getDenominator() {
          return denominator;
        }
        /** Add a rational number to this rational */
        public Rational add(Rational secondRational) {
            long n = numerator * secondRational.getDenominator() +
            denominator * secondRational.getNumerator();

            long d = denominator * secondRational.getDenominator();
            return new Rational(n, d);
          }

        /** Subtract a rational number from this rational */
        public Rational subtract(Rational secondRational) {
          long n = numerator * secondRational.getDenominator()
            - denominator * secondRational.getNumerator();

          long d = denominator * secondRational.getDenominator();
          return new Rational(n, d);
        }

        /** Multiply a rational number to this rational */
        public Rational multiply(Rational secondRational) {
          long n = numerator * secondRational.getNumerator();
          long d = denominator * secondRational.getDenominator();
          return new Rational(n, d);
        }

        /** Divide a rational number from this rational */
        public Rational divide(Rational secondRational) {
          long n = numerator * secondRational.getDenominator();
          long d = denominator * secondRational.numerator;
          return new Rational(n, d);
        }

        @Override 
        public String toString() {
          if (denominator == 1)
            return numerator + "";
          else
            return numerator + "/" + denominator;
        }

        @Override//Override the equals method in the Object class
        public boolean equals(Object other) {
          if ((this.subtract((Rational)(other))).getNumerator() == 0)
            return true;
          else
            return false;
        }

        @Override//Implement the abstract intValue method in Number
        public int intValue() {
          return (int)doubleValue();
        }

        @Override//Implement the abstract floatValue method in Number
        public float floatValue() {
          return (float)doubleValue();
        }

        @Override//Implement the doubleValue method in Number
        public double doubleValue() {
          return numerator * 1.0 / denominator;
        }

        @Override//Implement the abstract longValue method in Number
        public long longValue() {
          return (long)doubleValue();
        }

        @Override//Implement the compareTo method in Comparable
        public int compareTo(Rational o) {
          if (this.subtract(o).getNumerator() > 0)
            return 1;
          else if (this.subtract(o).getNumerator() < 0)
            return -1;
          else
            return 0;
        }

    }
}
/*
Sample Output:

Welcome to my Rational Numbers Calculator!
Enter two rational numbers separated by a space: 
5/2 7/4
Press + to add, - to subtract, * to multiply, and / to divide: +
5/2 + 7/4 = 17/4

Welcome to my Rational Numbers Calculator!
Enter two rational numbers separated by a space: 
5/2 7/4
Press + to add, - to subtract, * to multiply, and / to divide: -
5/2 - 7/4 = 3/4

Welcome to my Rational Numbers Calculator!
Enter two rational numbers separated by a space: 
5/2 7/4
Press + to add, - to subtract, * to multiply, and / to divide: *
5/2 * 7/4 = 35/8

Welcome to my Rational Numbers Calculator!
Enter two rational numbers separated by a space: 
5/2 7/4
Press + to add, - to subtract, * to multiply, and / to divide: /
5/2 / 7/4 = 10/7

*/
