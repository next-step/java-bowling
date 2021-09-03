package bowling.domain;

public class NormalFrame implements Frame{
    private static final int FINAL_NORMAL_FRAME_NUMBER = 9;

    private final int frameNumber;

    private NormalState normalState;
    private NormalFrame next;

    public NormalFrame(int frameNumber){
        this.frameNumber = frameNumber;
        normalState = new NormalState();
    }
    @Override
    public void bowl(Pins pins) {
        normalState.bowl(pins);
    }

    @Override
    public Pins getFirstPin(){
        return normalState.getFirstPin();
    }

    @Override
    public Pins getSecondPin() {
        return normalState.getSecondPin();
    }

    @Override
    public boolean isFinish(){
        if(normalState.isFinish()){
            next = new NormalFrame(frameNumber + 1);
            return true;
        }
        return false;
    }

    public Frame next() {
        if(frameNumber + 1 == FINAL_NORMAL_FRAME_NUMBER){
            return null;
        }
        return next;
    }
}
