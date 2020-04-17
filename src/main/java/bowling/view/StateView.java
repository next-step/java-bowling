package bowling.view;

import bowling.domain.frame.state.State;
import bowling.domain.frame.state.States;
import bowling.view.format.StateFormatter;

import java.util.List;
import java.util.stream.Collectors;

public class StateView {

    private StateView() {}

    public static void print(final List<States> states) {
        for (States history : states) {
            System.out.print(printStates(history));
        }
        System.out.println();
    }

    private static String printStates(final States states) {
        if (states.isEmpty()) {
            return formatState(OutputView.EMPTY_STRING) + OutputView.DELIMITER;
        }

        String expression = states.getList()
                                  .stream()
                                  .map(StateView::replaceNull)
                                  .collect(Collectors.joining(OutputView.DELIMITER));
        expression = formatState(expression);
        expression += OutputView.DELIMITER;
        return expression;
    }

    private static String replaceNull(final State state) {
        if (state == null) {
            return OutputView.EMPTY_STRING;
        }
        return state.toResult();
    }

    private static String formatState(final String expression) {
        return StateFormatter.format(expression);
    }
}
