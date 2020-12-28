package bowling_step3.view;

import bowling_step3.domain.BowlingGames;
import bowling_step3.domain.Frame.FinalFrame;
import bowling_step3.domain.Frame.Frame;
import bowling_step3.domain.Frame.NormalFrame;
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

    private static String printAllFrame(int index, List<Frame> frames) {
        if (!isFinalFrame(index)) {
            return String.format("%-4s%s", printNormalKnockDown(frames.get(index)), VERTICAL);
        }
        return String.format("%-4s%s", printFinalKnockDown(frames.get(index)), VERTICAL);
    }

    private static void printKnockDown(List<Frame> frames) {
        int bound = frames.size();
        StringBuilder builder = new StringBuilder();
        IntStream.range(0, bound)
                .mapToObj(i -> printAllFrame(i, frames))
                .forEach(builder::append);
        System.out.print(builder);

        printEmptySpace(frames.size());
    }

    private static void printScores(Scores scores) {
        printName(Strings.EMPTY);

        scores.getScores().stream()
                .map(score -> String.format("%-4s%s", score, VERTICAL))
                .forEach(System.out::print);

        printEmptySpace(scores.size());
    }

    public static void printInitBowling(BowlingGames bowlingGames) {
        for (int i = 0; i < bowlingGames.size(); i++) {
            printName(bowlingGames.player(i).getName());
            printKnockDown(bowlingGames.frame(i).getFrames());
            printScores(bowlingGames.sumScore(i));
        }
    }

    public static void printInitFrame(BowlingGames bowlingGames) {
        printInitBowling(bowlingGames);
    }
}
