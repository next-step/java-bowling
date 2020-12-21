package bowling.domain;

/**
 * Created : 2020-12-16 오전 9:30
 * Developer : Seo
 */
public class NormalFrame extends Frame {
    public static final int NEXT_FRAME = 1;
    public static final int LAST_FRAME = 10;
    public static final int PREVIOUS_INDEX = 1;

    private final States states;
    private final int frameNo;

    public NormalFrame(int frameNo) {
        this.frameNo = frameNo;
        this.states = new States();
    }

    @Override
    public Frame next(int frameNo) {
        return this.frameNo + NEXT_FRAME == LAST_FRAME ?
                new FinalFrame(this.frameNo) : new NormalFrame(this.frameNo + NEXT_FRAME);
    }

    @Override
    public int getFrameNo() {
        return frameNo;
    }

    @Override
    public void bowl(Pins pins) {
        Bowl firstBowl = new FirstBowl();
        State state = firstBowl.stroke(pins);
        states.add(state);
    }

    @Override
    public void secondBowl(int frameNo, State state, Pins pins) {
        if (state instanceof Miss || state instanceof Gutter) {
            Score firstScore = state.getScore();
            Bowl secondBowl = new SecondBowl(firstScore);
            State secondState = secondBowl.stroke(pins);
            states.set(frameNo - PREVIOUS_INDEX, secondState);
        }
    }

    public States getStates() {
        return states;
    }

    @Override
    public State getLastState() {
        return this.states.getLast();
    }

    @Override
    public State getState(int index) {
        return this.states.getState(index);
    }

    @Override
    public int getScore(int index) {
        State state = this.states.getState(index);
        return state.getScore().getFrameScore();
    }

    // frameNo != this.states.getState(frameNo);
    public int getFirstScore(int frameNo) {
        State state = this.states.getState(frameNo);
        return state.getScore().getFirst().get();
    }
}
