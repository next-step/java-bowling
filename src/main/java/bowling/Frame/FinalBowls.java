package bowling.Frame;

import bowling.bowl.Bowl;
import bowling.bowl.First;
import bowling.bowl.Gutter;
import bowling.bowl.Miss;
import bowling.pin.Pins;

import java.util.ArrayList;
import java.util.List;

public class FinalBowls {

    public static final int MAX_PITCH_CNT = 3;

    private final List<Bowl> bowls;
    private int count;

    public FinalBowls(){
        bowls = new ArrayList<>();
        bowls.add(new First());
    }

    public List<Bowl> getBowls() {
        return bowls;
    }

    public void pitch(Pins pins) {
        count += 1;
        Bowl curBowl = getCurBowl();
        Bowl resultBowl = curBowl.pitch(pins);
        changeBowl(resultBowl);

        if (resultBowl.isStrike() || resultBowl.isSpare()) {
            addBall(new First());
        }
    }

    public boolean canPitch(){
        Bowl curBowl = getCurBowl();
        if(curBowl instanceof Gutter || curBowl instanceof Miss){
            return false;
        }
        if(count == MAX_PITCH_CNT){
            return false;
        }
        if(count == 2 && getCurBowl().isSecond()){
            return false;
        }
        return true;
    }

    private void addBall(Bowl resultBowl) {
        bowls.add(resultBowl);
    }

    private void changeBowl(Bowl resultBowl) {
        bowls.remove(bowls.size()-1);
        bowls.add(resultBowl);
    }

    private Bowl getCurBowl() {
        return bowls.get(bowls.size()-1);
    }

}
