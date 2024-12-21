package Day_5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part_2 {
    public static void main(String[] args) throws FileNotFoundException {
        // This is not nice at all. Sounds fun!
        Scanner input = new Scanner(new File(".\\Day_5\\input.txt"));
        int total = 0;

        // Get a list of the print rules and one of the print updates.
        ArrayList<String> print_rules = new ArrayList<>();
        ArrayList<ArrayList<String>> print_updates = new ArrayList<>(); 
        boolean rules = true;

        while (input.hasNextLine()) {    
            String line = input.nextLine();
            if (line.isBlank()) {
                rules = false;
                line = input.nextLine();
            }

            if (rules) {
                print_rules.add(line);
            } else {
                ArrayList<String> print_update = new ArrayList<>();
                for (String page_number : line.split(",")) {
                    print_update.add(page_number);
                }
                print_updates.add(print_update);
            }
        }

        // Check each update to see if it's in the right order.
        boolean good_update = true;
        for (ArrayList<String> print_update : print_updates) {
            good_update = true;
            for (String print_rule : print_rules) {
                if (print_update.contains(print_rule.split("\\|")[0]) && print_update.contains(print_rule.split("\\|")[1])) {
                    if (print_update.indexOf(print_rule.split("\\|")[0]) > print_update.indexOf(print_rule.split("\\|")[1])) {
                        good_update = false;
                        break;
                    }
                }
            }
            if (!good_update) {
                // Now we want to handle the bad updates.
                // Go through the list of rules and correct the update. (be lazy and swap any incorrect rules until they are correct)
                while (good_update == false) {
                    good_update = true;
                    for (String print_rule : print_rules) {
                        if (print_update.contains(print_rule.split("\\|")[0]) && print_update.contains(print_rule.split("\\|")[1])) {
                            if (print_update.indexOf(print_rule.split("\\|")[0]) > print_update.indexOf(print_rule.split("\\|")[1])) {
                                print_update = swap(print_update, print_rule.split("\\|")[0], print_rule.split("\\|")[1]);
                                good_update = false;
                                break;
                            }
                        }
                    }
                }

                total += Integer.valueOf(print_update.get((int) Math.ceil(print_update.size() / 2)));
            }
        }

        input.close();
        System.out.println(total);
    }

    @SuppressWarnings("unchecked")
    private static ArrayList<String> swap(ArrayList<String> list, String number_1, String number_2) {
        ArrayList<String> swapped_list = (ArrayList<String>) list.clone();
        swapped_list.set(list.indexOf(number_1), number_2);
        swapped_list.set(list.indexOf(number_2), number_1);
        return swapped_list;
    }
}
