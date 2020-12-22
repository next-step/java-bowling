package bowling.domain;

/**
 * Created : 2020-12-16 오전 9:30
 * Developer : Seo
 */
public class NormalFrame extends Frame {
    public static final int NEXT_FRAME = 1;
    public static final int LAST_FRAME = 10;

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
    public void secondBowl(int userIndex, State state, Pins pins) {
        if (state instanceof Miss || state instanceof Gutter) {
            Score firstScore = state.getScore();
            Bowl secondBowl = new SecondBowl(firstScore);
            State secondState = secondBowl.stroke(pins);
            states.set(userIndex, secondState);
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
    public int getScore(int userIndex) {
        State state = this.states.getState(userIndex);
        return state.getScore().getFrameScore();
    }

    @Override
    public int getFirstScore(int userIndex) {
        State state = this.states.getState(userIndex);
        return state.getScore().getFirst().get();
    }
}
