package step4.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static step4.domain.Score.UN_COUNTABLE_SCORE;

public class Scores {
    private final List<Score> scores;

    public Scores(Frames frames) {
        this.scores = convertFrom(frames);
    }

    private List<Score> convertFrom(Frames frames) {
        return frames.frames()
                .stream()
                .map(frame -> score(frame, frames))
                .collect(Collectors.toList());
    }

    private Score score(Frame frame, Frames frames) {
        Score score = frame.score();

        while (score.isOpportunityLeft()) {
            frame = frames.get(frame.next().index());
            score = frame.add(score);
        }

        return score;
    }

    public List<Integer> accumulatedScore() {
        return IntStream.range(0, scores.size())
                .mapToObj(this::accumulate)
                .collect(Collectors.toList());
    }

    private Integer accumulate(int currentIndex) {
        if (scores.get(currentIndex).isUnCountable()) {
            return UN_COUNTABLE_SCORE;
        }

        return scores.stream()
                .limit(currentIndex + 1)
                .mapToInt(Score::value)
                .sum();
    }
}
