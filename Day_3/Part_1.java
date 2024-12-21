package Day_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Part_1 {
    public static void main(String[] args) throws FileNotFoundException {
        int total = 0;

        Scanner input = new Scanner(new File(".\\Day_3\\input.txt"));
        while (input.hasNextLine()) {
            Pattern pattern = Pattern.compile("mul\\((-?\\d+),(-?\\d+)\\)");
            String[] matches = pattern.matcher(input.nextLine()).results().map(MatchResult::group).toArray(String[]::new);
            
            for (String match : matches) {
                match = match.replaceAll("mul", "");
                match = match.replaceAll("\\(", "");
                match = match.replaceAll("\\)", "");

                int num_1 = Integer.valueOf(match.split(",")[0]);
                int num_2 = Integer.valueOf(match.split(",")[1]);

                total += (num_1 * num_2);
            }
        }

        input.close();

        System.out.println(total);
    }
}
