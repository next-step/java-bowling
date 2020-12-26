package bowling_step3.view;

import bowling_step3.domain.BowlingGame;
import bowling_step3.domain.Frame.Frame;
import bowling_step3.domain.Player;
import bowling_step3.domain.Scores;
import org.apache.logging.log4j.util.Strings;

import java.util.List;
import java.util.stream.IntStream;


public class OutputUi {

    public static final int MAX_FRAME_COUNT = 10;
    public static final String VERTICAL = " | ";
    public static final String SCORE_HEADER = " | NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final Object EMPTY = "    ";

    private OutputUi() {
    }

    public static void printInitHeader() {
        System.out.println(SCORE_HEADER);
    }

    private static void printName(String playerName) {
        System.out.print(String.format("%s%4s%s", VERTICAL, playerName, VERTICAL));
    }

    private static void printEmptySpace(int size) {
        IntStream.range(size, MAX_FRAME_COUNT)
                .mapToObj(i -> String.format("%s%s", EMPTY, VERTICAL))
                .forEach(System.out::print);
        System.out.println();
    }

    private static void printKnockDown(List<Frame> frames) {
        frames.stream()
                .map(frame -> String.format("%-4s%s", frame.getKnockDownExpression(), VERTICAL))
                .forEach(System.out::print);
        printEmptySpace(frames.size());
    }

    private static void printScores(Scores scores) {
        printName(Strings.EMPTY);

        scores.getScores().stream()
                .map(score -> String.format("%-4s%s", score, VERTICAL))
                .forEach(System.out::print);

        printEmptySpace(scores.size());
    }

    public static void printInitBowling(String playerName, BowlingGame gameFrames) {
        printInitHeader();

        printName(playerName);
        printKnockDown(gameFrames.getGameFrames().getFrames());
        printScores(gameFrames.addScores());
    }

    public static void printInitFrame(Player player, BowlingGame gameFrames) {
        printInitBowling(player.getName(), gameFrames);
    }
}
