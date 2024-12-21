package Day_12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Part_2 {

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
        Map<String, Integer> plant_sides = new HashMap<>();
        for (String plant : plant_count.keySet()) {
            ArrayList<String> top_perimeters = new ArrayList<>();
            ArrayList<String> right_perimeters = new ArrayList<>();
            ArrayList<String> left_perimeters = new ArrayList<>();
            ArrayList<String> bottom_perimeters = new ArrayList<>(); 
            for (int i = 0; i < grid.size(); i++) {
                for (int j = 0; j < grid.get(i).size(); j++) {
                    if (grid.get(i).get(j) == plant) {
                        try {
                            if (grid.get(i - 1).get(j) != plant) { // up
                                top_perimeters.add(i + "," + j);
                            }
                        } catch (IndexOutOfBoundsException index_out_of_bounds_exception) {
                            top_perimeters.add(i + "," + j);
                        }

                        try {
                            if (grid.get(i).get(j + 1) != plant) { // right
                                right_perimeters.add(i + "," + j);
                            }
                        } catch (IndexOutOfBoundsException index_out_of_bounds_exception) {
                            right_perimeters.add(i + "," + j);
                        }

                        try {
                            if (grid.get(i + 1).get(j) != plant) { // down
                                bottom_perimeters.add(i + "," + j);
                            }
                        } catch (IndexOutOfBoundsException index_out_of_bounds_exception) {
                            bottom_perimeters.add(i + "," + j);
                        }

                        try {
                            if (grid.get(i).get(j - 1) != plant) { // left
                                left_perimeters.add(i + "," + j);
                            }
                        } catch (IndexOutOfBoundsException index_out_of_bounds_exception) {
                            left_perimeters.add(i + "," + j);
                        }
                    }
                }
            }

            int sides = 0;
            for (String top_perimeter : top_perimeters) {
                if (!top_perimeters.contains(top_perimeter.split(",")[0] + "," + (Integer.parseInt(top_perimeter.split(",")[1]) + 1))) {
                    sides ++;
                }
            }

            for (String right_perimeter : right_perimeters) {
                if (!right_perimeters.contains((Integer.parseInt(right_perimeter.split(",")[0]) - 1) + "," + right_perimeter.split(",")[1])) {
                    sides ++;
                }
            }

            for (String bottom_perimeter : bottom_perimeters) {
                if (!bottom_perimeters.contains(bottom_perimeter.split(",")[0] + "," + (Integer.parseInt(bottom_perimeter.split(",")[1]) + 1))) {
                    sides ++;
                }
            }

            for (String left_perimeter : left_perimeters) {
                if (!left_perimeters.contains((Integer.parseInt(left_perimeter.split(",")[0]) - 1) + "," + left_perimeter.split(",")[1])) {
                    sides ++;
                }
            }

            if (plant_sides.containsKey(plant)) {
                plant_sides.put(plant, plant_sides.get(plant) + sides);
            } else {
                plant_sides.put(plant, sides);
            }
        }


        // Output.
        int total = 0;
        for (String plant : plant_count.keySet()) {
            System.out.println("Plant " + plant + " > area = " + plant_count.get(plant) + ", sides = " + plant_sides.get(plant));
            total += plant_count.get(plant) * plant_sides.get(plant);
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
