package Day_9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part_1 {
    public static void main(String[] args) throws FileNotFoundException {
        // This is interesting, surely it's just a single array?
        // Input is only one line.
        Scanner file = new Scanner(new File(".\\Day_9\\input.txt"));
        String input = file.nextLine();
        file.close();
        ArrayList<Integer> disk_map = new ArrayList<>();
        int id = 0;
        for (int i = 0; i < input.length(); i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < Integer.parseInt(Character.toString(input.charAt(i))); j++) {
                    disk_map.add(id);
                }
                id++;
            } else {
                for (int j = 0; j < Integer.parseInt(Character.toString(input.charAt(i))); j++) {
                    disk_map.add(null);
                }
            }
        }

        // Now we can iterate through the disk map and move from the end to the first null.
        for (int i = disk_map.size() - 1; i >= 0; i--) {
            if (disk_map.get(i) != null) {
                for (int j = 0; j < i; j++) {
                    if (disk_map.get(j) == null) {
                        disk_map.set(j, disk_map.get(i));
                        disk_map.set(i, null);
                        break;
                    }
                }
            } 
        }

        // Now the disk is correctly formatted.
        long total = 0;
        for (int i = 0; i < disk_map.size(); i++) {
            if (disk_map.get(i) == null) {
                break;
            } else {
                total += (i * disk_map.get(i));
            }
        }

        System.out.println(total);
    }
}
