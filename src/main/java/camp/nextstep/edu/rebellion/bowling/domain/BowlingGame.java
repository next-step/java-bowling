package camp.nextstep.edu.rebellion.bowling.domain;

import camp.nextstep.edu.rebellion.bowling.domain.frame.Frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BowlingGame {
    private final int MAX_ROUNDS = 10;
    private final int FINAL_ROUND = MAX_ROUNDS - 1;

    private final Player player;
    private final List<Frame> frames;
    private int currentRound;

    private BowlingGame(Player player) {
        this.player = player;
        this.frames = setup();
    }

    private List<Frame> setup() {
        List<Frame> frames = new ArrayList<>();
        for (int i = 0; i < MAX_ROUNDS; i++) {
            frames.add(Frame.ready());
        }
        return frames;
    }

    public static BowlingGame start(Player player) {
        return new BowlingGame(player);
    }

    public void record(int hits) {
        Frame current = frames.get(currentRound);
        current.markScore(hits);

        if (current.meetEnd()) {
            currentRound++;
        }
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

    public ScoreBoard getScoreBoard() {
        return new ScoreBoard(player, Collections.unmodifiableList(frames));
    }
}
