package bowling.domain.score;

import bowling.domain.frame.Frame;
import bowling.exception.BowlingException;

import java.util.Optional;

public class SpareScoreStrategy implements ScoreStrategy {
    @Override
    public int compute(Frame frame) {
        Integer prevScore = Optional.ofNullable(frame.getPrev())
                .map(Frame::score)
                .orElse(0);

        int sumOfFallenPins = frame.getFallenPins()
                .stream()
                .mapToInt(e -> e)
                .sum();

        Integer nextScore = Optional.ofNullable(frame.getNext())
                .map(Frame::sumOfFallenPins)
                .orElseThrow(() -> new BowlingException("spare 점수 계산 시에는 next는 null이면 안됩니다."));

        return prevScore + sumOfFallenPins + nextScore;
    }
}
