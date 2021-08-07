package bowling.view;

import bowling.dto.BowlingPlayResultData;
import java.util.stream.IntStream;

import static bowling.domain.frame.CommonFrame.END_NUMBER;
import static bowling.domain.frame.CommonFrame.START_NUMBER;

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

        IntStream.rangeClosed(START_NUMBER, END_NUMBER)
                .forEach(number -> print(String.format(FRAME_NUMBER_FORMAT, number)));

        printNextLine();
    }

    private static void printPlayer(BowlingPlayResultData bowlingPlayerDto) {
        print(String.format(NAME_FORMAT, bowlingPlayerDto.getPlayerName()));
        printNextLine();
    }

    private static void print(String statement) {
        System.out.print(statement);
    }

    private static void printNextLine() {
        print(System.lineSeparator());
    }
}
