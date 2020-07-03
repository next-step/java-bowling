package bowling.strategy;

import bowling.pin.domain.Pin;
import bowling.view.InputView;

public class NormalInputBowlingStrategy implements BowlingStrategy {

    @Override
    public Pin drawBowl(Pin pin, int index) {
        /*if (pin.isAllClear()) {
            return new Pin(0, 0);
        }
        return new Pin(pin.leftPins(), InputView.inputDrawPins(index));*/
        return null;
    }

}
