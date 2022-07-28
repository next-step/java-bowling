package bowling2.domain.score;

import bowling2.domain.frame.Frame;
import bowling2.exception.BowlingException;

import java.util.Objects;
import java.util.Optional;

public class StrikeScoreStrategy implements ScoreStrategy {

    @Override
    public int compute(Frame frame) {
        Integer prevScore = Optional.ofNullable(frame.getPrev())
                .map(Frame::score)
                .orElse(0);

        int sumOfFallenPins = frame.sumOfFallenPins();

        Frame nextFrame = frame.getNext();
        Objects.requireNonNull(nextFrame);
        // next 프레임의 핀 개수가 2개면 next 프레임만 필요
        if (nextFrame.countOfFallenPins() >= 2) {
            int nextScore = nextFrame.sumOfFallenPins();

            return prevScore + sumOfFallenPins + nextScore;
        }
        // next 프레임의 핀 개수가 1개면 next의 next 프레임도 필요
        if (nextFrame.countOfFallenPins() < 2) {
            int firstNextScore = nextFrame.sumOfFallenPins();
            Frame nextnextFrame = nextFrame.getNext();
            Objects.requireNonNull(nextnextFrame);
            int secondNextScore = nextnextFrame.sumOfFallenPins();

            return prevScore + sumOfFallenPins + firstNextScore + secondNextScore;
        }

        // 이전에 리턴안되고 여기까지 오면 점수 계산이 이상한거라 exp
        throw new BowlingException("strike 점수 계산 중 에러가 발생했습니다."); // TODO(jack.comeback) : 에러 디테일
    }
}
