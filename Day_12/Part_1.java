package Day_12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Part_1 {

    private static final ArrayList<ArrayList<String>> grid = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        // Read in the input.
        Scanner input = new Scanner(new File(".\\Day_12\\input.txt"));

        while (input.hasNextLine()) {
            String line = input.nextLine();
            ArrayList<String> row = new ArrayList<>();
            for (int i = 0; i < line.length(); i++) {
                row.add("" + line.charAt(i));
            }
            grid.add(row);
        }

        // Go through the input and map out each region of each letter with letter + region_count?
        ArrayList<String> mapped = new ArrayList<>();
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                String current_plant = grid.get(i).get(j);
                if (mapped.contains(current_plant)) {
                    continue;
                }

                int count = 1;
                while (mapped.contains(current_plant + count)) {
                    count++;
                }
                String plant_id = current_plant + count;

                map_region(i, j, current_plant, plant_id);
                mapped.add(plant_id);
            }
        }

        // Now we have the input get a count of each plant type (area).
        Map<String, Integer> plant_count = new HashMap<>();
        for (ArrayList<String> row : grid) {
            for (int i = 0; i < row.size(); i++) {
                String plant = row.get(i);
                if (plant_count.containsKey(plant)) {
                    plant_count.put(plant, plant_count.get(plant) + 1);
                } else {
                    plant_count.put(plant, 1);
                }
            }
        }

        // Now go through each plant and find the perimeter of the region.
        Map<String, Integer> plant_perimeters = new HashMap<>();
        for (String plant : plant_count.keySet()) {
            int perimeter = 0;
            for (int i = 0; i < grid.size(); i++) {
                for (int j = 0; j < grid.get(i).size(); j++) {
                    if (grid.get(i).get(j) == plant) {
                        try {
                            if (grid.get(i - 1).get(j) != plant) { // up
                                perimeter++;
                            }
                        } catch (IndexOutOfBoundsException index_out_of_bounds_exception) {
                            perimeter++;
                        }

                        try {
                            if (grid.get(i).get(j + 1) != plant) { // right
                                perimeter++;
                            }
                        } catch (IndexOutOfBoundsException index_out_of_bounds_exception) {
                            perimeter++;
                        }

                        try {
                            if (grid.get(i + 1).get(j) != plant) { // down
                                perimeter++;
                            }
                        } catch (IndexOutOfBoundsException index_out_of_bounds_exception) {
                            perimeter++;
                        }

                        try {
                            if (grid.get(i).get(j - 1) != plant) { // left
                                perimeter++;
                            }
                        } catch (IndexOutOfBoundsException index_out_of_bounds_exception) {
                            perimeter++;
                        }
                    }
                }
            }

            if (plant_perimeters.containsKey(plant)) {
                plant_perimeters.put(plant, plant_perimeters.get(plant) + perimeter);
            } else {
                plant_perimeters.put(plant, perimeter);
            }
        }


        // Output.
        int total = 0;
        for (String plant : plant_count.keySet()) {
            // System.out.println("Plant " + plant + " > area = " + plant_count.get(plant) + ", perimeter = " + plant_perimeters.get(plant));
            total += plant_count.get(plant) * plant_perimeters.get(plant);
        }

        System.out.println(total);

        input.close();
    }

    private static void map_region(int row, int column, String current_plant, String plant_id) {
        grid.get(row).set(column, plant_id);
        try {
            if (grid.get(row - 1).get(column).equals(current_plant)) { // Up.   
                map_region(row - 1, column, current_plant, plant_id);
            }
        } catch (IndexOutOfBoundsException ignore) {}

        try {
            if (grid.get(row).get(column + 1).equals(current_plant)) { // Right.   
                map_region(row, column + 1, current_plant, plant_id);
            }
        } catch (IndexOutOfBoundsException ignore) {}

        try {
            if (grid.get(row + 1).get(column).equals(current_plant)) { // Down.   
                map_region(row + 1, column, current_plant, plant_id);
            }
        } catch (IndexOutOfBoundsException ignore) {}

        try {
            if (grid.get(row).get(column - 1).equals(current_plant)) { // Left.   
                map_region(row, column - 1, current_plant, plant_id);
            }
        } catch (IndexOutOfBoundsException ignore) {}
    }
}
