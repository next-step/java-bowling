package bowling.service.convert;

import bowling.domain.state.State;

import java.util.List;

@FunctionalInterface
public interface StateConvert {
    String convert(List<State> states);
}
