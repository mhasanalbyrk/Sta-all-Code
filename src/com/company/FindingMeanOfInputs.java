package com.company;

import java.util.Scanner;

public class FindingMeanOfInputs {
    public static void main(String[] args) {
                Scanner in = new Scanner(System.in);

        System.out.println("Enter a number");
        int number = in.nextInt();

        int count = 1;
        double total = number;

        while (number != 101){
            System.out.println("Enter a number");
             number = in.nextInt();
            if (number == 101)
                break;
            total += number;
            count++;

        }
        System.out.println("Mean is =" + total / count);
    }
}
