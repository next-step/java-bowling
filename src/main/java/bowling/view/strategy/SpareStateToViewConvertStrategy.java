package bowling.view.strategy;

import bowling.domain.frame.Pin;
import bowling.domain.state.State;
import bowling.view.enums.PinView;

public class SpareStateToViewConvertStrategy implements StateToViewConvertStrategy {
    private static final String DELIMITER = "|";
    private static final String SPARE_VIEW = "/";
    private static final int FIRST_PIN_INDEX = 0;

    @Override
    public String convert(State state) {
        Pin firstPin = state.pins().get(FIRST_PIN_INDEX);
        return PinView.valueOf(firstPin).getDescription() + DELIMITER + SPARE_VIEW;
    }
}
