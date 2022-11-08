package bowling.domain;

import bowling.global.BowlingConst;
import java.util.List;

public class ScoreValidator {

    private ScoreValidator() throws InstantiationException {
        throw new InstantiationException("ScoreValidator는 생성할 수 없습니다.");
    }

    public static void validate(Frame frame) {
        if (frame instanceof DefaultFrame) {
            validateDefaultFrame(frame.scores());
        }
        if (frame instanceof LastFrame) {
            validateLastFrame(frame.scores());
        }
    }

    private static void validateDefaultFrame(List<Score> scores) {
        if (scores.size() < 2) {
            return;
        }
        if (scores.get(0).value() + scores.get(1).value() <= BowlingConst.SCORE_STRIKE) {
            return;
        }
        throw new IllegalArgumentException();
    }

    private static void validateLastFrame(List<Score> scores) {
        if (scores.size() < 2) {
            return;
        }
        if (scores.size() == 2) {
            validateSecondTimeScore(scores);
            return;
        }
        if (scores.size() == 3) {
            validateThirdTimeScore(scores);
            return;
        }
        throw new IllegalArgumentException();
    }

    private static void validateSecondTimeScore(List<Score> scores) {
        Score first = scores.get(0);
        Score second = scores.get(1);
        if (first.isStrike()) {
            return;
        }
        if ((first.value() + second.value()) <= BowlingConst.SCORE_STRIKE) {
            return;
        }
        throw new IllegalArgumentException();
    }

    private static void validateThirdTimeScore(List<Score> scores) {
        Score first = scores.get(0);
        Score second = scores.get(1);
        Score third = scores.get(2);
        if (first.isStrike() && second.isStrike()) {
            return;
        }
        if ((first.value() + second.value()) == BowlingConst.SCORE_STRIKE) {
            return;
        }
        if (first.isStrike() && (second.value() + third.value()) <= BowlingConst.SCORE_STRIKE) {
            return;
        }
        throw new IllegalArgumentException();
    }
}
