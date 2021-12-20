package bowling.view.strategy;

import bowling.domain.state.State;

@FunctionalInterface
public interface StateToViewConvertStrategy<T extends State> {
    String convert(T state);
}
