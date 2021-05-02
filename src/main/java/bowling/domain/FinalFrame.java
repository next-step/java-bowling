package bowling.domain;

public class FinalFrame extends Frame {
    public FinalFrame() {
        super();
        this.availability = 3;
    }

    @Override
    public Frame createFrame(int frameNumber) {
        throw new IllegalStateException("마지막 프레임에서는 프레임을 생성할 수 없습니다.");
    }

    @Override
    public boolean isFinalFrame() {
        return true;
    }

    @Override
    protected void updateCondition(int inputScore) {
        if (inputScore == 10) {
            score.fillPins();
            return;
        }
        if (score.isPinCleared()) {
            score.fillPins();
            return;
        }
        if (availability == 1 && !score.isPinCleared()) {
            availability = 0;
        }
    }
}
