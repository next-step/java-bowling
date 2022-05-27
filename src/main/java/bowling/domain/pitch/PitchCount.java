package bowling.domain.pitch;

public class PitchCount {

    public static final int MAX_PITCH_CNT = 3;

    private int count;

    public void proceed(){
        count += 1;
    }

    public boolean canProceed(){
        if(count == MAX_PITCH_CNT){
            return false;
        }
        return true;
    }
}
