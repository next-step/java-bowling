package bowling.frame;

import bowling.bowl.Bowl;
import bowling.bowl.First;

public class PitchCount {

    public static final int MAX_PITCH_CNT = 3;

    private int count;

    public void proceed(){
        count += 1;
    }

    public boolean canProceed(Bowl bowl){
        if(count == MAX_PITCH_CNT){
            return false;
        }

        if(count == 2 && bowl instanceof First){
            return false;
        }
        return true;
    }
}
