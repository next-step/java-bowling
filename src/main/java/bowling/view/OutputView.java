package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Player;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {
    private static final String NAME_TEXT = "| NAME |";
    private static final String PLAYER_NAME_TEXT = "| %s |";
    private static final String FRAME_NUMBER_TEXT = "  %d  |";
    private static final String FRAME_RESULT_TEXT = "  %s  |";
    private static final int BOWLING_FIRST_FRAME = 1;

    public static void printFrame(Frame frame, Player player) {
        printFrameTop();
        printFrameResult(frame, player);
    }

    private static void printFrameTop() {
        System.out.print(NAME_TEXT);

        String text = IntStream.iterate(BOWLING_FIRST_FRAME, index -> index + 1)
                .limit(Frame.LAST_FRAME)
                .mapToObj(index -> String.format(FRAME_NUMBER_TEXT, index))
                .collect(Collectors.joining());

        System.out.println(text);
    }

    private static void printFrameResult(Frame frame, Player player) {
        String playerName = String.format(PLAYER_NAME_TEXT, player.getName());

        String playerResult = IntStream.iterate(BOWLING_FIRST_FRAME, index -> index + 1)
                .limit(Frame.LAST_FRAME)
                .mapToObj(frame::getResult)
                .map(result -> String.format(FRAME_RESULT_TEXT, result))
                .collect(Collectors.joining());

        System.out.println(playerName + playerResult);
    }
}
