package bowling.service;

import bowling.ui.ConsoleInputView;

import java.util.function.Function;

public class BowlingService {

    public <R, T> T getInstanceFromInput(ConsoleInputView<R> inputView, Function<R, T> supplier) {
        inputView.print();
        return supplier.apply(inputView.getConsoleInput());
    }
}