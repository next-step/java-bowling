package domain;

import exception.DontTryException;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Pins {
    private List<Pin> pins = new ArrayList<>();

    public Pins() {
    }

    public Pins(List<Pin> pinCollection) {
        if(isRange(pinCollection)){
            throw new DontTryException();
        }
        pins.addAll(pinCollection);
    }

    private boolean isRange(List<Pin> pinCollection) {
        return sumFramePoint(pinCollection) > Pin.MAX_PIN_COUNT || sumFramePoint(pinCollection) < Pin.MIN_PIN_COUNT;
    }

    private int sumFramePoint(List<Pin> pinCollection) {
        int sum = 0;
        for(Pin pin : pinCollection){
            sum += pin.getPinCount();
        }
        return sum;
    }

    public void addPin(Pin pin){
        pins.add(pin);
        if(isRange(pins)){
            throw new DontTryException();
        }
    }

    public void addFinalPin(Pin pin){
        pins.add(pin);
        if(isStrike()){
           return;
        }
        if(isSpare()){
            return;
        }
        if(!isSpare() && isRange(pins)){
            throw new DontTryException();
        }
    }

    public boolean isStrike() {
        if(pins.isEmpty()){
            return false;
        }
        return sumFramePoint(pins) % (Pin.MAX_PIN_COUNT * pins.size()) == Pin.MIN_PIN_COUNT;
    }


    @Override
    public String toString() {
        return pins.stream().map(pin -> pin.toString()).collect(Collectors.joining(StringUtils.VERTICAL_BAR));
    }

    public boolean isSpare() {
        if(pins.size() > 2){
            int sum = 0;
            sum = sumFramePoint(sum);
            return sum == Pin.MAX_PIN_COUNT;
        }
        return sumFramePoint(pins) == Pin.MAX_PIN_COUNT;
    }

    private int sumFramePoint(int sum) {
        for (int i = 0; i < 2; i++) {
            sum += pins.get(i).getPinCount();
        }
        return sum;
    }

    public boolean isNotSpare() {
        if(pins.size() > 2){
            int sum = 0;
            sum = sumFramePoint(sum);
            return sum != Pin.MAX_PIN_COUNT;
        }
        return sumFramePoint(pins) < Pin.MAX_PIN_COUNT;
    }

}
