package bowling.domain;

import bowling.domain.score.Pins;

import java.util.Random;

/**
 * Created : 2020-12-17 오후 12:40
 * Developer : Seo
 */
public class Bowling {
    private static final Random random = new Random();
    public static final int FOR_RANDOM_RANGE = 1;

    public static Pins stroke(Pins leftPins) {
        return new Pins(random.nextInt(leftPins.get() + FOR_RANDOM_RANGE));
    }

    private Bowling() {
    }
}
