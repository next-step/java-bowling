package bowling.domain;

/**
 * Created : 2020-12-16 오전 9:09
 * Developer : Seo
 */
public class FinalFrame extends Frame {
    public static final int FINAL_FRAME = 10;
    public static final int PREVIOUS_INDEX = 1;

    private final States states;
    private final int frameNo;

    public FinalFrame(int frameNo) {
        this.frameNo = frameNo;
        this.states = new States();
    }

    @Override
    public Frame next(int frameNo) {
        return null;
    }

    @Override
    public int getFrameNo() {
        return FINAL_FRAME;
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
            state = secondBowl.stroke(pins);
            states.set(frameNo - PREVIOUS_INDEX, state);
        }
    }

    @Override
    public State getState(int userIndex) {
        return this.states.getState(userIndex);
    }

    @Override
    public State getLastState() {
        return this.states.getLast();
    }

    @Override
    public int getScore(int index) {
        State state = this.states.getState(index);
        return state.getScore().getFrameScore();
    }

    @Override
    public int getFirstScore(int frameNo) {
        State state = this.states.getState(frameNo);
        return state.getScore().getFirst().get();
    }
}
