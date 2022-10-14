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
            if (state.isStrike()) {
                final int oneNextScore = nextFrame.getOneNextScore();
                if (oneNextScore == -1) {
                    return -1;
                }
                
                return getSumScore() + oneNextScore;
            }
            
            return getSumScore();
        }
        
        return -1;
    }
    
    private int getSumScore() {
        return getScores().stream()
                .mapToInt(Score::getFallenPins)
                .sum();
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
