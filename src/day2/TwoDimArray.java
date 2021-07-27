package day2;

import java.util.Scanner;

public class TwoDimArray {
    public static void main(String[] args) {

        System.out.println("Enter the number of cities");
        Scanner input = new Scanner(System.in);
        int numberOfCities = input.nextInt();
        String[][] cities = new String[numberOfCities][3];

        for (int i = 0; i < numberOfCities; i++) {
            System.out.print("Enter a city name -> ");
            cities[i][0] = input.next();
            for (int j = 1; j < 3; j++) {
                System.out.print("Enter 2 districts (one at a time!)-> ");
                cities[i][j] = input.next();
            }
        }

        for (int i = 0; i < numberOfCities; i++) {
            System.out.print(cities[i][0] + " -> ");

            for (int j = 1; j < 3; j++) {
                System.out.print(cities[i][j]);
            }
            System.out.println(", ");
        }
    }
}
