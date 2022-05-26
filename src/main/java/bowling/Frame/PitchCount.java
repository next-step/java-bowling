package bowling.Frame;

import bowling.bowl.Bowl;
import bowling.bowl.Second;

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

        if(count == 2 && bowl instanceof Second){
            return false;
        }
        return true;
    }
}
