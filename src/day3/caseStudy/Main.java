package day3.caseStudy;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String in = "ali ali ahmet mehmet veli veli ali ahmet";
         String[] input = in.split(" ");
        //HashMap<String, Integer> khmap = new HashMap<String, Integer>();
        SortedMap<String, Integer> hmap = new TreeMap<String, Integer>();

        for (int i = 0; i < input.length; i++) {
            String s = input[i];

            if (hmap.get(s) == null)
                hmap.put(s, 1);
            else{
                int c = hmap.get(s);
                hmap.put(s, ++c);
            }

        }

        System.out.println(hmap.toString());
    }
}
