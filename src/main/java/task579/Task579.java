package task579;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Task579 {
    public static void main(String ... arg) {

        Task579 task = new Task579();

        List<Integer> listNumbers = task.getLineNumbers();

        task.writeResultToFile(task.getSubsequenceMaxSum(listNumbers));


    }

    private List<Integer> getSubsequenceMaxSum(List<Integer> inputNumbers) {
        int minusValues = 0;
        int plusValues = 0;
        int index = 0;
        List<Integer> resultPlus = new ArrayList<Integer>();
        List<Integer> resultMinus = new ArrayList<Integer>();
        Iterator<Integer> iterator = inputNumbers.iterator();

        while (iterator.hasNext()) {
            index++;
            int valuesNextElement = iterator.next();
            if(valuesNextElement >= 0) {
                plusValues += valuesNextElement;
                resultPlus.add(index);
            } else {
                minusValues += Math.abs(valuesNextElement);
                resultMinus.add(index);
            }
        }

        if (plusValues > minusValues) {
            return resultPlus;
        } else {
            return resultMinus;
        }
    }

    private List <Integer> getLineNumbers() {
        Scanner scanner = null;
        List<Integer> numbers = new ArrayList<Integer>();
        int sequenceLenght = 0;

        try {
            File file = new File("input.txt");
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Error: file 'input.txt' not found! " + e);
        }

        for (int i=0; i<2; i++) {
            String line = scanner.nextLine().trim();
            String [] numbersString = line.split("\\s+");
            if (i==0){

                sequenceLenght = Integer.parseInt(line);
            }

            if (i==1){
                for (String number : numbersString) {
                    if (sequenceLenght > 0) {
                        numbers.add(Integer.parseInt(number));
                        sequenceLenght--;
                    } else
                        break;
                }

            }

        }
        scanner.close();
        return numbers;
    }

    private void writeResultToFile(List<Integer> listResult){

        String result;
        int sizeResult = listResult.size();
        result = "" + sizeResult + "\n";
        for (Integer number : listResult) {
            result =  result + number.toString() + " ";
        }

        try {
            FileWriter fileWriter = new FileWriter("output.txt");
            fileWriter.write(result);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error: could not write file 'output.txt'" + e);
        }

    }

}
