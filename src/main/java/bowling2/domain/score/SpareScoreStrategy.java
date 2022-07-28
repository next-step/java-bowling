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

        // TODO(jack.comeback) : Optional.of()에서 null일 경우 orElseThrow가 나가나? 아님 NPE가 나가는지 확인필요
        Integer nextScore = Optional.of(frame.getNext())
                .map(frame1 -> frame1.getFallenPins()
                        .stream()
                        .mapToInt(e -> e)
                        .sum())
                .orElseThrow(() -> new BowlingException("spare 점수 계산 시에는 next는 null이면 안됩니다."));

        return prevScore + sumOfFallenPins + nextScore;
    }
}
