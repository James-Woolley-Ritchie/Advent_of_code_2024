package Day_10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part_1 {

    private static final ArrayList<String> grid = new ArrayList<>();

    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) throws FileNotFoundException {
        // Topographic map.
        // Figure out how many 9s can be reached by only travelling north, south, east, and west (going one step up each time)
        // Recursion?

        // Read the input first things first.
        Scanner input = new Scanner(new File(".\\Day_10\\input.txt"));
        while (input.hasNextLine()) {
            grid.add(input.nextLine());
        }

        // Now we have the input go through it until we find a 0.
        int total_number_of_trails = 0;
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).length(); j++) {
                if (grid.get(i).charAt(j) == '0') {
                    total_number_of_trails += find_number_of_peaks(i, j, 0, new ArrayList<>()).size();
                }
            }
        }

        System.out.println(total_number_of_trails);

        input.close();
    }

    private static ArrayList<String> find_number_of_peaks(int row, int column, int step, ArrayList<String> positions) {
        if (step == 9) {
            if (!positions.contains(row + "," + column)) {
                positions.add(row + "," + column);
            }
        }

        // North
        try {
            if (grid.get(row - 1).charAt(column) == Integer.toString(step + 1).charAt(0)) {
                // This is a valid next step.
                positions = find_number_of_peaks(row - 1, column, step + 1, positions);
            }
        } catch (IndexOutOfBoundsException index_out_of_bounds_exception) {}

        // East
        try {
            if (grid.get(row).charAt(column + 1) == Integer.toString(step + 1).charAt(0)) {
                // This is a valid next step.
                positions = find_number_of_peaks(row, column + 1, step + 1, positions);
            }
        } catch (IndexOutOfBoundsException index_out_of_bounds_exception) {}

        // South
        try {
            if (grid.get(row + 1).charAt(column) == Integer.toString(step + 1).charAt(0)) {
                // This is a valid next step.
                positions = find_number_of_peaks(row + 1, column, step + 1, positions);
            }
        } catch (IndexOutOfBoundsException index_out_of_bounds_exception) {}

        // West
        try {
            if (grid.get(row).charAt(column - 1) == Integer.toString(step + 1).charAt(0)) {
                // This is a valid next step.
                positions = find_number_of_peaks(row, column - 1, step + 1, positions);
            }
        } catch (IndexOutOfBoundsException index_out_of_bounds_exception) {}

        return positions;
    }
}
