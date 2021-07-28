package day3.lab1;

import java.io.*;

public class IOExample {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\Hasan\\IdeaProjects\\Staj gün 1\\src\\day3\\text.txt");
        FileReader fr = null;
        try {
             fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert fr != null;
        BufferedReader bufferedReader = new BufferedReader(fr);
        String s ;
        while ( (s = bufferedReader.readLine() )!= null){
            System.out.println(s);
        }

        FileWriter  fw = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fw);

        bufferedWriter.write("appended");
        fr.close();
        fw.close();
        bufferedReader.close();
        bufferedWriter.close();

    }
}
