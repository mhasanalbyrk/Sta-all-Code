package day3.exercise;

import java.util.*;

public class Exercise {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter a number");
        int number ;
        try {
            number = input.nextInt();
        }
        catch (InputMismatchException e){
            System.err.println("Entered -> not a number");
            System.exit(0);
        }

        System.out.print("Enter a string:");
        String s = input.next();
        System.out.println(s);
        boolean flag = true;
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)){
                System.out.println("Is not a palindrome");
                flag = false;
                break;
            }


        }
        if (flag)
        System.out.println("Is  a palindrome");

        Set<String> set = new LinkedHashSet<String>();
        for (int i = 0; i < s.length(); i++){
            set.add(s.charAt(i) + "");
        }
        System.out.println(set.toString());
    }
}
