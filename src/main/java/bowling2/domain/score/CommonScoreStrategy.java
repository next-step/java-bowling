package bowling2.domain.score;

import bowling2.domain.frame.Frame;

import java.util.Optional;

public class CommonScoreStrategy implements ScoreStrategy {
    @Override
    public int compute(Frame frame) {
        Integer prevScore = Optional.ofNullable(frame.getPrev())
                .map(Frame::score)
                .orElse(0);

        int sumOfFallenPins = frame.sumOfFallenPins();

        return prevScore + sumOfFallenPins;
    }
}
