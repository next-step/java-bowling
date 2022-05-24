package bowling.Frame;

import bowling.bowl.Bowl;
import bowling.bowl.First;
import bowling.bowl.Second;
import bowling.pin.Pins;

public class NormalFrame implements Frame{
    private final static int LAST_NORMAL_FRAME_INDEX = 9;
    private final int index;
    private Bowl bowl;

    private NormalFrame(int index, Bowl bowl) {
        this.index = index;
        this.bowl = bowl;
    }

    public NormalFrame(int index) {
        this.index = index;
        this.bowl = new First();
    }

    @Override
    public Frame pitch(Pins pins){
        bowl = bowl.pitch(pins);
        if(bowl.isEnd()){
            return createNextFrame();
        }
        return this;
    }

    private Frame createNextFrame(){
        if(index == LAST_NORMAL_FRAME_INDEX){
            return new FinalFrame(index+1);
        }
        return new NormalFrame(index+1, new First());
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public Bowl getBowl() {
        return bowl;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public String toString(){
        return "index: "+index +" | bowl: "+bowl;
    }
}
