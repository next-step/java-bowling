package bowling.view;

import bowling.model.State;
import bowling.model.delivery.Delivery;
import bowling.model.frame.Frame;
import bowling.model.frame.Frames;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResultView {
    private static final String FRAME_BOARD = "|  NAME  |   01   |   02   |   03   |   04   |   05   |   06   |" +
                                              "   07   |   08   |   09   |   10   |";
    private static final String BOARD_FORMAT = " %-6s |";
    private static final String VERTICAL_LINE = "|";
    private static final String BLANK = " ";
    private static final Map<State, String> stateStringMap;

    static {
        stateStringMap = new HashMap<>();
        stateStringMap.put(State.GUTTER, "-");
        stateStringMap.put(State.STRIKE, "X");
        stateStringMap.put(State.SPARE, "/");
    }

    public static void printScoreBoard(int frameNo, String userName, Stream<Frame> frames) {
        System.out.println(FRAME_BOARD);
        int countOfBlank = Frames.TOTAL_FRAME_COUNT - frameNo;

        printUserName(userName);
        printScores(frames);
        printBlank(countOfBlank);
        System.out.println();
    }

    public static void printTotalScoreBoard(List<Integer> totalScores) {
        int countOfBlank = Frames.TOTAL_FRAME_COUNT - totalScores.size();

        printTotalScores(totalScores);
        printBlank(countOfBlank);
        System.out.println();
    }

    private static void printUserName(String userName) {
        System.out.print(VERTICAL_LINE);
        System.out.printf(BOARD_FORMAT, userName);
    }

    private static void printScores(Stream<Frame> frames) {
        frames.forEach(frame -> {
            printScore(frame.getDeliveries());
        });
    }

    private static void printScore(Stream<Delivery> deliveries) {
        String score = deliveries.map(ResultView::getScoreString)
                                 .collect(Collectors.joining ("|"));

        System.out.printf(BOARD_FORMAT, BLANK + score);
    }

    private static String getScoreString(Delivery delivery) {
        if (stateStringMap.containsKey(delivery.getState())) {
            return stateStringMap.get(delivery.getState());
        }

        return String.valueOf(delivery.getFallenPins());
    }

    private static void printBlank(int countOfBlank) {
        while (countOfBlank > 0) {
            System.out.printf(BOARD_FORMAT, "");
            countOfBlank--;
        }
    }

    private static void printTotalScores(List<Integer> totalScores) {
        System.out.print(VERTICAL_LINE);
        System.out.printf(BOARD_FORMAT, "");

        totalScores.forEach(totalScore -> {
            System.out.printf(BOARD_FORMAT, BLANK + totalScore);
        });

    }

}
