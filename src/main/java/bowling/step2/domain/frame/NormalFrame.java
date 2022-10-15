package bowling.step2.domain.frame;

import bowling.step2.domain.FrameOrderNumber;
import bowling.step2.domain.Score;
import bowling.step2.domain.state.Ready;
import bowling.step2.domain.state.State;

import java.util.List;
import java.util.Objects;

public class NormalFrame implements Frame {
    private final FrameOrderNumber frameOrderNumber;
    private State state;
    private Frame nextFrame;
    
    
    public NormalFrame(int frameOrderNumber) {
        this.state = new Ready();
        this.frameOrderNumber = new FrameOrderNumber(frameOrderNumber);
    }
    
    @Override
    public Frame bowl(final int fallenPins) {
        this.state = this.state.bowl(fallenPins);
        if (state.isFinished()) {
            nextFrame = nextFrame();
            return nextFrame;
        }
        
        return this;
    }
    
    private Frame nextFrame() {
        if (frameOrderNumber.isNextFinalFrame()) {
            return new FinalFrame();
        }
        return new NormalFrame(frameOrderNumber.increase());
    }
    
    @Override
    public boolean isNormalFrame() {
        return true;
    }
    
    @Override
    public List<Score> getScores() {
        return state.getScores();
    }
    
    @Override
    public int getOneNextScore() {
        final List<Score> scores = state.getScores();
        return scores.get(0).getFallenPins();
    }
    
    @Override
    public int getTwoNextScore() {
        if (state.isFinished()) {
            return sumTwoNextScore();
        }
        
        return READY_SCORE;
    }
    
    private int sumTwoNextScore() {
        if (isStrike()) {
            return containsStrikeTwoNextScore();
        }
        
        return getSumScore();
    }
    
    private boolean isStrike() {
        return getScores().get(0).isStrike();
    }
    
    private int containsStrikeTwoNextScore() {
        final int oneNextScore = nextFrame.getOneNextScore();
        if (oneNextScore == READY_SCORE) {
            return READY_SCORE;
        }
        
        return getSumScore() + oneNextScore;
    }
    
    private int getSumScore() {
        return getScores().stream()
                .mapToInt(Score::getFallenPins)
                .sum();
    }
    
    @Override
    public int calculateCumulativeScore(final int cumulativeScore) {
        if (state.isFinished()) {
            return sumCumulativeScore(cumulativeScore);
        }
        
        return READY_SCORE;
    }
    
    private int sumCumulativeScore(final int cumulativeScore) {
        if (isSpare()) {
            return getSpareScore(cumulativeScore);
        }
        
        if (isStrike()) {
            return getStrikeScore(cumulativeScore);
        }
        
        return cumulativeScore + getSumScore();
    }
    
    private boolean isSpare() {
        return getScores().size() >= 2 && getScores().get(1).isSpare();
    }
    
    private int getSpareScore(final int cumulativeScore) {
        final int oneNextScore = nextFrame.getOneNextScore();
        if (oneNextScore == READY_SCORE) {
            return READY_SCORE;
        }
        
        return cumulativeScore + getSumScore() + oneNextScore;
    }
    
    private int getStrikeScore(final int cumulativeScore) {
        final int twoNextScore = nextFrame.getTwoNextScore();
        if (twoNextScore == READY_SCORE) {
            return READY_SCORE;
        }
        
        return cumulativeScore + getSumScore() + twoNextScore;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final NormalFrame that = (NormalFrame) o;
        return Objects.equals(state, that.state);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(state);
    }
}
