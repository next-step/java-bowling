package bowling.frame.domain;

import bowling.pin.domain.Pin;

public interface Frame {

    static Frame init() {
        return NormalFrame.of(Index.first());
    }

    static Frame end() {
        return EndFrame.of();
    }

    Frame roll(Pin felled);

    boolean isEnd();

    boolean hasNext();

    Frame next();

    Index getIndex();

    Score getScore();

    Score calculateScore(Score base);


}
