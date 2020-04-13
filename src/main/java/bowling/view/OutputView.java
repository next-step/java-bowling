package bowling.view;

import bowling.frame.domain.Frame;
import bowling.frame.domain.Frames;
import bowling.game.dto.BowlingGameDto;
import bowling.player.domain.Player;

import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class OutputView {

    private static final String HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String HEADER_BONUS = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |   10   |";
    private static final String SCORE = "|  {0} |  {1} |  {2} |  {3} |  {4} |  {5} |  {6} |  {7} |  {8} |  {9} |  {10} |";
    private static final String BLANK = "   ";
    private static final String ALIGN = "%3s";
    private static final int ZERO_INDEX = 0;
    private static final int FIRST_FRAME = 1;
    private static final int LAST_FRAME = 10;
    private static final PrintStream CONSOLE = System.out;

    public static void print(BowlingGameDto gameDto) {
        Frames game = gameDto.getFrames();
        Player player = game.getPlayer();
        List<Frame> frames = game.getFrames();
        printHeader(game);
        List<String> scores = frames.stream()
                .map(frame -> String.format(ALIGN, frame.view()))
                .collect(toList());
        MessageFormat messageFormat = new MessageFormat(SCORE);
        CONSOLE.println(messageFormat.format(format(player.getName(), scores)));
    }

    private static void printHeader(Frames game) {
        if (game.getSize() == LAST_FRAME && game.getFrames().get(LAST_FRAME - FIRST_FRAME).view().length() > 3) {
            CONSOLE.println(HEADER_BONUS);
            return;
        }
        CONSOLE.println(HEADER);
    }

    private static Object[] format(String playerName, List<String> scores) {
        scores.addAll(ZERO_INDEX, Arrays.asList(playerName));
        for (int i = FIRST_FRAME; i <= LAST_FRAME; i++) {
            if (scores.size() <= i) {
                scores.add(BLANK);
            }
        }
        return scores.toArray();
    }
}
