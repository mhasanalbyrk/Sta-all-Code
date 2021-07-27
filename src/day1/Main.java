package day1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Hello world");

        Scanner in = new Scanner(System.in);

        System.out.println("Enter a number");
        int number = in.nextInt();

        if (number % 2 == 0)
            System.out.println("Number is even");
        else
            System.out.println("Number is odd");





    }


}
