package bowling.domain.frame;

import bowling.domain.turn.TurnState;
import bowling.domain.turn.Turns;
import bowling.exception.BowlingException;

public class NormalFrame implements Frame {

    private static final String MAX_FRAME_MESSAGE = "1~10번 프레임 까지만 등록 가능 합니다.";
    private static final String CAN_THROW_TWICE = "1~9번 프레임은 2회 던질수 있습니다.";
    private static final int NEXT_TURN_SIZE = 2;
    private static final int MIN_FRAME_NUMBER = 1;
    private static final int MAX_FRAME_NUMBER = 10;

    private final Turns turns;
    private final int frameNumber;
    private final Frame nextFrame;
    private final TurnState turnState;

    public NormalFrame() {
        this(new Turns(), MIN_FRAME_NUMBER, null, TurnState.READY);
    }

    public NormalFrame(final int frameNumber) {
        this(new Turns(), frameNumber, null, TurnState.READY);
    }

    public NormalFrame(final Turns turns, final int frameNumber, final Frame nextFrame,
                       final TurnState turnState) {
        validateFrameNumber(frameNumber);
        validateTurnCount(turns, frameNumber);
        this.turns = turns;
        this.frameNumber = frameNumber;
        this.nextFrame = nextFrame;
        this.turnState = turnState;
    }

    public static Frame from(final int frameNumber) {
        if (frameNumber == MAX_FRAME_NUMBER) {
            return new FinalFrame();
        }

        return new NormalFrame(frameNumber);
    }

    public Frame createNext() {
        if (frameNumber == MAX_FRAME_NUMBER - 1) {
            return new FinalFrame();
        }

        NormalFrame next = new NormalFrame(frameNumber + 1);
        return new NormalFrame(this.turns, this.frameNumber, next, this.turnState);
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

    @Override
    public Frame bowl(final int pinCount) {
        if (this.turns.isFinish()) {
            new NormalFrame(new Turns(), MIN_FRAME_NUMBER,
                    new NormalFrame(), TurnState.READY);
        }

        Turns turns = this.turns.bowl(pinCount);
        TurnState next = turnState.getNextTurnState();
        Frame frame = null;

        if (turns.size() == NEXT_TURN_SIZE && frameNumber < MAX_FRAME_NUMBER) {
            frame = NormalFrame.from(frameNumber + MIN_FRAME_NUMBER);
        }

        return new NormalFrame(turns, frameNumber, frame, next);
    }

    @Override
    public Turns getTurns() {
        return turns;
    }
}
