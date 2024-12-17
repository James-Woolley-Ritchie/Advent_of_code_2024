package Day_11;

import java.util.HashMap;
import java.util.Map;

public class Part_2 {

    private static Map<Long, Long> counter = new HashMap<>();
    private static Map<Long, Long> temp_counter = new HashMap<>();

    public static void main(String[] args) {
        long start_time = System.nanoTime();
        counter.put(1L, 1L);
        counter.put(24596L, 1L);
        counter.put(0L, 1L);
        counter.put(740994L, 1L);
        counter.put(60L, 1L);
        counter.put(803L, 1L);
        counter.put(8918L, 1L);
        counter.put(9405859L, 1L);
        

        for (int i = 0; i < 75; i++) {
            temp_counter = new HashMap<>();
            temp_counter.putAll(counter);

            counter = new HashMap<>();
            for (Long key : temp_counter.keySet()) {
                blink(key);
            }
    
        }

        Long total = 0L;
        for (Long key : counter.keySet()) {
            total += counter.get(key);
        }

        System.out.println(total);
        long end_time = System.nanoTime();
        System.out.println((end_time - start_time) / 1000000);
    }

    private static void blink(Long stone) {
        // Rule 1, if the stone is 0 then it becomes 1.
        if (stone == 0L) {
            try {
                counter.put(1L, counter.get(1L) + temp_counter.get(stone)); 
            } catch (Exception exception) {
                counter.put(1L, temp_counter.get(stone)); 
            }
        } 

        // Rule 2, if the stone has an even number of digits then it's split in half.
        else if (Long.toString(stone).length() % 2 == 0) {
            Long key = Long.valueOf(Long.toString(stone).substring(0, Long.toString(stone).length() / 2));
            try {
                counter.put(key, counter.get(key) + temp_counter.get(stone)); 
            } catch (Exception exception) {
                counter.put(key, temp_counter.get(stone)); 
            }

            key = Long.valueOf(Long.toString(stone).substring((Long.toString(stone).length() / 2), Long.toString(stone).length()));
            try {
                counter.put(key, counter.get(key) + temp_counter.get(stone)); 
            } catch (Exception exception) {
                counter.put(key, temp_counter.get(stone)); 
            }
        }

        // Rule 3, if no other rules apply then multiply the value by 2024.
        else {
            Long key = stone * 2024;
            try {
                counter.put(key, counter.get(key) + temp_counter.get(stone)); 
            } catch (Exception exception) {
                counter.put(key, temp_counter.get(stone)); 
            }
        }
    }


}
