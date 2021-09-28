package bowling.model;

import bowling.CannotBowlException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BowlingGame {
    private static final int LAST_FRAME_NO = 11;

    private int frameNo;
    private List<Round> rounds;

    public BowlingGame() {
        this.frameNo = 0;
        this.rounds = new ArrayList<>();
        this.rounds.add(new NormalRound());
    }

    public BowlingGame(int frameNo, List<Round> rounds) {
        this.frameNo = frameNo;
        this.rounds = rounds;
    }

    public State bowl(int countOfPin) throws CannotBowlException {
        State state = currentFrame().bowl(countOfPin);

        if (currentFrame().isFinish()) {
            frameNo += 1;
        }

        return state;
    }

    private Round currentFrame() {
        return rounds.get(rounds.size() - 1);
    }

    public boolean isEndGame() {
        if (frameNo == LAST_FRAME_NO) {
            return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingGame game = (BowlingGame) o;
        return frameNo == game.frameNo && Objects.equals(rounds, game.rounds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNo, rounds);
    }

}
