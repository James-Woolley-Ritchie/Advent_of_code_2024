package Day_6;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Part_2 {
    // No, don't brute force it, be clever and elegant.
    // Shut up.

    // I'm going to use the big PC for what it was made for, putting a block in every one of the 16,900 possible spots and see if it gets caught in a loop.

    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) throws FileNotFoundException {
        // Figure out how many places the guard will visit on their journey.
        // Input the grid as we have for previous days.
        Scanner input = new Scanner(new File(".\\Day_6\\input.txt"));
        System.out.println(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
        String[] original_grid = new String[130];
        int count = 0;
        int guard_start_row = 0;
        int guard_start_column = 0;
        while (input.hasNextLine()) {
            original_grid[count] = input.nextLine();

            // Find the position of the guard while we're at it.
            if (original_grid[count].contains("^")) {
                guard_start_row = count;
                guard_start_column = original_grid[guard_start_row].indexOf('^');
            }

            count++;
        }

        // Now we have the full grid and the guard starting position let's fill with the path.
        String guard_direction; // "up", "right", "down", "left".

        // Now just go through and do it, stop counting when guard reaches back to start position facing up.
        int count_valid = 0;
        for (int i = 0; i < 130; i++) {
            for (int j = 0; j < 130; j++) {
                String[] grid = original_grid.clone();
                char[] row = grid[i].toCharArray();
                row[j] = '#';
                grid[i] = new String(row);

                int guard_current_row = guard_start_row;
                int guard_current_column = guard_start_column;
                guard_direction = "up";
                ArrayList<String> guard_positions = new ArrayList<>();
                while (true) { 
                    int guard_next_row = guard_current_row;
                    int guard_next_column = guard_current_column;
                    switch (guard_direction) {
                        case "up" -> {
                            guard_next_row -= 1;
                        }
                        case "right" -> {
                            guard_next_column += 1;
                        }
                        case "down" -> {
                            guard_next_row += 1;
                        }
                        case "left" -> {
                            guard_next_column -= 1;
                        }
                    }

                    // Check for obstructions.
                    boolean obstructed = false;
                    if ((guard_next_row < 0 || guard_next_row >= grid.length) || (guard_next_column < 0 || guard_next_column >= grid[guard_current_row].length())) {
                        // If the guard's next position is to leave the grid.
                        // Not a valid obstruction.
                        break;
                    } else if (grid[guard_next_row].charAt(guard_next_column) == '#') {
                        obstructed = true;
                    }

                    if (obstructed) {
                        // Change direction without updating current position.
                        switch (guard_direction) {
                            case "up" -> {
                                guard_direction = "right";
                            }
                            case "right" -> {
                                guard_direction = "down";
                            }
                            case "down" -> {
                                guard_direction = "left";
                            }
                            case "left" -> {
                                guard_direction = "up";
                            }
                        }
                    } else {
                        // Update current position, adding the previous position in "row,column" format to the arraylist.
                        // Check if we are a place we have been facing a direction we've already done.
                        if (guard_positions.contains(guard_current_row + "," + guard_current_column + "," + guard_direction)) {
                            // Valid obstruction.
                            count_valid++;
                            break;
                        }
                        guard_positions.add(guard_current_row + "," + guard_current_column + "," + guard_direction);
                        guard_current_row = guard_next_row;
                        guard_current_column = guard_next_column;
                    }
                }
            }
        }

        // All we need is the number of unique positions, so just the count.
        System.out.println(count_valid);
        System.out.println(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));

        input.close();
    }
}
