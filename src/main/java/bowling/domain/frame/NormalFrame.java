package bowling.domain.frame;

import bowling.domain.Content;
import bowling.domain.Score;
import bowling.domain.state.State;
import bowling.domain.state.StateFactory;
import bowling.exception.CannotCalculateScore;
import bowling.exception.NotCreateFrameException;

import java.util.Objects;

public class NormalFrame implements Frame {

    private final Content content;
    private State state;
    private Frame next;

    private NormalFrame(Content content) {
        this.state = StateFactory.initialState();
        this.content = content;
    }

    public static NormalFrame initialize() {
        return new NormalFrame(Content.initialize());
    }

    @Override
    public Frame next() throws NotCreateFrameException {
        generateNextFrame();
        return next;
    }

    private void generateNextFrame() {
        if (content.isNextFrameNoLast()) {
            next = new FinalFrame(content.next());
            return;
        }
        next = new NormalFrame(content.next());
    }

    @Override
    public void bowling(int hit) {
        state = state.bowl(hit);
    }

    @Override
    public Score calculate(Score score) {
        Score calculatedScore = state.calculateAdditionalScore(score);
        if (calculatedScore.hasAdditionalScoreCount()) {
            return calculateScoreToNextFrame(calculatedScore);
        }
        return calculatedScore;
    }

    private Score calculateScoreToNextFrame(Score score) {
        if (next == null) {
            throw new CannotCalculateScore();
        }
        return next.calculate(score);
    }

    @Override
    public boolean isFinish() {
        return state.isFinish();
    }

    @Override
    public boolean hasLastBonusChance() {
        return false;
    }

    @Override
    public Content content() {
        return content;
    }

    @Override
    public Score score() {
        if (!isFinish()) {
            throw new CannotCalculateScore();
        }
        Score score = state.score();
        if (score.hasAdditionalScoreCount()) {
            return calculateScoreToNextFrame(score);
        }
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(state, that.state) && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, content);
    }

    @Override
    public String toString() {
        return state.description();
    }
}
