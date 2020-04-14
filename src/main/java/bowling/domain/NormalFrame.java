package bowling.domain;

import bowling.domain.turn.TurnState;
import bowling.domain.turn.Turns;
import bowling.exception.BowlingException;

public class NormalFrame implements Frame {

    private static final String MAX_FRAME_MESSAGE = "1~10번 프레임 까지만 등록 가능 합니다.";
    private static final String CAN_THROW_TWICE = "1~9번 프레임은 2회 던질수 있습니다.";
    private static final int ZERO = 0;
    private static final int MIN_FRAME_NUMBER = 1;
    private static final int MAX_FRAME_NUMBER = 10;

    private final Turns turns;
    private final int frameNumber;
    private final Frame nextFrame;
    private final TurnState turnState;

    public NormalFrame(final Turns turns, final int frameNumber, final Frame nextFrame,
                       final TurnState turnState) {
        validateFrameNumber(frameNumber);
        validateTurnCount(turns, frameNumber);
        this.turns = turns;
        this.frameNumber = frameNumber;
        this.nextFrame = nextFrame;
        this.turnState = turnState;
    }

    public static NormalFrame from() {
        return new NormalFrame(new Turns(), MIN_FRAME_NUMBER, null, TurnState.READY);
    }

    public static NormalFrame from(final int frameNumber) {
        return new NormalFrame(new Turns(), frameNumber, null, TurnState.READY);
    }

    private void validateFrameNumber(final int frameNumber) {
        if (frameNumber < MIN_FRAME_NUMBER || frameNumber > MAX_FRAME_NUMBER) {
            throw new BowlingException(MAX_FRAME_MESSAGE);
        }
    }

    private void validateTurnCount(final Turns turns, final int frameNumber) {
        if (frameNumber < MAX_FRAME_NUMBER && turns.isMoreTwice()) {
            throw new BowlingException(CAN_THROW_TWICE);
        }
    }

    public NormalFrame bowl(final int pinCount) {
        Turns turns = this.turns.bowl(pinCount);
        TurnState next = turnState.getNextTurnState();
        NormalFrame frame = null;

        if (turns.size() == 2 && frameNumber < 10) {
            frame = NormalFrame.from(frameNumber + 1);
        }

        return new NormalFrame(turns, frameNumber, frame, next);
    }

    public Turns getTurns() {
        return turns;
    }
}
