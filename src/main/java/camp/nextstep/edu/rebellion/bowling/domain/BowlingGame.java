package camp.nextstep.edu.rebellion.bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BowlingGame {
    private final int MAX_ROUNDS = 10;
    private final int FINAL_ROUND = MAX_ROUNDS - 1;

    private final List<Frame> frames;
    private int currentRound;

    private BowlingGame() {
        frames = new ArrayList<>();
        for (int i = 0; i < MAX_ROUNDS; i++) {
            frames.add(Frame.ready());
        }
    }

    public static BowlingGame start() {
        return new BowlingGame();
    }

    public void record(int hits) {
        Frame current = frames.get(currentRound);
        current.markScore(hits);

        if (current.meetEnd()) {
            currentRound++;
        }

        System.out.println("[Record END] " + currentRound);
    }

    public int currentRound() {
        return this.currentRound + 1;
    }

    public boolean hasNext() {
        return MAX_ROUNDS > currentRound || hasBonusChance();
    }

    private boolean hasBonusChance() {
        if (MAX_ROUNDS == currentRound &&
                frames.get(FINAL_ROUND).isStrike()) {
            frames.add(Frame.readyForBonus());
            return true;
        }

        return false;
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }
}
