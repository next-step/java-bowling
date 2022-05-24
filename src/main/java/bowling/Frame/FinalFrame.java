package bowling.Frame;

import bowling.bowl.*;
import bowling.pin.Pins;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FinalFrame implements Frame{

    public static final int MAX_PITCH_CNT = 3;
    private final int index;
    private List<Bowl> bowls;
    private int count;

    private FinalFrame(int index, Bowl bowl, int count) {
        this.index = index;
        this.bowls = new ArrayList<>();
        this.bowls.add(bowl);
        this.count = count;
    }

    public FinalFrame(int index) {
        this(index, new First(), 0);
    }


    @Override
    public Frame pitch(Pins pins) {
        if(!canPitch()){
            throw new IllegalArgumentException("더 이상 투구할 수 없습니다.");
        }

        count += 1;
        Bowl curBowl = getCurBowl();
        Bowl resultBowl = curBowl.pitch(pins);
        changeBowl(resultBowl);

        if(resultBowl.isStrike() || resultBowl.isSpare()){
            addBall(new First());
            return this;
        }

        return this;
    }

    private boolean canPitch(){
        Bowl curBowl = getCurBowl();
        if(curBowl.isGutter() || curBowl.isMiss()){
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

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public Bowl getBowls() {
        return bowls.get(0);
    }

    @Override
    public boolean hasNext() {
        return canPitch();
    }

    @Override
    public String getSymbol() {
        return bowls.stream()
                .map(Bowl::getSymbol)
                .collect(Collectors.joining("|"));
    }


    @Override
    public String toString(){
        return "[final frame]" +
                "\nindex: "+index
                +"\nbowls: "+ bowls
                +"\ncount: "+count;
    }
}
