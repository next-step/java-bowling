package bowling.Frame;

import bowling.bowl.Bowl;
import bowling.pin.Pins;
import java.util.stream.Collectors;

public class FinalFrame implements Frame{

    private static final String CAN_NOT_PITCH_MESSAGE = "더 이상 투구할 수 없습니다.";

    private final int index;
    private FinalBowls bowls;

    private FinalFrame(int index, FinalBowls bowls) {
        this.index = index;
        this.bowls = bowls;
    }

    public FinalFrame(int index) {
        this(index, new FinalBowls());
    }

    @Override
    public Frame pitch(Pins pins) {
        if(!bowls.canPitch()){
            throw new IllegalArgumentException(CAN_NOT_PITCH_MESSAGE);
        }
        bowls.pitch(pins);
        return this;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public Bowl getBowls() {
        return bowls.getBowls().get(0);
    }

    @Override
    public boolean hasNext() {
        return bowls.canPitch();
    }

    @Override
    public String getSymbol() {
        return bowls.getBowls().stream()
                .map(Bowl::getSymbol)
                .collect(Collectors.joining("|"));
    }

    @Override
    public String toString(){
        return "[final frame]" +
                "\nindex: "+index
                +"\nbowls: "+ bowls;
    }
}
