package bowling.domain.frame;

import bowling.domain.score.Score;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Frames {
    public static final int TOTAL_FRAME_NUMBER = 10;

    private List<Frame> frames;
    private List<String> results;

    public List<String> initFrames() {
        frames = Stream.generate(NormalFrame::create)
            .limit(TOTAL_FRAME_NUMBER - 1)
            .collect(Collectors.toList());
        frames.add(FinalFrame.create());

        results = Stream.generate(() -> "")
            .limit(TOTAL_FRAME_NUMBER)
            .collect(Collectors.toList());
        return results;
    }

    public void throwBalls(int frameNumber, List<Integer> scores) {
        scores.forEach(i -> throwBall(frameNumber
            , Score.from(i))
        );
    }

    public void throwBall(int frameNumber, Score score) {
        frames.get(frameNumber - 1).addScore(score);
        results.set(frameNumber - 1,
            frames.get(frameNumber - 1)
                .toScoreSymbol()
        );
    }

    public boolean isNext(int frameNumber) {
        return frames.get(frameNumber - 1).isNextFrame();
    }

    public List<String> results() {
        return results;
    }
}
