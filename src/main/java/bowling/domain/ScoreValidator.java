package bowling.domain;

import bowling.global.BowlingConst;

public class ScoreValidator {

    private ScoreValidator() throws InstantiationException {
        throw new InstantiationException("ScoreValidator는 생성할 수 없습니다.");
    }

    public static void validate(Frame frame) {
        if (frame.scores().size() < 2) {
            return;
        }
        if (frame instanceof DefaultFrame) {
            validateDefaultFrame(frame.scores());
        }
        if (frame instanceof LastFrame) {
            validateLastFrame(frame.scores());
        }
    }

    private static void validateDefaultFrame(Scores scores) {
        if (scores.sum() <= BowlingConst.SCORE_STRIKE) {
            return;
        }
        throw new IllegalArgumentException();
    }

    private static void validateLastFrame(Scores scores) {
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

    private static void validateSecondTimeScore(Scores scores) {
        if (scores.first().isStrike()) {
            return;
        }
        if (scores.sum() <= BowlingConst.SCORE_STRIKE) {
            return;
        }
        throw new IllegalArgumentException();
    }

    private static void validateThirdTimeScore(Scores scores) {
        if (scores.first().isStrike() && scores.second().isStrike()) {
            return;
        }
        if (new Scores(scores.first(), scores.second()).sum() == BowlingConst.SCORE_STRIKE) {
            return;
        }
        if (scores.first().isStrike()
                && new Scores(scores.second(), scores.third()).sum() <= BowlingConst.SCORE_STRIKE) {
            return;
        }
        throw new IllegalArgumentException();
    }
}
