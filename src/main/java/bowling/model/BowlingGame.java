package bowling.model;

import bowling.CannotBowlException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class BowlingGame {
    private static final int LAST_FRAME_NO = 11;

    private int frameNo;
    private List<Round> rounds;

    public BowlingGame() {
        this.frameNo = 1;
        this.rounds = new ArrayList<>();
        this.rounds.add(new NormalRound());
    }

    public BowlingGame(int frameNo, List<Round> rounds) {
        this.frameNo = frameNo;
        this.rounds = rounds;
    }

    public State bowl(int countOfPin) throws CannotBowlException {
        System.out.println(frameNo);
        return currentFrame().bowl(countOfPin);
    }

    public List<Integer> getScore() {
        List<Integer> scores = currentFrame().getScore();

        createNextFrame();

        return scores;
    }

    private void createNextFrame() {
        if (currentFrame().isFinish()) {
            frameNo += 1;
            if (currentFrame() instanceof NormalRound) {
                Round round = ((NormalRound) currentFrame()).next(frameNo);
                rounds.add(round);
            }
        }
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

    public int getFrameNo() {
        return this.frameNo;
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
