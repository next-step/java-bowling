package bowling.view;

import bowling.domain.state.StateType;
import bowling.dto.BowlingRecord;
import bowling.dto.FrameRecord;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {

    private static final String FORMAT_FRAME_NUMBER = "   %02d   ";
    private static final String FORMAT_FRAME = " %5s  ";
    private static final String FORMAT_RECORD = "| %6s |%s|%n";
    private static final String FORMAT_BOWL_NOW = "%s's turn : %d";

    private static final String BAR = "|";
    private static final String STRIKE = "X";
    private static final String SPARE = "/";
    private static final String GUTTER = "-";
    private static final String BLANK = "";

    private OutputView() {

    }

    public static void printStart(List<BowlingRecord> bowlingRecords) {
        printHeader();
        bowlingRecords.forEach(record -> {
            printFrameRecord(record);
            printPointsStart();
        } );
    }

    public static void print(List<BowlingRecord> bowlingRecords) {
        printHeader();
        bowlingRecords.forEach(record -> {
            printFrameRecord(record);
            printPoints(record);
        } );
    }

    private static void printFrameRecord(BowlingRecord bowlingRecord) {
        String headerName = String.format(FORMAT_FRAME, bowlingRecord.getPlayerName());

        String records = bowlingRecord.getFrameRecords().stream()
                .map(record -> String.format(FORMAT_FRAME, printFrameRecord(record)))
                .collect(Collectors.joining(BAR));

        records = addRestEmptyRecord(records, bowlingRecord.getNth());

        System.out.printf(FORMAT_RECORD, headerName, records);
    }

    private static void printPoints(BowlingRecord bowlingRecord){
        String points = bowlingRecord.getFrameRecords().stream()
                .map(record -> String.format(FORMAT_FRAME, printPoint(record.getPoint())))
                .collect(Collectors.joining(BAR));

        points = addRestEmptyRecord(points, bowlingRecord.getNth());

        String headerPoint = String.format(FORMAT_FRAME, BLANK);

        System.out.printf(FORMAT_RECORD, headerPoint, points);
    }

    private static void printPointsStart(){
        String points = IntStream.range(0,10)
                .mapToObj(i -> String.format(FORMAT_FRAME, BLANK))
                .collect(Collectors.joining(BAR));

        String headerPoint = String.format(FORMAT_FRAME, BLANK);

        System.out.printf(FORMAT_RECORD, headerPoint, points);
    }

    private static String addRestEmptyRecord(String records, int nth) {
        String rest = IntStream.range(nth, 10)
                .mapToObj(a -> String.format(FORMAT_FRAME, BLANK))
                .collect(Collectors.joining(BAR));
        if (nth < 10) {
            rest = BAR + rest;
        }
        return records + rest;
    }

    private static String printFrameRecord(FrameRecord frameRecord) {
        return Optional.ofNullable(frameRecord)
                .map(frameRecordItem -> printScore(frameRecordItem) + printBonus(frameRecordItem))
                .orElse(BLANK);
    }

    private static String printScore(FrameRecord frameRecord) {
        if (frameRecord.getScores() == null || frameRecord.getScores().isEmpty()) {
            return BLANK;
        }

        if (frameRecord.getState() == StateType.STRIKE) {
            return STRIKE;
        }
        if (frameRecord.getState() == StateType.SPARE) {
            return getScore(frameRecord.getScores().get(0)) + BAR + SPARE;
        }
        if (frameRecord.getState() == StateType.MISS) {
            return getScore(frameRecord.getScores().get(0))
                    + BAR
                    + getScore(frameRecord.getScores().get(1));
        }
        return getScore(frameRecord.getScores().get(0));

    }

    private static String printBonus(FrameRecord input) {

        if (input.getBonus() == null && input.getBonus().isEmpty()) {
            return BLANK;
        }

        return input.getBonus().stream()
                .map(bonus -> BAR + bonus)
                .collect(Collectors.joining(BLANK));
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
        System.out.printf(FORMAT_RECORD, headerName, headerFrame);
    }

    private static String printPoint(Integer point) {
        return Optional.ofNullable(point)
                .map(String::valueOf)
                .orElse(BLANK);
    }

    public static void printBowlNow(String playerName, int now) {
        String bowlNow = String.format(FORMAT_BOWL_NOW, playerName, now);
        System.out.println(bowlNow);
    }
}
