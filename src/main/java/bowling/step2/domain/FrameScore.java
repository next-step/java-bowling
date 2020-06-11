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
        return get(0) == Score.getStrike();
    }

    public boolean isSpared () {
        return get(0) != null && get(0).sumIsStrike(get(1));
    }

    public Stream<Score> stream () {
        return scores.stream();
    }

    public void addScore (Score score) {
        scores.add(score);
    }

    public int totalScore () {
        return stream().map(Score::getValue)
                       .reduce(0, Math::addExact);
    }

    public int size () {
        return scores.size();
    }

    public Score get (int index) {
        return size() > index ? scores.get(index) : null;
    }
}