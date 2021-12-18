package bowling.model.state;

import bowling.model.Pins;

public class Ready extends Running {

    @Override
    public State bowl(Pins knockedDownPin) {
        if(knockedDownPin.isStrike()) {
            return new Strike();
        }
        return new FirstBowl(knockedDownPin);
    }

    @Override
    public String getDesc() {
        return "";
    }
}
