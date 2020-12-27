package bowling_step3.view;

import bowling_step3.domain.BowlingGame;
import bowling_step3.domain.Frame.FinalFrame;
import bowling_step3.domain.Frame.Frame;
import bowling_step3.domain.Frame.NormalFrame;
import bowling_step3.domain.Player;
import bowling_step3.domain.Scores;
import bowling_step3.domain.state.State;
import org.apache.logging.log4j.util.Strings;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class OutputUi {
    public static final int MAX_FRAME_COUNT = 10;
    public static final String VERTICAL = " | ";
    public static final String SCORE_HEADER = " | NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String EMPTY = "    ";

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

    private static boolean isFinalFrame(int index) {
        return index == MAX_FRAME_COUNT - 1;
    }

    private static String printNormalKnockDown(Frame frame) {
        NormalFrame normalFrame = (NormalFrame) frame;
        return normalFrame.getKnockDownExpression();
    }

    private static String printFinalKnockDown(Frame frame) {
        FinalFrame finalFrame = (FinalFrame) frame;
        return finalFrame.getStates().stream()
                .map(State::toString)
                .collect(Collectors.joining(VERTICAL));
    }

    private static void printAllFrame(int index, List<Frame> frames) {
        String format = "";
        if (!isFinalFrame(index)) {
            format += String.format("%-4s%s", printNormalKnockDown(frames.get(index)), VERTICAL);
        } else {
            format += String.format("%-4s%s", printFinalKnockDown(frames.get(index)), VERTICAL);
        }
        System.out.print(format);
    }

    private static void printKnockDown(List<Frame> frames) {
        IntStream.range(0, frames.size())
                .forEach(i -> printAllFrame(i, frames));
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
