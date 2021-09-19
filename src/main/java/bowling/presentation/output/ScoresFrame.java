package bowling.presentation.output;

import bowling.domain.frame.FrameType;

import java.util.List;

public class ScoresFrame {

    private static final int NONE = -1;

    private static final String NORMAL_STRIKE_FRAME = "  X    |";

    private static final String BOUNDARY = "|";

    private static final String SPACE = " ";

    private static final String STRIKE = "X";

    private static final String SPARE = "/";

    private static final String NO_POINT = "-";

    private static final int MAX_SCORE = 10;

    private static final int NO_POINT_SCORE = 0;

    private static final int NORMAL_FRAME_SIZE = 2;

    private static final int FINAL_FRAME_SIZE = 3;

    private static final int INDENT_SIZE = 5;

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
        if (scores.size() < NORMAL_FRAME_SIZE || scores.size() > FINAL_FRAME_SIZE) {
            throw new IllegalArgumentException("각 프레임은 2개 혹은 3개의 점수만을 가질 수 있습니다.");
        }
    }

    public String toOutput() {
        StringBuilder frameOutput = new StringBuilder();
        String outputScores = outputScores();
        frameOutput.append(SPACE).append(SPACE).append(outputScores);
        for (int i = outputScores.length(); i < INDENT_SIZE; i++) {
            frameOutput.append(SPACE);
        }

        return frameOutput.append(BOUNDARY).toString();
    }

    private String outputScores() {
        String outputScores = firstAndSecondScore();
        return setThirdScore(outputScores);
    }

    private String firstAndSecondScore() {

        String outputScores = "";

        for (int i = 0; i < scores.size(); i++) {
            int score = scores.get(i);
            if (score == NONE) {
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
        if (score == MAX_SCORE && frameType == FrameType.NORMAL) {
            return outputScores + NORMAL_STRIKE_FRAME;
        }
        if (score == MAX_SCORE) {
            return outputScores + STRIKE;
        }
        if (score == NO_POINT_SCORE) {
            return outputScores + NO_POINT;
        }
        return outputScores + score;
    }

    private String secondScore(String outputScores, int idx, int score) {
        if (idx != 1) {
            return outputScores;
        }

        int prev = scores.get(0);
        outputScores += BOUNDARY;

        if (prev != MAX_SCORE && prev + score == MAX_SCORE) {
            return outputScores + SPARE;
        }
        if (prev == MAX_SCORE && score == MAX_SCORE) {
            return outputScores + STRIKE;
        }
        if (score == NO_POINT_SCORE) {
            return outputScores + NO_POINT;
        }

        return outputScores + score;
    }

    private String setThirdScore(String outputScores) {
        if (frameType != FrameType.FINAL) {
            return outputScores;
        }

        int score = scores.get(2);

        if (score == NONE) {
            return outputScores;
        }

        outputScores += BOUNDARY;

        if (score == MAX_SCORE) {
            return outputScores + STRIKE;
        }
        if (score == NO_POINT_SCORE) {
            return outputScores + SPARE;
        }
        return outputScores + score;
    }


}
