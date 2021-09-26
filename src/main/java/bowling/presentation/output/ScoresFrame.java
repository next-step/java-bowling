package bowling.presentation.output;

import bowling.common.Pin;
import bowling.domain.frame.FrameType;
import bowling.presentation.output.constant.ScoreOutput;

import java.util.List;

import static bowling.presentation.output.constant.FrameSize.*;
import static bowling.presentation.output.constant.ScoreOutput.*;

public class ScoresFrame {

    private static final String NORMAL_STRIKE_FRAME = " X   ";

    private final List<Integer> scores;

    private final FrameType frameType;

    private ScoresFrame(List<Integer> scores, FrameType frameType) {
        this.scores = scores;
        this.frameType = frameType;
    }

    public static ScoresFrame from(List<Integer> scores) {
        validate(scores);
        return new ScoresFrame(scores, FrameType.from(scores.size()));
    }

    private static void validate(List<Integer> scores) {
        checkIsNullOrEmpty(scores);
        checkScoresSize(scores);
    }

    private static void checkIsNullOrEmpty(List<Integer> scores) {
        if (scores == null || scores.isEmpty()) {
            throw new IllegalArgumentException("프레임의 점수가 존재하지 않습니다.");
        }
    }

    private static void checkScoresSize(List<Integer> scores) {
        if (scores.size() < NORMAL.value() || scores.size() > FINAL.value()) {
            throw new IllegalArgumentException("각 프레임은 2개 혹은 3개의 점수만을 가질 수 있습니다.");
        }
    }

    public String toOutput() {
        StringBuilder frameOutput = new StringBuilder();
        String outputScores = outputScores();
        frameOutput.append(SPACE.value()).append(SPACE.value()).append(outputScores);
        for (int i = outputScores.length(); i < INDENT.value(); i++) {
            frameOutput.append(SPACE.value());
        }

        return frameOutput.append(BOUNDARY.value()).toString();
    }

    private String outputScores() {
        String outputScores = firstAndSecondScore();
        return setThirdScore(outputScores);
    }

    private String firstAndSecondScore() {

        String outputScores = "";

        for (int i = 0; i < scores.size(); i++) {
            int score = scores.get(i);
            if (score == Pin.NONE.value()) {
                continue;
            }
            outputScores = firstScore(outputScores, i, score);
            outputScores = secondScore(outputScores, i, score);
        }

        return outputScores;

    }

    private String firstScore(String outputScores, int idx, int score) {
        if (idx != 0) {
            return outputScores;
        }
        if (score == Pin.MAX.value() && frameType == FrameType.NORMAL) {
            return outputScores + NORMAL_STRIKE_FRAME;
        }
        if (score == Pin.MAX.value()) {
            return outputScores + STRIKE.value();
        }
        if (score == Pin.NO_POINT.value()) {
            return outputScores + ScoreOutput.NO_POINT.value();
        }
        return outputScores + score;
    }

    private String secondScore(String outputScores, int idx, int score) {
        if (idx != 1) {
            return outputScores;
        }

        int prev = scores.get(0);
        outputScores += BOUNDARY.value();

        if (prev != Pin.MAX.value() && prev + score == Pin.MAX.value()) {
            return outputScores + SPARE.value();
        }
        if (prev == Pin.MAX.value() && score == Pin.MAX.value()) {
            return outputScores + STRIKE.value();
        }
        if (score == Pin.NO_POINT.value()) {
            return outputScores + NO_POINT.value();
        }

        return outputScores + score;
    }

    private String setThirdScore(String outputScores) {
        if (frameType != FrameType.FINAL) {
            return outputScores;
        }

        int score = scores.get(2);

        if (score == Pin.NONE.value()) {
            return outputScores;
        }

        outputScores += BOUNDARY.value();

        if (score == Pin.MAX.value()) {
            return outputScores + STRIKE.value();
        }
        if (score == Pin.NO_POINT.value()) {
            return outputScores + NO_POINT.value();
        }
        return outputScores + score;
    }


}
