package bowling.domain;

public class NormalFrame extends Frame {

    public NormalFrame(int frameNo) {
        this.frameNo = frameNo;
        this.pitch = new Pitch();
    }

    @Override
    public State bowling(Pin pin) {
        pitch.add(pin);
        if (pitch.isFinish()) {
            return State.Finish;
        }
        return State.NotFinish;
    }

    @Override
    public ShotHistory getShotHistory() {
        return pitch.getShotHistory();
    }

    @Override
    public boolean isGameEnd() {
        return false;
    }
}
