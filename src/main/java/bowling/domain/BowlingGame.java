package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {
    private static final int NUMBERS_OF_NORMAL_FRAME = 9;

    private List<Frame> bowlingGame;

    public BowlingGame() {
        initial();
    }

    private void initial() {
        bowlingGame =  new ArrayList<>();
        for (int frameIndex = 1; frameIndex <= NUMBERS_OF_NORMAL_FRAME; frameIndex++) {
            bowlingGame.add(new NormalFrame());
        }
        bowlingGame.add(new FinalFrame());
    }

    public Frame frame(int frameIndex) {
        return bowlingGame.get(frameIndex);
    }

    public List<Frame> getBowlingGame() {
        return bowlingGame;
    }
}
