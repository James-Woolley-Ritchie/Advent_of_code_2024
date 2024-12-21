package Day_13;

public class Part_1 {
    public static void main(String[] args) {
        // Try decrementing numbers of B presses until you get a solution unless there isn't one (e.g. all the way from all B to all A doesn't work)
        int a_x = 94;
        int a_y = 34;
        int b_x = 22;
        int b_y = 67;
        int prize_x = 8400;
        int prize_y = 5400;
        
        // Find the max number of B presses that fit into the prize x and y.
        int max_number_of_b = Math.min((int) Math.floor(prize_x / Math.min(a_x, b_x)), (int) Math.floor(prize_y / Math.min(a_y, b_y)));
        int actual_number_of_a = -1;
        int actual_number_of_b = -1;
        for (int i = max_number_of_b; i >= 0 ; i--) {
            if (prize_x == (b_x * i) + (a_x * (max_number_of_b - i)) && prize_y == b_y * max_number_of_b + (a_y * (max_number_of_b - i))) {
                actual_number_of_a = max_number_of_b - i;
                actual_number_of_b = i;
            }
        }

        int number_of_tokens = (actual_number_of_a * 3) + actual_number_of_b;
        System.out.println(number_of_tokens);
    }
}
