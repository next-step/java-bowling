package bowling.view;

import bowling.domain.score.Result;
import bowling.domain.score.Score;
import bowling.domain.score.Scores;

import java.util.Arrays;

public enum ViewResult {
    STRIKE (Result.STRIKE) {
        @Override
        String parse(Scores scores) {
            return "   X    ";
        }
    },
    SPARE (Result.SPARE) {
        @Override
        String parse(Scores scores) {
            Score first = scores.getFirst();
            return String.format("  %d|/   ", first.getContent());
        }
    },
    MISS (Result.MISS) {
        @Override
        String parse(Scores scores) {
            Score first = scores.getFirst();
            Score second = scores.getSecond();
            return String.format("   %d|%d  ", first.getContent(), second.getContent());
        }
    },
    GUTTER (Result.GUTTER) {
        @Override
        String parse(Scores scores) {
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

    public static String parseScores(Scores scores) {
        if (scores.canAddMore()) {
            Score first = scores.getFirst();
            return String.format("   %s    ", first.getContent());
        }

        Result result = scores.checkResult();
        ViewResult viewResult = findByResult(result);
        return viewResult.parse(scores);
    }

    abstract String parse(Scores scores);
}
