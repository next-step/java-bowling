package bowling;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import bowling.utils.StringUtils;

public final class OutputView {

    public static final int NAME_PADDING_SIZE = 5;
    public static final int FRAME_SIZE = 6;

    private OutputView() {}

    public static void printScoreBoard(Player player) {
        printBoardHeader();
        printPlayerNameAndStatus(player);
    }

    private static void printBoardHeader() {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
    }

    private static void printPlayerNameAndStatus(Player player) {
        final String playerName = player.playerName();
        final Frames frames = player.frames();
        final StringBuilder playerResultBuilder = new StringBuilder();

        playerResultBuilder.append("|").append(StringUtils.padLeft(playerName, NAME_PADDING_SIZE)).append(" ");
        for (Frame frame : frames.value()) {
            playerResultBuilder.append("|").append(StringUtils.alignCenter(frame.status(), FRAME_SIZE));
        }
        playerResultBuilder.append("|");

        System.out.println(playerResultBuilder);
    }
}
