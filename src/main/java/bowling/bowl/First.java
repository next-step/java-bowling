package bowling.bowl;

import bowling.pin.Pins;

public class First extends Running{
    @Override
    public Bowl pitch(Pins pins) {
        if(pins.isStrike()){
            return new Strike();
        }
        return new Second(pins);
    }

    @Override
    public String getSymbol() {
        return "first";
    }

    @Override
    public String toString(){
        return "[First]";
    }


    @Override
    public boolean isFirst(){
        return true;
    }
}
