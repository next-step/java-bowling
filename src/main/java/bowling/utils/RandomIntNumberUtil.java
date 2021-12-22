package bowling.utils;

import java.util.Random;

public class RandomIntNumberUtil {
    private static final Random random = new Random();

    private RandomIntNumberUtil() {
    }

    public static int nextInt(int bound) {
        return random.nextInt(bound);
    }
}
