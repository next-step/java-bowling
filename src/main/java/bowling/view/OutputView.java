package bowling.view;

import bowling.domain.Pin;
import bowling.domain.PlayerName;
import bowling.domain.dto.Record;
import bowling.domain.frame.FrameType;
import bowling.domain.frame.Frames;
import bowling.domain.state.StateType;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {

    private static final String FORMAT_BOWL = "%d프레임 투구 : %d%n";
    private static final String FORMAT_FRAME_NUMBER = "  %02d  ";
    private static final String FORMAT_FRAME = " %4s ";
    private static final String FORMAT_RECORD = "| %4s |%s|%n";

    private static final String BAR = "|";
    private static final String STRIKE = "X";
    private static final String SPARE = "/";
    private static final String GUTTER = "-";
    private static final String BLANK = "";

    private OutputView() {

    }

    public static void print(Frames frames, PlayerName playerName, Pin now) {
        System.out.printf(FORMAT_BOWL, frames.getFrameNumber(), now.getValue());
        printHeader();
        printRecord(frames, playerName);
    }

    public static void printStart(Frames frames, PlayerName playerName) {
        printHeader();
        printRecord(frames, playerName);
    }

    private static void printRecord(Frames frames, PlayerName playerName) {
        String headerName = String.format(FORMAT_FRAME, playerName);
        String headerPoint = String.format(FORMAT_FRAME, BLANK);

        String records = frames.getGameRecord().stream()
                .map(record -> String.format(FORMAT_FRAME, printRecord(record)))
                .collect(Collectors.joining(BAR));

        String points = frames.getGameRecord().stream()
                .map(record -> String.format(FORMAT_FRAME, printPoint(record.getPoint())))
                .collect(Collectors.joining(BAR));

        System.out.printf(FORMAT_RECORD, headerName, records);
        System.out.printf(FORMAT_RECORD, headerPoint, points);
    }

    private static String printRecord(Record record) {
        return Optional.ofNullable(record)
                .map(recordItem -> printScore(recordItem) + printBonus(recordItem))
                .orElse(BLANK);
    }

    private static String printScore(Record record) {
        if (record.getScores() == null || record.getScores().isEmpty()) {
            return BLANK;
        }

        if (record.getState() == StateType.STRIKE) {
            return STRIKE;
        }
        if (record.getState() == StateType.SPARE) {
            return getScore(record.getScores().get(0)) + BAR + SPARE;
        }
        if (record.getState() == StateType.MISS) {
            return getScore(record.getScores().get(0))
                    + BAR
                    + getScore(record.getScores().get(1));
        }
        return getScore(record.getScores().get(0));

    }

    private static String printBonus(Record input) {
        return Optional.ofNullable(input)
                .filter(record -> record.getKind() == FrameType.FINAL)
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
        System.out.printf(FORMAT_RECORD, headerName, headerFrame);
    }

    private static String printPoint(Integer point) {
        return Optional.ofNullable(point)
                .map(String::valueOf)
                .orElse(BLANK);
    }
}
