package Day_7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part_2 {
    public static void main(String[] args) throws FileNotFoundException {
        // Get the input.
        // Example format.
        boolean possible = false;
        Scanner input = new Scanner(new File(".\\Day_7\\input.txt"));
        long total = 0;
        while (input.hasNextLine()) {
            String[] example_data = input.nextLine().split(" ");
            long target_answer = Long.parseLong(example_data[0].replace(":", ""));
            long[] values = new long[example_data.length - 1];
            for (int i = 1; i < example_data.length; i++) {
                values[i - 1] = Long.parseLong(example_data[i]);
            }

            String[] operations = {"+", "*", "||"};

            // Now determine if it's solveable.
            possible = is_solveable(target_answer, values, operations);
            if (possible) {
                total += target_answer;
            }
        }

        System.out.println(total);
        input.close();
    }

    private static boolean is_solveable(long target_answer, long[] values, String[] operations) {
        if (values.length == 1) {
            return values[0] == target_answer;
        }

        long a = values[0];
        long b = values[1];
        long[] new_values = new long[values.length - 1];
        for (int i = 1; i < new_values.length; i++) {
            new_values[i] = values[i + 1]; 
        }
        for (String operation : operations) {
            long new_total = 0;
            if (operation.equals("+")) {
                new_total = a + b;
            } else if (operation.equals("*")) {
                new_total = a * b;
            } else if (operation.equals("||")) {
                new_total = Long.parseLong(Long.toString(a) + Long.toString(b));
            }
            if (new_total <= target_answer) {
                new_values[0] = new_total;
                if (is_solveable(target_answer, new_values, operations)) {
                    return true;
                }
            }
        }

        return false;
    }
}
