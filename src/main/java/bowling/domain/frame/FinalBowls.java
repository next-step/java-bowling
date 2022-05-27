package bowling.domain.frame;

import bowling.domain.bowl.Ready;
import bowling.domain.bowl.Miss;
import bowling.domain.bowl.Bowl;
import bowling.domain.bowl.Gutter;
import bowling.domain.bowl.Spare;
import bowling.domain.bowl.Strike;
import bowling.domain.pin.Pins;
import bowling.domain.exception.CannotPitchException;

import java.util.ArrayList;
import java.util.List;

public class FinalBowls {

    private final List<Bowl> bowls;
    private final PitchCount pitchCount;

    public FinalBowls(){
        bowls = new ArrayList<>();
        bowls.add(new Ready());
        pitchCount = new PitchCount();
    }

    public List<Bowl> getBowls() {
        return bowls;
    }

    public void pitch(Pins pins) {
        if(!canProceed()){
            throw new CannotPitchException();
        }

        Bowl curBowl = getCurBowl();

        Bowl resultBowl = curBowl.pitch(pins);
        pitchCount.proceed();

        changeBowl(resultBowl);

        if (canProceed() && (resultBowl instanceof Strike || resultBowl instanceof Spare)) {
            addBall(new Ready());
        }
    }

    public boolean canProceed(){
        Bowl curBowl = getCurBowl();
        if(curBowl instanceof Gutter || curBowl instanceof Miss){
            return false;
        }
        if(!pitchCount.canProceed()){
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
