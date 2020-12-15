package bowling.model.frame;

public class FinalFrame extends Frame {

    private int countOfTry = 0;

    public FinalFrame() {
        frameNumber = FrameNumber.from(FrameNumber.MAX_FRAME_NUMBER);
    }

    @Override
    public Frame bowling(int fallenPins) {
        countOfTry++;
        states.bowling(fallenPins);

        if (isAbleToBonusFrame()) {
            states.changeLastToBonusFrame();
        }

        return this;
    }

    private boolean isAbleToBonusFrame() {
        return countOfTry < 3 && states.isMaxScore();
    }
}
