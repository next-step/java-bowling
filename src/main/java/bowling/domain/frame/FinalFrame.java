package bowling.domain.frame;

import static bowling.domain.frame.NormalFrame.FINAL_ROUND;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.domain.state.running.Ready;
import java.util.ArrayList;
import java.util.List;

public class FinalFrame implements Frame {

    private final int round;
    private final List<State> states;

    private FinalFrame(int round, List<State> states) {
        this.round = round;
        this.states = states;
    }

    static Frame from(int round, List<State> states) {
        return new FinalFrame(round, states);
    }

    public static Frame createFinalFrame() {
        List<State> states = new ArrayList<>();
        states.add(new Ready());
        return new FinalFrame(FINAL_ROUND, states);
    }

    @Override
    public Frame bowling(Pin pin) {
        return null;
    }

    @Override
    public FrameResult createFrameResult() {
        return null;
    }

//    Score score() {
//        Score score = state.createScore();
//        if (score.canCalculateScore()) {
//            return score;
//        }
//        return calculateAdditionalScore(score);
//    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        for (State state : states) {
            score = state.calculateAdditionalScore(score);
            if (score.canCalculateScore()) {
                return score;
            }
        }
        throw new RuntimeException("더 이상 계산할 수 없습니다.");
    }

}
