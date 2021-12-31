package bowling.domain;

import java.util.List;

public abstract class Frame {

    abstract Frame bowl(Ball ball);

    abstract boolean isEnd();

    abstract int getFrameIndex();

    public abstract String symbol();

    public abstract Score score();

    abstract Score additionalScore(Score previous);

    public int score(List<Frame> frames, int limit) {
        return frames.stream()
                .limit(limit)
                .map(Frame::score)
                .mapToInt(Score::getScore)
                .sum();
    }

}
