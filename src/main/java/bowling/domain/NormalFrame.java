package bowling.domain;

public class NormalFrame extends Frame {

    public boolean hasSecond() {
        return !pinNumbers.index(0).isStrike();
   }

    @Override
    public boolean hasNext() {
        if (pinNumbers.size() == 2) {
            return false;
        }
        return pinNumbers.size() == 0 || hasSecond();
    }

    @Override
    public FrameStrategy nextFrame(int frameNumber) {
        if (frameNumber == 9) {
            next = new FinalFrame();
            return next;
        }
        next = new NormalFrame();
        return next;
    }
}
