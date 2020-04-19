package bowling.framestate.last;

import bowling.FrameScore;
import bowling.LeftScoreCount;
import bowling.Pin;
import bowling.framestate.State;
import bowling.framestate.common.Miss;

import java.util.Collections;
import java.util.List;

public class FirstBowlLastFrame implements State {

    private final Pin pinCount;

    private FirstBowlLastFrame(final Pin pinCount) {
        this.pinCount = pinCount;
    }

    public static FirstBowlLastFrame newInstance(final Pin countOfPin) {
        return new FirstBowlLastFrame(countOfPin);
    }

    @Override
    public State bowl(final Pin pinCount) {
        Pin sumCount = pinCount.add(this.pinCount);

        if (sumCount.isEqualTo(10)) {
            return SpareLastFrame.newInstance(this.pinCount, pinCount);
        }

        return Miss.newInstance(this.pinCount, pinCount);
    }

    @Override
    public FrameScore createFrameScore() {
        return FrameScore.newInstance(pinCount.toScore(), LeftScoreCount.of(1));
    }

    @Override
    public FrameScore addingUpFrameScore(final FrameScore beforeScore) {
        return beforeScore.addingUp(Collections.singletonList(pinCount.toScore()));
    }

    @Override
    public boolean isOver() {
        return false;
    }

    @Override
    public List<Pin> getPins() {
        return Collections.singletonList(pinCount);
    }
}
