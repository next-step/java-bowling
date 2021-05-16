package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    private static final int FIRST_FRAME = 1;
    private static final int LAST_NORMAL_FRAME = 9;
    private static final int FINAL_FRAME = 10;

    private int frameCount;
    private List<Frame> frames;

    public Frames(){
        frameCount = toIndex(FIRST_FRAME);
        frames = new ArrayList<>();
    }

    public void bowl(int pin){

    }

    public void moveFrame(){

    }

    public boolean currentFrameNeedBonus(){
        return false;
    }

    public Frame currentFrame(){
        return null;
    }

    private int toIndex(int value){
        return value-1;
    }
}
