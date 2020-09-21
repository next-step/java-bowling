package bowling.domain.score;

import bowling.domain.frame.Frame;
import bowling.domain.pin.Pins;

public class ScoreConverter {

    private static final String GUTTER = "G";
    private static final String STRING_STRIKE = "X";
    private static final String STRING_MISS = "-";
    private static final String STRING_SPARE = "/";
    private static final String SCORE_SPLIT = "|";
    private static final int SECOND_ROLL = 1;
    private static final int BONUS_GAME = 2;
    private static final int STRIKE = 10;
    private static final int SPARE = 10;
    private static final int FINAL_FRAME = 10;
    private static final int FIRST_ROLL = 0;
    private static final int MAXIMUM_PIN_ROLL = 10;
    private static final int NO_HIT = 0;
    private static final int ROLL_ONCE = 1;
    private static final int ROLL_TWICE = 2;
    private static final int ROLL_THIRD = 3;
    private static final int TOTAL_PIN_BONUS_GAME = 30;
    private static final int TOTAL_PIN_NORMAL_GAME = 20;

    private ScoreConverter() {
    }

    public static String convert(Frame frame) {
        if (frame.getIndex() < FINAL_FRAME) {
            return normalFrameConvert(frame.getPins());
        }
        return finalFrameConvert(frame.getPins());
    }

    public static String normalFrameConvert(Pins pins) {
        StringBuilder sb = new StringBuilder();
        convertMiss(pins, sb);
        convertRollOnce(pins, sb);
        convertRollTwice(pins, sb);
        convertSpare(pins, sb);
        convertStrike(pins, sb);
        return sb.toString();
    }

    public static String finalFrameConvert(Pins pins) {
        StringBuilder sb = new StringBuilder();
        convertStrike(pins, sb);
        convertRollOnce(pins, sb);
        convertRollTwice(pins, sb);
        convertDoubleStrike(pins, sb);
        convertOneStrikeNormal(pins, sb);

        if (isBonusGame(pins)) {
            convertTurkey(pins, sb);
            convertDoubleStrikeBonusGame(pins, sb);
            convertSpareBonusGame(pins, sb);
            convertStrikeSpare(pins, sb);
        }
        return sb.toString();
    }

    private static void convertStrikeSpare(Pins pins, StringBuilder sb) {
        if (isFirstRollStrike(pins) && isSecondRollNormal(pins) && isLastSpare(pins)) {
            sb.append(STRING_STRIKE);
            sb.append(SCORE_SPLIT);
            sb.append(pins.getPinCount(SECOND_ROLL) == NO_HIT ? GUTTER : pins.getPinCount(SECOND_ROLL));
            sb.append(SCORE_SPLIT);
            sb.append(STRING_SPARE);
        }
    }

    private static boolean isSecondRollNormal(Pins pins) {
        return pins.getPinCount(SECOND_ROLL) != MAXIMUM_PIN_ROLL;
    }

    private static boolean isLastSpare(Pins pins) {
        return pins.getPinCount(SECOND_ROLL) + pins.getPinCount(BONUS_GAME) == SPARE;
    }

    private static void convertDoubleStrikeBonusGame(Pins pins, StringBuilder sb) {
        if (isDoubleStrike(pins) && pins.getPinCount(BONUS_GAME) < MAXIMUM_PIN_ROLL) {
            sb.append(STRING_STRIKE);
            sb.append(SCORE_SPLIT);
            sb.append(STRING_STRIKE);
            sb.append(SCORE_SPLIT);
            sb.append(pins.getPinCount(BONUS_GAME) == NO_HIT ? GUTTER : pins.getPinCount(BONUS_GAME));
        }
    }

    private static void convertSpareBonusGame(Pins pins, StringBuilder sb) {
        if (isSpare(pins) && pins.getTotalPins() < TOTAL_PIN_BONUS_GAME) {
            sb.append(pins.getPinCount(FIRST_ROLL));
            sb.append(SCORE_SPLIT);
            sb.append(STRING_SPARE);
            sb.append(SCORE_SPLIT);
            sb.append(pins.getPinCount(BONUS_GAME) == NO_HIT ? GUTTER : pins.getPinCount(BONUS_GAME));
        }
    }

    private static boolean isDoubleStrike(Pins pins) {
        return isFirstRollStrike(pins) && secondRollStrike(pins);
    }

    private static boolean secondRollStrike(Pins pins) {
        return pins.getPinCount(SECOND_ROLL) == STRIKE;
    }

    private static boolean isFirstRollStrike(Pins pins) {
        return pins.getPinCount(FIRST_ROLL) == STRIKE;
    }

    private static boolean isSpare(Pins pins) {
        return pins.getPinCount(FIRST_ROLL) + pins.getPinCount(SECOND_ROLL) == SPARE;
    }

    private static void convertRollOnce(Pins pins, StringBuilder sb) {
        if (isRollOnce(pins) && pins.getTotalPins() < MAXIMUM_PIN_ROLL) {
            sb.append(pins.getPinCount(FIRST_ROLL) == NO_HIT ? GUTTER : pins.getPinCount(FIRST_ROLL));
        }
    }

    private static void convertSpare(Pins pins, StringBuilder sb) {
        if (isRollTwice(pins) && pins.getTotalPins() == SPARE) {
            sb.append(pins.getPinCount(FIRST_ROLL));
            sb.append(SCORE_SPLIT);
            sb.append(STRING_SPARE);
        }
    }

    private static void convertRollTwice(Pins pins, StringBuilder sb) {
        if (isRollTwice(pins) && isPinHit(pins)) {
            sb.append(pins.getPinCount(FIRST_ROLL) == NO_HIT ? GUTTER : pins.getPinCount(FIRST_ROLL));
            sb.append(SCORE_SPLIT);
            sb.append(pins.getPinCount(SECOND_ROLL) == NO_HIT ? GUTTER : pins.getPinCount(SECOND_ROLL));
        }
    }

    private static boolean isPinHit(Pins pins) {
        return pins.getTotalPins() > 0 && pins.getTotalPins() < MAXIMUM_PIN_ROLL;
    }

    private static void convertMiss(Pins pins, StringBuilder sb) {
        if (isRollTwice(pins) && pins.getTotalPins() == NO_HIT) {
            sb.append(STRING_MISS);
        }
    }

    private static void convertStrike(Pins pins, StringBuilder sb) {
        if (isRollOnce(pins) && pins.getTotalPins() == MAXIMUM_PIN_ROLL) {
            sb.append(STRING_STRIKE);
        }
    }

    private static void convertTurkey(Pins pins, StringBuilder sb) {
        if (pins.getTotalPins() == 30) {
            sb.append(STRING_STRIKE);
            sb.append(SCORE_SPLIT);
            sb.append(STRING_STRIKE);
            sb.append(SCORE_SPLIT);
            sb.append(STRING_STRIKE);
        }
    }

    private static void convertOneStrikeNormal(Pins pins, StringBuilder sb) {
        if (isRollTwice(pins) && isFirstRollStrike(pins) && pins.getTotalPins() < TOTAL_PIN_NORMAL_GAME) {
            sb.append(STRING_STRIKE);
            sb.append(SCORE_SPLIT);
            sb.append(pins.getPinCount(SECOND_ROLL) == 0 ? GUTTER : pins.getPinCount(SECOND_ROLL));
        }
    }

    private static void convertDoubleStrike(Pins pins, StringBuilder sb) {
        if (isRollTwice(pins) && pins.getTotalPins() == TOTAL_PIN_NORMAL_GAME) {
            sb.append(STRING_STRIKE);
            sb.append(SCORE_SPLIT);
            sb.append(STRING_STRIKE);
        }
    }

    private static boolean isRollOnce(Pins pins) {
        return pins.rollCount() == ROLL_ONCE;
    }

    private static boolean isRollTwice(Pins pins) {
        return pins.rollCount() == ROLL_TWICE;
    }

    private static boolean isBonusGame(Pins pins) {
        return pins.rollCount() == ROLL_THIRD;
    }
}
