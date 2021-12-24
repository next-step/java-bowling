package bowling.view;

import bowling.domain.Player;
import bowling.domain.frame.Frames;
import bowling.domain.state.end.EndState;
import bowling.domain.state.end.Results;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String DELIMITER = "|";
    private static final String HEADER_BOARD = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String NAME_BOARD_FORMAT = "|  %s |";
    private static final String NORMAL_GAME_RESULT_PRINT_FORMAT = "  %-3s |";


    public void printResult(Player player) {
        printHead();
        printPlayerName(player);
        printResult(player.getFrames());
    }

    private void printHead() {
        System.out.println(HEADER_BOARD);
    }

    private void printPlayerName(Player player) {
        System.out.printf(NAME_BOARD_FORMAT, player.getName());
    }

    private void printResult(Frames frames) {
        frames.getFrames().stream()
            .map(frame -> resultToString(frame.getResults()))
            .map(frameResult -> String.format(NORMAL_GAME_RESULT_PRINT_FORMAT, frameResult))
            .forEach(System.out::print);

        System.out.println();
    }

    private String resultToString(Results results) {
        return results.getResults().stream()
            .map(EndState::getPrintMark)
            .collect(Collectors.joining(DELIMITER));
    }
}