package bowling.domain;

public class FinalFrame extends Frame {

    public FinalFrame(Frame beforeFrame, Frame nextFrame) {
        super(beforeFrame, nextFrame);
    }

    @Override
    public void shot(int hitCount) {
        super.shot(hitCount);

        if (scores.isStrike()) {
            createNext().createNext();
            return;
        }

        if (scores.isSpare()) {
            createNext();
        }
    }
}
