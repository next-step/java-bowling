package bowling.domain;

import java.util.Random;

/**
 * Created : 2020-12-17 오후 12:40
 * Developer : Seo
 */
public class Bowling {
    private static final Random random = new Random();

    public static int stroke(int leftPins) {
        return random.nextInt(leftPins + 1);
    }

    private Bowling() {
    }
}
