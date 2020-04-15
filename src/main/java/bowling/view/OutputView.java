package bowling.view;

import bowling.controller.BowlingGame;
import bowling.domain.frame.state.State;
import bowling.domain.frame.state.States;
import bowling.domain.player.Player;
import bowling.view.format.StateFormatter;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DELIMITER = "|";
    private static final String EMPTY_STRING = "";
    private static final String FRAMES_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";

    private OutputView() {}

    private static void printFramesHeader() {
        System.out.println(FRAMES_HEADER);
    }

    public static void printOverHead(BowlingGame game) {
        printFramesHeader();
        printPlayerName(game.getCurrentPlayer());
        //printFrameState(game.getCurrentPlayer(), game.getResult());
        printFrameState(game.getStates());
        System.out.println();
    }

    private static void printPlayerName(final Player currentPlayer) {
        System.out.print(formatName(currentPlayer.name()));
    }

    private static String formatName(final String name) {
        return DELIMITER + name + DELIMITER;
    }

    private static void printFrameState(final Player currentPlayer, final List<String> states) {
        String formatName = formatName(currentPlayer.name());
        String formatStates = states.stream()
                                    .map(StateFormatter::format)
                                    .collect(Collectors.joining(DELIMITER, EMPTY_STRING, DELIMITER));
        System.out.println(formatName + formatStates);
    }

    private static void printFrameState(final List<States> states) {
        StringBuilder stateExpressionBuilder = new StringBuilder();
        for (States histories : states) {
            System.out.print(printStateHistories(histories));
        }
        System.out.println();
    }

    private static String printStateHistories(final States states) {
        if (states.isEmpty()) {
            return formatState(EMPTY_STRING) + DELIMITER;
        }

        String expression = EMPTY_STRING;
        for (State state : states.getList()) {
            expression += addJoiner(state, replaceNull(state));
        }
        expression = formatState(expression);
        expression += DELIMITER;
        return expression;
    }

    private static String replaceNull(final State state) {
        if (state == null) {
            return EMPTY_STRING;
        }
        return state.toResult();
    }

    private static String addJoiner(final State state, final String expression) {
        if (!state.isTurnOver()) {
            return expression + DELIMITER;
        }
        return expression;
    }

    private static String formatState(final String expression) {
        return StateFormatter.format(expression);
    }
}
