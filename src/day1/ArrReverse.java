package day1;

public class ArrReverse {
    public static void main(String[] args) {
        int arr[] = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = i;
        }

        int reversedArr[] = new int[10];
        for (int i = 9; i > -1; i--) {
            reversedArr[9 - i] = arr[i];

        }

        for (int i = 0; i < 10; i++) {
            System.out.println(reversedArr[i]);
        }
    }
}
