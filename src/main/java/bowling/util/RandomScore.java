package bowling.util;

import java.util.Random;

public class RandomScore {
    private static final Random rd = new Random();

    public Integer generate(Integer range) {
        return rd.nextInt(range) + 1;
    }
}
