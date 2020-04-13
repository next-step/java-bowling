package bowling.domain;

import java.util.Random;

public class PlayBowling {
    private static PlayBowling playBowling = new PlayBowling();
    private Random random = new Random();

    public int generateBowlingScore(int rangePin) {
        validatePinRange(rangePin);
        return random.nextInt(rangePin);
    }

    public void validatePinRange(int rangePin) {
        if (rangePin > Rule.MAX_PINS.getValue()) {
            throw new IllegalArgumentException("발생 할 수 있는 스코어는 10을 넘을 수 없습니다.");
        }

        if (rangePin < Rule.MIN_PINS.getValue()) {
            throw new IllegalArgumentException("발생 할 수 있는 스코어는 0미만이 될 수 없습니다.");
        }
    }

    public static PlayBowling getPlayBowling() {
        return playBowling;
    }
}
