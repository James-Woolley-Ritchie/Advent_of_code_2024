package Day_11;

import java.util.ArrayList;

public class Part_1 {
    public static void main(String[] args) {
        long start_time = System.nanoTime();
        ArrayList<Long> stones = new ArrayList<>();
        stones.add(1L);
        stones.add(24596L);
        stones.add(0L);
        stones.add(740994L);
        stones.add(60L);
        stones.add(803L);
        stones.add(8918L);
        stones.add(9405859L);
        for (int i = 0; i < 25; i++) {
            stones = blink(stones);
        }
        
        System.out.println(stones.size());
        long end_time = System.nanoTime();
        System.out.println((end_time - start_time) / 1000000);
    }

    private static ArrayList<Long> blink(ArrayList<Long> stones) {
        ArrayList<Long> new_stones = new ArrayList<>();
        
        for (Long stone : stones) {
            // Rule 1, if the stone is 0 then it becomes 1.
            if (stone == 0) {
                new_stones.add(1L);
            } 

            // Rule 2, if the stone has an even number of digits then it's split in half.
            else if (Long.toString(stone).length() % 2 == 0) {
                new_stones.add(Long.valueOf(Long.toString(stone).substring(0, Long.toString(stone).length() / 2)));
                new_stones.add(Long.valueOf(Long.toString(stone).substring((Long.toString(stone).length() / 2), Long.toString(stone).length())));
            }

            // Rule 3, if no other rules apply then multiply the value by 2024.
            else {
                new_stones.add(stone * 2024);
            }
        }

        return new_stones;
    }
}
