package bowling.utils;

import java.util.Random;

public class RandomIntNumberUtil {
    private static final Random random = new Random();

    public static int nextInt() {
        return random.nextInt();
    }

    public static int nextInt(int bound) {
        return random.nextInt(bound);
    }
}
