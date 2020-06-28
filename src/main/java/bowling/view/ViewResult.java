package bowling.view;

import bowling.domain.score.Result;
import bowling.domain.score.Score;
import bowling.domain.score.Scores;

import java.util.Arrays;
import java.util.Optional;

public enum ViewResult {
    STRIKE (Result.STRIKE) {
        @Override
        String parse(Score first, Score second) {
            return "   X    ";
        }
    },
    SPARE (Result.SPARE) {
        @Override
        String parse(Score first, Score second) {
            return String.format("  %d|/   ", first.getContent());
        }
    },
    MISS (Result.MISS) {
        @Override
        String parse(Score first, Score second) {
            return String.format("   %d|%d  ", first.getContent(), second.getContent());
        }
    },
    GUTTER (Result.GUTTER) {
        @Override
        String parse(Score first, Score second) {
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
        Optional<Score> first = scores.getFirst();
        Optional<Score> second = scores.getSecond();

        if (!first.isPresent()) {
            return "        ";
        }

        if (!second.isPresent()) {
            return String.format("   %s    ", first.get().getContent());
        }

        ViewResult viewResult = findByResult(scores.checkResult());
        return viewResult.parse(first.get(), second.get());
    }

    abstract String parse(Score first, Score second);
}
