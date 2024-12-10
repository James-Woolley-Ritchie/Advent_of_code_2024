package Day_6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Part_1 {
    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) throws FileNotFoundException {
        // Figure out how many places the guard will visit on their journey.
        // Input the grid as we have for previous days.
        Scanner input = new Scanner(new File(".\\Day_6\\input.txt"));
        String[] grid = new String[130];
        int count = 0;
        int guard_start_row = 0;
        int guard_start_column = 0;
        while (input.hasNextLine()) {
            grid[count] = input.nextLine();

            // Find the position of the guard while we're at it.
            if (grid[count].contains("^")) {
                guard_start_row = count;
                guard_start_column = grid[guard_start_row].indexOf('^');
            }

            count++;
        }

        // Now we have the full grid and the guard starting position let's fill with the path.
        String guard_direction = "up"; // "up", "right", "down", "left".
        ArrayList<String> guard_positions = new ArrayList<>();

        // Now just go through and do it, stop counting when guard reaches back to start position facing up.
        int guard_current_row = guard_start_row;
        int guard_current_column = guard_start_column;
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
                guard_positions.add(guard_current_row + "," + guard_current_column);
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
                guard_positions.add(guard_current_row + "," + guard_current_column);
                guard_current_row = guard_next_row;
                guard_current_column = guard_next_column;
            }

            // Finally, check if we are back to square one again.
            if (guard_current_row == guard_start_row && guard_current_column == guard_start_column && guard_direction.equals("up")) {
                break;
            }
        }

        // Now we have all of the positions that the guard crosses, there will be some duplicates, so dedupe the arraylist.
        Set<String> temp_set = new HashSet<>(guard_positions);
        guard_positions.clear();
        guard_positions.addAll(temp_set);

        // All we need is the number of unique positions, so just the count.
        System.out.println(guard_positions.size());

        input.close();
    }
}
