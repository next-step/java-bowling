package bowling.view;

import bowling.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class OutputView {

    private static final String BLANK = " ";
    private static final String BOARD_SEPARATOR = "|";
    private static final int SQUARE_LENGTH = 6;
    private static final int START_ROUND = 1;
    private static final int FINAL_ROUND = 10;
    private static final int FIRST_TRY = 0;
    private static final int BONUS_TRY = 2;

    private OutputView() {
    }

    public static void showScoreBoard(Frames frames, Player player) {
        showRoundTable();
        showScoreMarkTable(frames, player);
    }

    private static void showScoreMarkTable(Frames frames, Player player) {
        System.out.print(BOARD_SEPARATOR + centeredText(player.name()) + BOARD_SEPARATOR);
        System.out.println(eachFrameResult(frames.frames()) + BOARD_SEPARATOR);
        System.out.println();
    }

    private static String eachFrameResult(List<Frame> frames) {
        return frames.stream()
                .map(OutputView::frameResult)
                .collect(Collectors.joining(BOARD_SEPARATOR));
    }

    private static String frameResult(Frame frame) {
        if (frame.isNotYetStart()) {
            return centeredText(BLANK);
        }
        return centeredText(convertToScoreMarks(frame.pins()));
    }

    private static String convertToScoreMarks(Pins pins) {
        return scoreMarks(pins);
    }

    private static String scoreMarks(Pins pins) {
        List<String> marks = new ArrayList<>();
        marks.add(findMark(pins.firstPin()));
        if (pins.tryCount() == FIRST_TRY + 1) {
            return String.join(BOARD_SEPARATOR, marks);
        }
        marks.add(secondMark(pins));
        if (pins.tryCount() == BONUS_TRY + 1) {
            marks.add(findMark(pins.bonusPin()));
        }
        return String.join(BOARD_SEPARATOR, marks);
    }

    private static String findMark(Pin pin) {
        ScoreMark scoreMark = ScoreMark.of(pin.count(), TRUE);
        if (scoreMark == ScoreMark.STRIKE || scoreMark == ScoreMark.GUTTER) {
            return scoreMark.mark();
        }
        return String.valueOf(pin.count());
    }

    private static String secondMark(Pins pins) {
        ScoreMark scoreMark = ScoreMark.of(pins.toSecondCount(), FALSE);
        if (scoreMark == ScoreMark.SPARE) {
            return scoreMark.mark();
        }
        return String.valueOf(pins.secondTryCount());
    }

    private static void showRoundTable() {
        System.out.print(BOARD_SEPARATOR + centeredText("NAME") + BOARD_SEPARATOR);
        IntStream.rangeClosed(START_ROUND, FINAL_ROUND)
                .mapToObj(OutputView::convertRound)
                .forEach(System.out::print);
        System.out.println();
    }

    private static String convertRound(int round) {
        return centeredText(String.format("%02d", round)) + BOARD_SEPARATOR;
    }

    private static String centeredText(String text) {
        StringBuilder sb = new StringBuilder();
        sb.setLength((SQUARE_LENGTH - text.length()) / 2);
        sb.append(text);
        sb.setLength(SQUARE_LENGTH);
        return sb.toString().replace('\0', ' ');
    }
}
