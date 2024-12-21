package Day_13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part_1 {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(".\\Day_13\\input.txt"))) {
            int a_x = 0;
            int a_y = 0;
            int b_x = 0;
            int b_y = 0;
            int prize_x = 0;
            int prize_y = 0;
            
            int total_tokens = 0;
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line.contains("A")) {
                    a_x = Integer.parseInt(line.split(",")[0].replace("Button A: X", ""));
                    a_y = Integer.parseInt(line.split(",")[1].replace(" Y", ""));
                } else if (line.contains("B")) {
                    b_x = Integer.parseInt(line.split(",")[0].replace("Button B: X", ""));
                    b_y = Integer.parseInt(line.split(",")[1].replace(" Y", ""));
                } else if (line.contains("Prize")) {
                    prize_x = Integer.parseInt(line.split(",")[0].replace("Prize: X=", ""));
                    prize_y = Integer.parseInt(line.split(",")[1].replace(" Y=", ""));
                } else {
                    // Try decrementing numbers of B presses until you get a solution unless there isn't one (e.g. all the way from all B to all A doesn't work)
                    // Find the max number of B presses that fit into the prize x and y.
                    int max_number_of_b = Math.min((int) Math.floor(prize_x / b_x), (int) Math.floor(prize_y / b_y));
                    int actual_number_of_a = 0;
                    int actual_number_of_b = 0;
                    for (int i = max_number_of_b; i >= 0 ; i--) {
                        if ((prize_x - (i * b_x)) % a_x == 0 && (prize_y - (i * b_y)) % a_y == 0) {
                            if ((prize_x - (i * b_x)) / a_x == (prize_y - (i * b_y)) / a_y) {
                                actual_number_of_a = (prize_x - (i * b_x)) / a_x;
                                actual_number_of_b = i;
                            }
                        }
                    }

                    total_tokens += (actual_number_of_a * 3) + actual_number_of_b;
                }
            }
            System.out.println(total_tokens);
        }
    }
}