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

    private static final StringBuilder STRING_BUILDER = new StringBuilder();
    private static final int NUMBER_OF_FRAME = 10;
    private static final String BOARD_OF_HEAD = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |\n|";
    private static final String BOARD_FORMAT = " %-4s |";
    private static final String BOARD_EMPTY = "      |";
    private static final int FIRST_PIN_INDEX = 0;

    private OutputView() {
    }

    public static void showBowlingGame(BowlingGame bowlingGame) {
        List<Bowling> bowlings = bowlingGame.bowlings();
        bowlings.forEach(OutputView::showBowling);

    }

    public static void showBowling(Bowling bowling) {
        System.out.print(BOARD_OF_HEAD);
        append(bowling.nameOfParticipant());

        appendBowlView(bowling.bowls());
        appendNewLine();

        appendScoreView(bowling.scores());
        appendNewLine();
        appendNewLine();
        flushBuilder();
    }

    private static void appendBowlView(List<Bowl> bowls) {
        bowls.forEach(bowl -> append(getBowlView(bowl)));
        appendEmptyBody(bowls);
    }

    public static String getBowlView(Bowl bowl) {
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

    public static void append(String string) {
        STRING_BUILDER.append(format(BOARD_FORMAT, string));
    }

    private static void appendEmptyBody(List<?> objects) {
        int size = objects.size();
        for (int number = size; number < NUMBER_OF_FRAME; number++) {
            STRING_BUILDER.append(BOARD_EMPTY);
        }
    }

    private static void appendScoreView(List<Score> scores) {
        STRING_BUILDER.append("|      |");
        for (Score score : scores) {
            STRING_BUILDER.append(getScoreView(score));
        }
        appendEmptyBody(scores);
    }

    private static String getScoreView(Score score) {
        if (score.canCalculate()) {
            return String.format(BOARD_FORMAT, score.value());
        }
        return BOARD_EMPTY;
    }

    private static void appendNewLine() {
        STRING_BUILDER.append("\n");
    }

    private static void flushBuilder() {
        System.out.println(STRING_BUILDER);
        STRING_BUILDER.setLength(0);
    }

}
