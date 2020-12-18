package bowling.domain;

import bowling.NotPlayableException;
import bowling.domain.frame.Frame;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class BowlingGame {
    private final User user;
    private final Frame firstFrame;
    private Frame nowFrame;

    private BowlingGame(final User user, final Frame firstFrame) {
        this.user = user;
        this.firstFrame = firstFrame;
        this.nowFrame = firstFrame;
    }

    public static BowlingGame of(final User user) {
        return new BowlingGame(user, Frame.creates());
    }

    public boolean isNotFinished() {
        return !isFinished();
    }

    public boolean isFinished() {
        return nowFrame.isFinishedAll();
    }

    public int nowFrameNo() {
        return nowFrame.getIndex() + 1;
    }

    public void pitch(final int fallenPins) {
        if (isFinished()) {
            throw new NotPlayableException();
        }

        if (nowFrame.isNotPlayable()) {
            nowFrame = nowFrame.next();
        }

        nowFrame.pitch(Pins.of(fallenPins));
    }

    public List<String> symbols() {
        return firstFrame.toFrames().stream()
                .map(Frame::getSymbol)
                .collect(collectingAndThen(toList(), Collections::unmodifiableList));
    }
    
    public String name() {
        return user.getName();
    }
}
