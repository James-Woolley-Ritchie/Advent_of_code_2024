package Day_8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part_2 {
    public static void main(String[] args) throws FileNotFoundException {
        // So this is a bit messy but if we get a list of each of the characters in the list and their positions then we can figure out the antinode locations pretty easily just with gradients.
        // It's just coordinates pretty much.
        Scanner input = new Scanner(new File(".\\Day_8\\input.txt"));
        ArrayList<Character> frequencies = new ArrayList<>();
        ArrayList<String> grid = new ArrayList<>();
        while (input.hasNextLine()) {
            String line = input.nextLine();
            for (char character : line.toCharArray()) {
                if ("abcdefghijklmnopqrstuvwxyz0123456789".contains(Character.toString(character).toLowerCase())) {
                    if (!frequencies.contains(character)) {
                        frequencies.add(character);
                    }
                }
            }
            grid.add(line);
        }

        // Now we've got a list of all of the unique nodes just go through this adding to the list of antinode locations.
        ArrayList<String> antinode_locations = new ArrayList<>(); // "row,column"
        for (Character character : frequencies) {
            ArrayList<String> node_locations = new ArrayList<>();
            for (int i = 0; i < grid.size(); i++) {
                for (int j = 0; j < grid.get(i).length(); j++) {
                    if (grid.get(i).charAt(j) == character) {
                        node_locations.add(j + "," + i);
                    }
                }
            } 

            // Now we have the node locations, find the two antinode positions for each node pair.
            for (int i = 0; i < node_locations.size(); i++) {
                for (int j = 0; j < node_locations.size(); j++) {
                    if (i != j) {
                        // Now there can be more than two antinodes for each node pair, find the distance between each pair and go along the gradient until off the grid.
                        int node_1_x = Integer.parseInt(node_locations.get(i).split(",")[0]);
                        int node_1_y = Integer.parseInt(node_locations.get(i).split(",")[1]);
                        int node_2_x = Integer.parseInt(node_locations.get(j).split(",")[0]);
                        int node_2_y = Integer.parseInt(node_locations.get(j).split(",")[1]);
                        boolean antinode_1_off_grid = false;
                        boolean antinode_2_off_grid = false;
                        int count = 0;
                        while (!(antinode_1_off_grid && antinode_2_off_grid)) {
                            int antinode_1_x = node_1_x - (count * ((node_2_x - node_1_x)));
                            int antinode_1_y = node_1_y - (count * ((node_2_y - node_1_y)));
                            int antinode_2_x = node_2_x - (count * ((node_1_x - node_2_x)));
                            int antinode_2_y = node_2_y - (count * ((node_1_y - node_2_y)));

                            // Check if the antinodes are off the grid.
                            if (!(antinode_1_x < 0 || antinode_1_x >= grid.get(0).length() || antinode_1_y < 0 || antinode_1_y >= grid.size())) {
                                String antinode_1_location = antinode_1_x + "," + antinode_1_y;
                                if (!antinode_locations.contains(antinode_1_location)) {
                                    antinode_locations.add(antinode_1_location);
                                }
                            } else {
                                antinode_1_off_grid = true;
                            }

                            if (!(antinode_2_x < 0 || antinode_2_x >= grid.get(0).length() || antinode_2_y < 0 || antinode_2_y >= grid.size())) {
                                String antinode_2_location = antinode_2_x + "," + antinode_2_y;
                                if (!antinode_locations.contains(antinode_2_location)) {
                                    antinode_locations.add(antinode_2_location);
                                }
                            } else {
                                antinode_2_off_grid = true;
                            }
                            count++;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < grid.size(); i++) {
            String row = "";
            for (int j = 0; j < grid.get(i).length(); j++) {
                if (antinode_locations.contains(j + "," + i)) {
                    row += "#";
                } else {
                    row += grid.get(i).charAt(j);
                }
            }

            System.out.println(row);
        }

        System.out.println(antinode_locations.size());

        input.close();
    }
}
