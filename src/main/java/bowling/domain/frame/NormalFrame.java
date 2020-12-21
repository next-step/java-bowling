package bowling.domain.frame;

import bowling.domain.score.Pin;
import bowling.state.*;

/**
 * Created By mand2 on 2020-12-21.
 */
public class NormalFrame extends Frame {

    public static final String ERROR_MESSAGE_FRAME_INDEX_RANGE = "프레임은 1~9번 사이로만 이루어져야 합니다.";

    private BowlingState state;

    private NormalFrame(int index) {
        super(index);
        this.state = Open.of(this);
    }

    public static Frame of(int index) {
        validateIndex(index);
        return new NormalFrame(index);
    }

    private static void validateIndex(int index) {
        if (Frames.START_INDEX > index || Frames.NORMAL_FRAME_SIZE < index) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FRAME_INDEX_RANGE);
        }
    }

    public BowlingState getState() {
        return state;
    }

    @Override
    public void pitch(Pin knockDownPin) {
        this.score.pitch(knockDownPin);
    }

    @Override
    public boolean isPlayable() {
        checkState();
        return state.isPlayable();
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public void checkState() {

        if (score.isStrike()) {
            this.state = Strike.of(this);
        }
        if (score.isSpare()) {
            this.state = Spare.of(this);
        }
        if (score.isMiss()) {
            this.state = Miss.of(this);
        }

    }

    @Override
    public int getIndex() {
        return super.getIndex();
    }
}
