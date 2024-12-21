package Day_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part_2 {
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

        // Now we have the whole thing we go and look for As.
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length(); j++) {
                if (grid[i].charAt(j) == 'A') {
                    // Slightly different now, figure out the number of As that have an M and an S on adjacent sides.
                    int up_left = 0;
                    int up = 1;
                    int up_right = 2;
                    int right = 3;
                    int down_right = 4;
                    int down = 5;
                    int down_left = 6;
                    int left = 7;

                    char[] to_check = new char[8];
                    try {
                        to_check[up_left] = grid[i - 1].charAt(j - 1);
                        to_check[up] = grid[i - 1].charAt(j);
                        to_check[up_right] = grid[i - 1].charAt(j + 1);
                        to_check[right] = grid[i].charAt(j + 1);
                        to_check[down_right] = grid[i + 1].charAt(j + 1);
                        to_check[down] = grid[i + 1].charAt(j);
                        to_check[down_left] = grid[i + 1].charAt(j - 1);
                        to_check[left] = grid[i].charAt(j - 1);

                        // Check the 4 pairs.
                        int number_of_valid_pairs = 0;
                        if ((to_check[up_left] == 'M' && to_check[down_right] == 'S') || (to_check[up_left] == 'S' && to_check[down_right] == 'M')) {
                            number_of_valid_pairs++;
                        } 
                        if ((to_check[up] == 'M' && to_check[down] == 'S') || (to_check[up] == 'S' && to_check[down] == 'M')) {
                            // number_of_valid_pairs++;
                        } 
                        if ((to_check[up_right] == 'M' && to_check[down_left] == 'S') || (to_check[up_right] == 'S' && to_check[down_left] == 'M')) {
                            number_of_valid_pairs++;
                        } 
                        if ((to_check[right] == 'M' && to_check[left] == 'S') || (to_check[right] == 'S' && to_check[left] == 'M')) {
                            // number_of_valid_pairs++;
                        }

                        if (number_of_valid_pairs >= 2) {
                            count++;
                        }

                        // Overachiever much, only needed to check the diagonal pairs!

                    } catch (IndexOutOfBoundsException index_out_of_bounds_exception) {}
                } 
            }
        }

        input.close();

        System.out.println(count);
    }
}
