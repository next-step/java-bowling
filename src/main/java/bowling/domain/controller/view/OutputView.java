package bowling.domain.controller.view;

import bowling.domain.bowl.Bowl;
import bowling.domain.bowl.BowlType;
import bowling.domain.bowling.Bowling;
import bowling.domain.bowling.BowlingGame;
import bowling.domain.pin.Pin;
import bowling.domain.score.Score;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class OutputView {

    private static final StringBuilder STRING_BUILDER = new StringBuilder(1000);
    private static final int NUMBER_OF_FRAME = 10;
    private static final int FIRST_PIN_INDEX = 0;

    private static final String BOARD_HEAD = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |\n";
    private static final String BOARD_FORMAT = " %-4s |";
    private static final String BOARD_CLOSED_FORMAT = "| %-4s |";
    private static final String BOARD_EMPTY = "      |";
    private static final String BOARD_CLOSED_EMPTY = "|      |";

    private OutputView() {
    }

    public static void showBowlingGame(BowlingGame bowlingGame) {
        STRING_BUILDER.append(BOARD_HEAD);
        bowlingGame.bowlings()
                .forEach(OutputView::appendBowlingView);
        flush();
    }

    private static void appendBowlingView(Bowling bowling) {
        appendParticipantName(bowling.nameOfParticipant());
        appendBowlView(bowling.bowls());
        appendScoreView(bowling.scores());
    }

    private static void appendParticipantName(String nameOfParticipant) {
        String nameView = format(BOARD_CLOSED_FORMAT, nameOfParticipant);
        STRING_BUILDER.append(nameView);
    }

    private static void appendBowlView(List<Bowl> bowls) {
        for (Bowl bowl : bowls) {
            STRING_BUILDER.append(format(BOARD_FORMAT, getBowlView(bowl)));
        }
        appendEmptyBody(bowls);
    }

    private static String getBowlView(Bowl bowl) {
        List<Pin> pins = bowl.pins();
        if (pins.size() == 1) {
            return pinView(pins.get(0));
        }
        if (bowl.typeEquals(BowlType.SPARE)) {
            Pin firstPin = bowl.pins().get(FIRST_PIN_INDEX);
            return firstPin.getHitCount() + "|/";
        }
        if (bowl.typeEquals(BowlType.MISS) || bowl.typeEquals(BowlType.GUTTER)) {
            return pinView(pins.get(FIRST_PIN_INDEX)) + "|" + pinView(pins.get(FIRST_PIN_INDEX + 1));
        }
        return getFinalBowlView(bowl.pins());
    }

    private static String getFinalBowlView(List<Pin> pins) {
        return pins.stream()
                .map(OutputView::pinView)
                .collect(Collectors.joining("|"));
    }

    private static String pinView(Pin pin) {
        if (pin.isAllHit()) {
            return "X";
        }
        if (pin.isNoneHit()) {
            return "-";
        }
        return String.valueOf(pin.getHitCount());
    }

    private static void appendEmptyBody(List<?> objects) {
        int size = objects.size();
        for (int number = size; number < NUMBER_OF_FRAME; number++) {
            STRING_BUILDER.append(BOARD_EMPTY);
        }
        STRING_BUILDER.append("\n");
    }

    private static void appendScoreView(List<Score> scores) {
        STRING_BUILDER.append(BOARD_CLOSED_EMPTY);
        for (Score score : scores) {
            STRING_BUILDER.append(getScoreView(score));
        }
        appendEmptyBody(scores);
    }

    private static String getScoreView(Score score) {
        if (score.canCalculate()) {
            return format(BOARD_FORMAT, score.value());
        }
        return BOARD_EMPTY;
    }

    private static void flush() {
        System.out.println(STRING_BUILDER);
        STRING_BUILDER.setLength(0);
    }

}
