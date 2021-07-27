package com.company;

import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int number = (int)(Math.random() * 100);

        int health = 5;
        int guess = -1;
        while (health != 0){
            guess = input.nextInt();
            if (number == guess){
                System.out.println("You won!!");
                break;
            }
            if (number < guess)
                System.out.println(" Wrong!! Guess lower " + --health + "  health left!");
            if (number > guess)
                System.out.println("Wrong!! Guess higher " + --health + " health left!");

        }
    }
}
