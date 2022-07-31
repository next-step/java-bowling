package bowling2.domain.score;

import bowling2.domain.frame.Frame;
import bowling2.exception.BowlingException;

import java.util.Optional;

// TODO(jack.comeback) : 외부에서 prev, next null인지 아닌지 검증하고 들어온다. (내부에서도 해야할까?)
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
