package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.score.FrameScore;
import bowling.domain.score.Result;
import bowling.domain.score.Score;

import java.util.Arrays;
import java.util.Optional;

public enum ViewResult {
    STRIKE (Result.STRIKE) {
        @Override
        String parseScore(Score first, Score second) {
            return "   X    ";
        }
    },
    SPARE (Result.SPARE) {
        @Override
        String parseScore(Score first, Score second) {
            return String.format("  %d|/   ", first.getContent());
        }
    },
    MISS (Result.MISS) {
        @Override
        String parseScore(Score first, Score second) {
            return String.format("   %d|%d  ", first.getContent(), second.getContent());
        }
    },
    GUTTER (Result.GUTTER) {
        @Override
        String parseScore(Score first, Score second) {
            return "   -    ";
        }
    };

    private final Result result;

    ViewResult(Result result) {
        this.result = result;
    }

    private static ViewResult findByResult(Result result) {
        return Arrays.stream(values())
                .filter(viewResult -> viewResult.result == result)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("프레임 결과에 해당하는 ViewResult가 없습니다"));
    }

    public static String parseFrameScore(Frame frame) {
        FrameScore frameScore = frame.getFrameScore();
        Optional<Score> bonusScore = frame.getBonusScore();

        if (frameScore.size() == 0) {
            return "        ";
        }

        if (frameScore.size() < FrameScore.SCORE_COUNT) {
            return String.format("   %s    ", frameScore.getFirst().getContent());
        }

        String result = parseFirstAndSecond(frameScore.checkResult(), frameScore.getFirst(), frameScore.getSecond());

        return bonusScore.map(b -> result + String.format(" + %d ", b.getContent()))
                .orElse(result);
    }

    private static String parseFirstAndSecond(Result result, Score first, Score second) {
        ViewResult viewResult = findByResult(result);
        return viewResult.parseScore(first, second);
    }

    abstract String parseScore(Score first, Score second);
}
