package bowling.view;

import bowling.domain.BowlingGame;
import bowling.domain.dto.Record;
import bowling.domain.frame.KindOfFrame;
import bowling.domain.state.BowlingRecordState;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {

    private static String FORMAT_BOWL = "%d프레임 투구 : %d%n";
    private static String FORMAT_FRAME_NUMBER = "  %02d  ";
    private static String FORMAT_FRAME = " %4s ";
    private static String FORMAT_RECORD = "|%s|%s|";

    private static String BAR = "|";
    private static String STRIKE = "X";
    private static String SPARE = "/";
    private static String GUTTER = "-";
    private static String BLANK = "";

    private OutputView() {

    }

    public static void print(BowlingGame bowlingGame) {
        System.out.printf(FORMAT_BOWL, bowlingGame.getFrameNumber(), bowlingGame.getNow().getValue());
        printHeader();
        printRecord(bowlingGame);
    }

    public static void printStart(BowlingGame bowlingGame) {
        printHeader();
        printRecord(bowlingGame);
    }

    private static void printRecord(BowlingGame bowlingGame) {
        String headerName = String.format(FORMAT_FRAME, bowlingGame.getPlayerName());

        String records = bowlingGame.getGameRecord().stream()
                .map(record -> String.format(FORMAT_FRAME, printRecord(record)))
                .collect(Collectors.joining(BAR));

        System.out.println(String.format(FORMAT_RECORD, headerName, records));
    }

    private static String printRecord(Record record) {
        return Optional.ofNullable(record)
                .map(recordItem -> printScore(recordItem) + printBonus(recordItem))
                .orElse("");
    }

    private static String printScore(Record record) {

        if (record.getState() == BowlingRecordState.STRIKE) {
            return STRIKE;
        }
        if (record.getState() == BowlingRecordState.SPARE) {
            return getScore(record.getScores().get(0)) + BAR + SPARE;
        }
        if (record.getState() == BowlingRecordState.MISS) {
            return getScore(record.getScores().get(0))
                    + BAR
                    + getScore(record.getScores().get(1));
        }
        return "";

    }

    private static String printBonus(Record input) {
        return Optional.ofNullable(input)
                .filter(record -> record.getKind() == KindOfFrame.FINAL)
                .map(Record::getBonus)
                .map(bonus -> BAR + bonus).orElse("");
    }

    private static String getScore(Integer integer) {
        if (integer == null) {
            return BLANK;
        }
        if (integer == 0) {
            return GUTTER;
        }
        return String.valueOf(integer);
    }

    private static void printHeader() {

        String headerName = String.format(FORMAT_FRAME, "NAME");
        String headerFrame = IntStream.range(1, 11)
                .mapToObj(i -> String.format(FORMAT_FRAME_NUMBER, i))
                .collect(Collectors.joining(BAR));
        System.out.println(String.format(FORMAT_RECORD, headerName, headerFrame));
    }
}
