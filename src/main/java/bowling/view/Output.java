package bowling.view;

import bowling.BowlingGame;
import bowling.Frame.Frame;
import bowling.Frame.Frames;
import bowling.bowl.Bowl;
import bowling.player.Player;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Output {
    private static final String BOARD_HEAD = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String PLAYER_NAME = "|  %s |";
    private static final String RESULT_FRAME = "  %-3s |";
    private static final String EMPTY_RESULT_FRAME = "      |";
    private static final int MAX_FRAME_INDEX = 10;

    public static void printBoard(BowlingGame bowlingGame){
        System.out.println(BOARD_HEAD);
        System.out.println(getPlayerName(bowlingGame.getPlayer()) + getResultBody(bowlingGame.getFrames()));

    }

    private static String getPlayerName(Player player) {
        return String.format(PLAYER_NAME, player.getName());
    }

    public static String getResultBody(Frames frames) {
        String res =  frames.getFrames().stream()
                .map(Frame::getSymbol)
                .map(symbol -> String.format(RESULT_FRAME, symbol))
                .collect(Collectors.joining());

        String append = IntStream.range(0, MAX_FRAME_INDEX - frames.getFrames().size())
                .mapToObj(i->EMPTY_RESULT_FRAME)
                .collect(Collectors.joining());

        return res + append;
    }


}
