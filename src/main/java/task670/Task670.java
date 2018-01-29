package task670;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task670 {

    public static void main(String ... arg) {
        Task670 task = new Task670();
        int number;
        number = task.getNumberFromSequence(task.readFile());
        task.writeResultToFile(number);
    }

    private int getNumberFromSequence(int value) {
        int k = 0;
        List<Integer> result = new ArrayList<Integer>();
        for(int i=1; i<=value; i++) {
            if (!chekNumber(k+=1)) {
                result.add(k);
            } else {
                i--;
            }
        }
        return result.get(value-1);
    }

    private boolean chekNumber(int number) {
        boolean bool = false;
        int bitNumber = 0;
        List<Integer> tempList = new ArrayList<Integer>();
        while (number >= 1) {
            bitNumber++;
            tempList.add(number % 10);
            number /=  10;
        }

        for (int i = 0; i < tempList.size() - 1; i++) {
            for (int k = i+1; k < tempList.size(); k++) {
                if (tempList.get(i) == tempList.get(k)) {
                    bool = true;
                    break;
                }
            }
        }
        return bool;
    }

    private int readFile() {

        int result = -1;

        try {
            File file = new File("input.txt");
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine().trim();
            result = Integer.parseInt(line);
        } catch (FileNotFoundException e) {
            System.out.println("Error: file 'input.txt' not found! " + e);
        }

        return result;
    }

    private void writeResultToFile(int value) {
        String result = "" + value;
        try {
            FileWriter fileWriter = new FileWriter("output.txt");
            fileWriter.write(result);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error: could not write file 'output.txt'" + e);
        }

    }
}
