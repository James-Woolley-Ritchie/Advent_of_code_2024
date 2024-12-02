package Day_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part_1 {

    /*
     * Pair up the smallest number in the first list with the smallest number in the second list, working up.
     * Add up all of the differences.
     */

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(".\\Day_1\\input.txt"))) {
            ArrayList<Integer> left_list = new ArrayList<>();
            ArrayList<Integer> right_list = new ArrayList<>();
            
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                left_list.add(Integer.valueOf(line.split("   ")[0].strip()));
                right_list.add(Integer.valueOf(line.split("   ")[1].strip()));
            }
            
            left_list.sort(null);
            right_list.sort(null);
            
            int total = 0;
            for (int i = 0; i < left_list.size(); i++) {
                total += (Math.abs(left_list.get(i) - right_list.get(i)));
            }
            
            System.out.println(total);
        }
    }
}
