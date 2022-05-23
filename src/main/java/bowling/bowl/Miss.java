package bowling.bowl;

import bowling.pin.Pins;

public class Miss extends Ended{
    private final Pins firstPin;
    private final Pins secondPin;


    public Miss(Pins firstPin, Pins secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    @Override
    public String toString(){
        return "[Miss first: "+firstPin
                +" second: "+secondPin+"]";
    }

    @Override
    public boolean isMiss(){
        return true;
    }

    @Override
    public String getSymbol() {
        return firstPin.getSymbol()+"|"+secondPin.getSymbol();
    }
}
