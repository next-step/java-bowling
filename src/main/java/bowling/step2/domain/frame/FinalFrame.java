package bowling.step2.domain.frame;

import bowling.step2.domain.LeftOverPins;
import bowling.step2.domain.Score;
import bowling.step2.domain.state.Ready;
import bowling.step2.domain.state.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class FinalFrame implements Frame {
    private static final int COUNT_OF_MAX_BOWL = 3;
    
    private final LinkedList<State> states;
    private LeftOverPins leftOverPins;
    
    public FinalFrame() {
        this.states = new LinkedList<>(List.of(new Ready()));
        this.leftOverPins = new LeftOverPins();
    }
    
    @Override
    public Frame bowl(final int fallenPins) {
        State state = states.getLast();
        state = state.bowl(fallenPins);
        
        states.set(statesLastIndex(), state);
        leftOverPins = leftOverPins.knockDown(fallenPins);
        
        return checkGameOver(state);
    }
    
    private int statesLastIndex() {
        return states.size() - 1;
    }
    
    private FinalFrame checkGameOver(final State state) {
        if (isCurrentFrameFinished(state)) {
            return new FinalFrame();
        }
        
        if (state.isFinished()) {
            states.add(new Ready());
            leftOverPins = new LeftOverPins();
        }
        return this;
    }
    
    private boolean isCurrentFrameFinished(final State state) {
        return isMiss(state) || isFinalBonusBowl();
    }
    
    private boolean isFinalBonusBowl() {
        return (states.size() >= 2 && isContainsSpare()) || (states.size() >= COUNT_OF_MAX_BOWL);
    }
    
    private boolean isContainsSpare() {
        final List<Score> scores = getScores();
        return IntStream.range(0, scores.size() - 1)
                .anyMatch(index -> scores.get(index).getFallenPins() + scores.get(index + 1).getFallenPins() == COUNT_OF_MAX_PINS);
    }
    
    private boolean isMiss(final State state) {
        return state.isFinished() && leftOverPins.isExistLeftOverPins();
    }
    
    @Override
    public boolean isNormalFrame() {
        return false;
    }
    
    @Override
    public List<Score> getScores() {
        List<Score> scores = new ArrayList<>();
        for (State state : states) {
            final List<Score> stateScores = state.getScores();
            scores.addAll(stateScores);
        }
        
        return Collections.unmodifiableList(scores);
    }
    
    @Override
    public int getOneNextScore() {
        return getScores().get(0).getFallenPins();
    }
    
    @Override
    public int getTwoNextScore() {
        if (isTwoScoreContainsReady() || isFirstStateNormal()) {
            return -1;
        }
        
        return getSumScore(2);
    }
    
    public boolean isFirstStateNormal() {
        return getScores().size() == 1 && getScores().get(0).getFallenPins() != COUNT_OF_MAX_PINS;
    }
    
    private boolean isTwoScoreContainsReady() {
        return getScores().stream()
                .limit(2)
                .anyMatch(score -> score.getFallenPins() == READY_SCORE);
    }
    
    private int getSumScore(int limit) {
        return getScores().stream()
                .mapToInt(Score::getFallenPins)
                .limit(limit)
                .sum();
    }
    
    @Override
    public int calculateCumulativeScore(final int cumulativeScore) {
        if (isNotReadyContains() && isCurrentFrameFinished(states.getLast())) {
            return cumulativeScore + getSumScore(getScores().size());
        }
        
        return READY_SCORE;
    }
    
    private boolean isNotReadyContains() {
        return getScores().stream()
                .noneMatch(score -> score.getFallenPins() == READY_SCORE);
    }
    
    @Override
    public boolean isCurrentScoreSpare(final int indexOfScore) {
        if (indexOfScore == 2) {
            return isCurrentScoreStrike(0) && isSpare(indexOfScore);
        }
        
        if (indexOfScore == 1) {
            return isSpare(indexOfScore);
        }
        
        return false;
    }
    
    private boolean isSpare(final int indexOfScore) {
        return getScores().get(indexOfScore - 1).getFallenPins() + getScores().get(indexOfScore).getFallenPins() == COUNT_OF_MAX_PINS;
    }
    
    private boolean isCurrentScoreStrike(final int indexOfScore) {
        return getScores().get(indexOfScore).getFallenPins() == COUNT_OF_MAX_PINS;
    }
}
