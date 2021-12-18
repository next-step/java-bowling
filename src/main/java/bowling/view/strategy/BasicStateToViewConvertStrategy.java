package bowling.view.strategy;

import bowling.domain.state.State;
import bowling.view.enums.PinView;

import java.util.stream.Collectors;

public class BasicStateToViewConvertStrategy implements StateToViewConvertStrategy {

    public static final String DELIMITER = "|";

    @Override
    public String convert(State state) {
        return state.pins().stream()
                .map(PinView::valueOf)
                .map(PinView::getDescription)
                .collect(Collectors.joining(DELIMITER));
    }
}
