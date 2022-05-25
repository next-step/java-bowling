package bowling.bowl;

import bowling.pin.Pins;

public class Second extends Running{

    private final Pins prePins;

    public Second(Pins prePins) {
        this.prePins = prePins;
    }

    @Override
    public Bowl pitch(Pins pins) {
        if(Pins.isSpare(prePins, pins)){
            return new Spare(prePins, pins);
        }
        if(Pins.isGutter(prePins, pins)){
            return new Gutter();
        }
        return new Miss(prePins, pins);
    }

    @Override
    public String getSymbol() {
        return prePins.getSymbol();
    }


    @Override
    public String toString(){
        return "[Second] pins: "+prePins;
    }

    @Override
    public boolean isSecond(){
        return true;
    }
}
