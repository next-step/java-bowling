package bowling.domain;

import java.util.List;

public interface Frame {
    Frame bowl(Ball ball);

    boolean isEnd();

    int getFrameIndex();

    String symbol();

    Score score();

    Score additionalScore(Score previous);

    default int score(List<Frame> frames, int limit) {
        return frames.stream()
                .limit(limit)
                .map(Frame::score)
                .mapToInt(Score::getScore)
                .sum();
    }
}
