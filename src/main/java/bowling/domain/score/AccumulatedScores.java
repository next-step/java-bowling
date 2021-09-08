package bowling.domain.score;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import bowling.domain.frame.Frames;

public class AccumulatedScores {
    private final List<Integer> cumulativeScores;
    private final PureScores pureScores;

    private AccumulatedScores(Frames frames) {
        cumulativeScores = new ArrayList<>();
        pureScores = PureScores.from(new ArrayList<>(), 0);
        IntStream.range(0, frames.frames().size())
            .forEach(i -> pureScores.initFrameAsScore(frames, i, cumulativeScores));
    }

    public static AccumulatedScores from(Frames frames) {
        return new AccumulatedScores(frames);
    }

    public List<Integer> getCumulativeScores(Frames frames) {
        return pureScores.getCumulativeScores(frames, cumulativeScores);
    }

    public void addScore(final Score score) {
        pureScores.scores()
            .add(score.score());
    }
}
