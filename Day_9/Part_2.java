package Day_9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part_2 {
    // Now we are only moving whole files rather than single fragments. Shouldn't be too hard to adapt.
    public static void main(String[] args) throws FileNotFoundException {
        // This is interesting, surely it's just a single array?
        // Input is only one line.
        Scanner file = new Scanner(new File(".\\Day_9\\input.txt"));
        String input = file.nextLine();
        file.close();
        ArrayList<Integer> disk_map = new ArrayList<>();
        ArrayList<Integer> file_lengths = new ArrayList<>();
        ArrayList<Integer> file_positions = new ArrayList<>();
        ArrayList<Integer> gap_lengths = new ArrayList<>();
        ArrayList<Integer> gap_positions = new ArrayList<>();
        int id = 0;
        for (int i = 0; i < input.length(); i++) {
            if (i % 2 == 0) {
                file_positions.add(disk_map.size());
                for (int j = 0; j < Integer.parseInt(Character.toString(input.charAt(i))); j++) {
                    disk_map.add(id);
                }
                file_lengths.add(Integer.parseInt(Character.toString(input.charAt(i))));
                id++;
            } else {
                gap_positions.add(disk_map.size());
                for (int j = 0; j < Integer.parseInt(Character.toString(input.charAt(i))); j++) {
                    disk_map.add(null);
                }
                gap_lengths.add(Integer.parseInt(Character.toString(input.charAt(i))));
            }
        }

        // Now here it would be useful to have a list of all of the file lengths and the gap lengths...
        // Let's go back and fix that! Got it now.
        for (int i = file_lengths.size() - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (file_lengths.get(i) <= gap_lengths.get(j)) {
                    // File fits!
                    for (int k = 0; k < file_lengths.get(i); k++) {
                        disk_map.set(gap_positions.get(j) + k, i);
                        disk_map.set(file_positions.get(i) + k, null);
                    }

                    gap_lengths.set(j, gap_lengths.get(j) - file_lengths.get(i));
                    gap_positions.set(j, gap_positions.get(j) + file_lengths.get(i));
                    break;
                }
            }
        }

        // Now the disk is correctly formatted.
        long total = 0;
        for (int i = 0; i < disk_map.size(); i++) {
            if (disk_map.get(i) == null) {
                continue;
            } else {
                total += (i * disk_map.get(i));
            }
        }

        System.out.println(total);
    }
}
