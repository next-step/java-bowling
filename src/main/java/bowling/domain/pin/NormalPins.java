package bowling.domain.pin;

import java.util.ArrayList;
import java.util.List;

public class NormalPins extends Pins{

    private static final int NOT_PLAYED = 0;
    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private static final int PIN_SIZE = 2;

    private List<Pin> pins;

    public NormalPins(){
        pins = new ArrayList<>();
    }

    @Override
    public void bowl() {

    }

    @Override
    public boolean isStrike() {
        if(pins.size()==NOT_PLAYED){
            return false;
        }
        return pins.get(FIRST).didClear();
    }

    @Override
    public boolean isSpare() {
        return false;
    }
}
