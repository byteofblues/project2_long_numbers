package project2;
import project2.Number;


public class Driver {

    // method for testing the Numbers class
    public static void main(String[] args) {

        // Number number1 = new Number("254902387");
        // System.out.println(number1);
        // System.out.println(number1.getHeadValue());
        // System.out.println(number1.getTailValue());
        // System.out.println(number1.getHead());

        // Number number2 = new Number("23345653");
        // System.out.println(number2);
        // System.out.println(number2.getHeadValue());
        // System.out.println(number2.getTailValue());
        // System.out.println(number2.getHead());

        // int n = 187;
        // System.out.println(n); // n
        // System.out.println(n % 10); // ones
        // System.out.println(((n % 100) - (n % 10)) / 10); // tens
        // System.out.println();
        // // number1.add(number2);
        // System.out.println(number1.add(number2));
        Number number3 = new Number("20");
        System.out.println(number3.length());
        System.out.println(number3);
        Number number4 = new Number("10209");
        System.out.println(number4.length());
        System.out.println(number4);
        // System.out.println(number4.getDigitCount());
        // System.out.println(number3.equals(number4));
    }
}