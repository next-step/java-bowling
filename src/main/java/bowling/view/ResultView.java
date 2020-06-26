package bowling.view;

import bowling.Validator;
import bowling.domain.Frame;
import bowling.utils.StringUtils;

import java.util.List;
import java.util.stream.IntStream;

public class ResultView {
    private static final int TEXT_LENGTH = 6;
    private static final String NAME_TEXT = "NAME";
    private static final String BAR_TEXT = "|";
    private static final String FRAME_NUMBER_STRING_FORMAT = "%02d";

    private ResultView() {
    }

    public static void printScoreBoard(String playerName, List<Frame> frames) {
        printBoardHeader();
        printBoardBody(playerName, frames);
    }

    public static void printEmptyScoreBoard(String playerName) {
        printBoardHeader();
        printEmptyBoardBody(playerName);
    }

    private static void printBoardHeader() {
        System.out.print(BAR_TEXT + StringUtils.center(NAME_TEXT, TEXT_LENGTH) + BAR_TEXT);
        IntStream.range(Validator.MIN_HIT_PIN, Validator.MAX_HIT_PIN).forEach(frameNumber ->
                System.out.print(StringUtils.center(String.format(FRAME_NUMBER_STRING_FORMAT, frameNumber + 1), TEXT_LENGTH) + BAR_TEXT));
        System.out.println();
    }

    private static void printBoardBody(String playerName, List<Frame> frames) {
        System.out.print(BAR_TEXT + StringUtils.center(playerName, TEXT_LENGTH) + BAR_TEXT);
        IntStream.range(Validator.MIN_HIT_PIN, Validator.MAX_HIT_PIN).forEach(i -> {
            System.out.print(StringUtils.center(frames.size() > i ? frames.get(i).toString() : "", TEXT_LENGTH));
            System.out.print(BAR_TEXT);
        });
        System.out.println();
        System.out.println();
    }

    private static void printEmptyBoardBody(String playerName) {
        System.out.print(BAR_TEXT + StringUtils.center(playerName, TEXT_LENGTH) + BAR_TEXT);
        IntStream.range(Validator.MIN_HIT_PIN, Validator.MAX_HIT_PIN).forEach(i -> System.out.print(StringUtils.center(StringUtils.EMPTY, TEXT_LENGTH) + BAR_TEXT));
        System.out.println();
        System.out.println();
    }
}
