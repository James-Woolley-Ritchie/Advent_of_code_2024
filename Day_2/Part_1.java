package Day_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part_1 {
    public static void main(String[] args) throws FileNotFoundException {
        int number_of_safe = 0;

        Scanner input = new Scanner(new File(".\\Day_2\\input.txt"));
        while (input.hasNextLine()) {
            String[] level = input.nextLine().split(" ");
            int difference = 0;
            boolean is_safe = true;
            for (int i = 0; i < level.length - 1; i++) {
                int new_difference = Integer.valueOf(level[i]) - Integer.valueOf(level[i + 1]);
                
                // Difference cannot be more than 3.
                if (Math.abs(new_difference) > 3) {
                    is_safe = false;
                    break;
                }
        
                // Difference cannot be in opposite direction.
                if ((difference < 0 && new_difference > 0) || (difference > 0 && new_difference < 0)) {
                    is_safe = false;
                    break;
                }

                // Difference cannot be 0.
                if (new_difference == 0) {
                    is_safe = false;
                    break;
                }

                difference = new_difference;
            }

            if (is_safe) {
                number_of_safe ++;
            }
            
        }

        System.out.println(number_of_safe);
        input.close();
    }
}