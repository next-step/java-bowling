package bowling.bowl;

import bowling.pin.Pins;

public class Spare extends Ended{

    private final Pins firstPin;
    private final Pins secondPin;


    public Spare(Pins firstPin, Pins secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }


    @Override
    public String toString(){
        return "[Spare first: "+firstPin
                +" second: "+secondPin+"]";
    }

    @Override
    public String getSymbol() {
        return firstPin.getSymbol()+"|/";
    }
}
