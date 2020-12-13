package bowling.model.frame;

public class NormalFrame extends Frame{

    private NormalFrame(FrameNumber frameNumber){
        this.frameNumber = frameNumber;
    }

    public static NormalFrame createFirstFrame(){
        return new NormalFrame(FrameNumber.from(1));
    }

    private NormalFrame next(){
        return new NormalFrame(frameNumber.next());
    }

    public Frame bowling(int fallenPins){
        state = state.bowling(fallenPins);

        if(frameNumber.beforeLast() && state.isFinished()){
            return new FinalFrame();
        }

        if(state.isFinished()){
            return next();
        }

        return this;
    }

    @Override
    public String toString() {
        return state.toString();
    }
}
