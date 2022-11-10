package bowling.domain;

import java.util.Random;

public class RandomStrategy implements BowlingStrategy {
    @Override
    public int pitchingBall(int remainPin) {
        Random random = new Random();
        return random.nextInt(remainPin + 1);
    }
}
