package Day_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part_1 {
    public static void main(String[] args) throws FileNotFoundException {
        // Only check every X, each one only appears once per XMAS but can be a part of multiple instances.
        Scanner input = new Scanner(new File(".\\Day_4\\input.txt"));
        
        // Load the whole thing.
        String[] grid = new String[140];
        int line_count = 0;
        while (input.hasNextLine()) {
            grid[line_count] = input.nextLine();
            line_count++;
        }

        // Now we have the whole thing we go and look for Xs.
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length(); j++) {
                if (grid[i].charAt(j) == 'X') {
                    // Right.
                    try {
                        if (grid[i].charAt(j + 1) == 'M' && grid[i].charAt(j + 2) == 'A' && grid[i].charAt(j + 3) == 'S') {
                            count++;
                        }
                    } catch (IndexOutOfBoundsException index_out_of_bounds_exception) {}

                    // Down right.
                    try {
                        if (grid[i + 1].charAt(j + 1) == 'M' && grid[i + 2].charAt(j + 2) == 'A' && grid[i + 3].charAt(j + 3) == 'S') {
                            count++;
                        }
                    } catch (IndexOutOfBoundsException index_out_of_bounds_exception) {}

                    // Down.
                    try {
                        if (grid[i + 1].charAt(j) == 'M' && grid[i + 2].charAt(j) == 'A' && grid[i + 3].charAt(j) == 'S') {
                            count++;
                        }
                    } catch (IndexOutOfBoundsException index_out_of_bounds_exception) {}

                    // Down left.
                    try {
                        if (grid[i + 1].charAt(j - 1) == 'M' && grid[i + 2].charAt(j - 2) == 'A' && grid[i + 3].charAt(j - 3) == 'S') {
                            count++;
                        }
                    } catch (IndexOutOfBoundsException index_out_of_bounds_exception) {}

                    // Left.
                    try {
                        if (grid[i].charAt(j - 1) == 'M' && grid[i].charAt(j - 2) == 'A' && grid[i].charAt(j - 3) == 'S') {
                            count++;
                        }
                    } catch (IndexOutOfBoundsException index_out_of_bounds_exception) {}

                    // Up left.
                    try {
                        if (grid[i - 1].charAt(j - 1) == 'M' && grid[i - 2].charAt(j - 2) == 'A' && grid[i - 3].charAt(j - 3) == 'S') {
                            count++;
                        }
                    } catch (IndexOutOfBoundsException index_out_of_bounds_exception) {}

                    // Up.
                    try {
                        if (grid[i - 1].charAt(j) == 'M' && grid[i - 2].charAt(j) == 'A' && grid[i - 3].charAt(j) == 'S') {
                            count++;
                        }
                    } catch (IndexOutOfBoundsException index_out_of_bounds_exception) {}

                    // Up right.
                    try {
                        if (grid[i - 1].charAt(j + 1) == 'M' && grid[i - 2].charAt(j + 2) == 'A' && grid[i - 3].charAt(j + 3) == 'S') {
                            count++;
                        }
                    } catch (IndexOutOfBoundsException index_out_of_bounds_exception) {};
                } 
            }
        }

        input.close();

        System.out.println(count);
    }
}
