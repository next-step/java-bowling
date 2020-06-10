package bowling.step2.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class FrameScore {
    private final List<Score> scores;

    private FrameScore (List<Score> scores) {
        this.scores = scores;
    }

    public static FrameScore of (List<Score> scores) {
        return new FrameScore(scores);
    }

    public static FrameScore init () {
        return of(new ArrayList<>());
    }

    public static FrameScore ofStrike () {
        Score strike = Score.getStrike();
        return of(Collections.singletonList(strike));
    }

    public boolean isStrike () {
        if (scores.size() == 0) {
            return false;
        }
        return scores.get(0) == Score.getStrike();
    }

    public boolean isSpared () {
        if (scores.size() < 2) {
            return false;
        }
        Score first = scores.get(0);
        Score second = scores.get(1);
        return Score.sumIsStrike(first, second);
    }

    public Stream<Score> stream () {
        return scores.stream();
    }

    public void addScore (FrameScore frameScore) {
        scores.addAll(frameScore.scores);
    }

    public int totalScore () {
        return stream().map(Score::getValue)
                       .reduce(0, Math::addExact);
    }

    public int size () {
        return scores.size();
    }

    public Score firstScore () {
        return scores.get(0);
    }

    public Score secondScore () {
        return scores.get(1);
    }
}