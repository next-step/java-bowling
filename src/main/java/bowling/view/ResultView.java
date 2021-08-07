package bowling.view;

import bowling.dto.BowlingPlayResultData;
import java.util.stream.IntStream;

import static bowling.domain.frame.Frame.END_NUMBER_OF_FRAME;
import static bowling.domain.frame.Frame.START_NUMBER_OF_FRAME;

public class ResultView {
    private static final String NAME_FORMAT = "| %4s |";
    private static final String FRAME_NUMBER_FORMAT = "  %02d  |";

    private ResultView() {}

    public static void printBowlingScore(BowlingPlayResultData resultData) {
        printHeader();
        printPlayer(resultData);
    }

    private static void printHeader() {
        print(String.format(NAME_FORMAT, "NAME"));

        IntStream.rangeClosed(START_NUMBER_OF_FRAME, END_NUMBER_OF_FRAME)
                .forEach(number -> print(String.format(FRAME_NUMBER_FORMAT, number)));

        printNextLine();
    }

    private static void printPlayer(BowlingPlayResultData bowlingPlayerDto) {
        print(String.format(NAME_FORMAT, bowlingPlayerDto.getName()));
        printNextLine();
    }

    private static void print(String statement) {
        System.out.print(statement);
    }

    private static void printNextLine() {
        print(System.lineSeparator());
    }
}
