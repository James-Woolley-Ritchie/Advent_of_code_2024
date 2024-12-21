package Day_13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part_2 {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(".\\Day_13\\input.txt"))) {
            long a_x = 0L;
            long a_y = 0L;
            long b_x = 0L;
            long b_y = 0L;
            long prize_x;
            long prize_y;
            
            long total_tokens = 0;
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line.contains("A")) {
                    a_x = Long.parseLong(line.split(",")[0].replace("Button A: X", ""));
                    a_y = Long.parseLong(line.split(",")[1].replace(" Y", ""));
                } else if (line.contains("B")) {
                    b_x = Long.parseLong(line.split(",")[0].replace("Button B: X", ""));
                    b_y = Long.parseLong(line.split(",")[1].replace(" Y", ""));
                } else if (line.contains("Prize")) {
                    prize_x = 10000000000000L + Long.parseLong(line.split(",")[0].replace("Prize: X=", ""));
                    prize_y = 10000000000000L + Long.parseLong(line.split(",")[1].replace(" Y=", ""));

                    // Try and do it the maths way.
                    double number_of_b = (prize_y - (prize_x * (a_y / (double) a_x))) / (double) (b_y - (b_x * (a_y / (double) a_x)));
                    double number_of_a = (prize_x - (number_of_b * b_x)) / (double) a_x;

                    if ((Math.round(number_of_a) * a_x) + (Math.round(number_of_b) * b_x) == prize_x && (Math.round(number_of_a) * a_y) + (Math.round(number_of_b) * b_y) == prize_y) { 
                        total_tokens += number_of_b + (number_of_a * 3);
                    }
                }
            }
            System.out.println(total_tokens);
        }
    }
}