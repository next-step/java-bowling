package bowling.domain.frame;

import bowling.domain.pin.Pin;

public interface Frame {

    static Frame init() {
        return NormalFrame.of(Index.first());
    }

    static Frame endFrame() {
        return EndFrame.of();
    }

    Frame bowl(Pin felledPin);

    boolean isEnd();

    Score calculateScore(Score baseScore);

    Score getScore();

    boolean hasNext();

    Frame next();

    Index getIndex();

}
