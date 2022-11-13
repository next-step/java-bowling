package bowling.domain.frame;

import static java.util.stream.Collectors.*;

import java.util.LinkedList;
import java.util.List;

import bowling.domain.BowlRecord;
import bowling.domain.BowlingGameFrameRecord;
import bowling.domain.frame.state.Ready;
import bowling.domain.frame.state.State;

public class LastFrame extends Frame {
    private static final int DEFAULT_BOWL_COUNT = 2;
    private static final int BONUS_BOWL_COUNT = 1;
    private static final String ALREADY_ENDED_FRAME_EXCEPTION_MESSAGE = "이미 종료된 프레임입니다.";

    private final LinkedList<State> states = new LinkedList<>();

    private int bowlCount = 0;

    public LastFrame() {
        states.add(new Ready());
    }

    @Override
    public void bowl(int falledPins) {
        if (isFrameFinish()) {
            throw new IllegalStateException(ALREADY_ENDED_FRAME_EXCEPTION_MESSAGE);
        }

        State currentState = getCurrentState();
        bowlCount += 1;

        if (currentState.isFinish()) {
            states.add(new Ready().bowl(falledPins));
            return;
        }

        states.removeLast();
        states.push(currentState.bowl(falledPins));
    }

    @Override
    public Frame createNextFrame() {
        return Frames.createNextFrame(Frame.LAST_FRAME);
    }

    @Override
    public BowlingGameFrameRecord createFrameRecord() {
        List<BowlRecord> bowlRecords = states.stream()
            .map(State::createBowlRecord)
            .collect(toList());

        return new BowlingGameFrameRecord(getScore(), bowlRecords);
    }

    @Override
    public Score calculateBonusScore(Score previousFrameScore) {
        Score score = previousFrameScore;
        for (State state : states) {
            score = state.calculateBonusScore(score);
            if (score.canCalculateScore()) {
                return score;
            }
        }
        return score;
    }

    @Override
    public int getFrameNumber() {
        return LAST_FRAME;
    }

    @Override
    public boolean isFrameFinish() {
        return hasNoBonusBowl() || bowlCount == (DEFAULT_BOWL_COUNT + BONUS_BOWL_COUNT);
    }

    private State getCurrentState() {
        return states.getLast();
    }

    private boolean hasNoBonusBowl() {
        return bowlCount == 2 && !getCurrentState().canBonusBowl();
    }

    private Score getScore() {
        Score score = states.getFirst().getScore();

        for (int i = 1; i < states.size(); ++i) {
            score = states.get(i).calculateBonusScore(score);
        }
        return score;
    }
}