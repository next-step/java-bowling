package bowling.domain.score;

import bowling.domain.frame.Frame;
import bowling.domain.pin.Pins;

public class ScoreConverter {

    public static final int SECOND_ROLL = 1;
    public static final int BONUS_GAME = 2;
    public static final int STRIKE = 10;
    public static final int SPARE = 10;
    public static final int FINAL_FRAME = 10;
    public static final String GUTTER = "G";
    public static final String STRING_STRIKE = "X";
    public static final int FIRST_ROLL = 0;
    public static final String STRING_MISS = "-";
    public static final String STRING_SPARE = "/";
    public static final String SCORE_SPLIT = "|";

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
        if (firstRollStrike(pins) && secondRollNormal(pins) && isLastSpare(pins)
        ) {
            sb.append(STRING_STRIKE);
            sb.append(SCORE_SPLIT);
            sb.append(pins.getPinCount(SECOND_ROLL) == 0 ? GUTTER : pins.getPinCount(SECOND_ROLL));
            sb.append(SCORE_SPLIT);
            sb.append(STRING_SPARE);
        }
    }

    private static boolean secondRollNormal(Pins pins) {
        return pins.getPinCount(SECOND_ROLL) != 10;
    }

    private static boolean isLastSpare(Pins pins) {
        return  pins.getPinCount(SECOND_ROLL) +  pins.getPinCount(BONUS_GAME) == SPARE;
    }

    private static void convertDoubleStrikeBonusGame(Pins pins, StringBuilder sb) {
        if (isDoubleStrike(pins) && pins.getPinCount(BONUS_GAME) < 10) {
            sb.append(STRING_STRIKE);
            sb.append(SCORE_SPLIT);
            sb.append(STRING_STRIKE);
            sb.append(SCORE_SPLIT);
            sb.append(pins.getPinCount(BONUS_GAME) == 0 ? "G" : pins.getPinCount(BONUS_GAME));
        }
    }

    private static void convertSpareBonusGame(Pins pins, StringBuilder sb) {
        if (isSpare(pins) && pins.getTotalPins() < 30) {
            sb.append(pins.getPinCount(FIRST_ROLL));
            sb.append(SCORE_SPLIT);
            sb.append(STRING_SPARE);
            sb.append(SCORE_SPLIT);
            sb.append(pins.getPinCount(BONUS_GAME) == 0 ? "G" : pins.getPinCount(BONUS_GAME));
        }
    }

    private static boolean isDoubleStrike(Pins pins) {
        return firstRollStrike(pins) && secondRollStrike(pins);
    }

    private static boolean secondRollStrike(Pins pins) {
        return pins.getPinCount(SECOND_ROLL) == STRIKE;
    }

    private static boolean firstRollStrike(Pins pins) {
        return pins.getPinCount(FIRST_ROLL) == STRIKE;
    }

    private static boolean isSpare(Pins pins) {
        return pins.getPinCount(FIRST_ROLL) + pins.getPinCount(SECOND_ROLL) == SPARE;
    }

    private static boolean isBonusGame(Pins pins) {
        return pins.rollCount() == 3;
    }

    private static void convertRollOnce(Pins pins, StringBuilder sb) {
        if (isRollOnce(pins) && pins.getTotalPins() < 10) {
            sb.append(pins.getPinCount(FIRST_ROLL) == 0 ? "G" : pins.getPinCount(FIRST_ROLL));
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
            sb.append(pins.getPins().get(0).getPin() == 0 ? GUTTER : pins.getPins().get(0).getPin());
            sb.append(SCORE_SPLIT);
            sb.append(pins.getPins().get(1).getPin() == 0 ? GUTTER : pins.getPins().get(1).getPin());
        }
    }

    private static boolean isPinHit(Pins pins) {
        return pins.getTotalPins() > 0 && pins.getTotalPins() < 10;
    }

    private static void convertMiss(Pins pins, StringBuilder sb) {
        if (isRollTwice(pins) && pins.getTotalPins() == 0) {
            sb.append(STRING_MISS);
        }
    }

    private static void convertStrike(Pins pins, StringBuilder sb) {
        if (isRollOnce(pins) && pins.getTotalPins() == 10) {
            sb.append(STRING_STRIKE);
        }
    }

    private static boolean isRollOnce(Pins pins) {
        return pins.rollCount() == 1;
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
        if (isRollTwice(pins) && pins.getTotalPins() < 20) {
            sb.append(STRING_STRIKE);
            sb.append(SCORE_SPLIT);
            sb.append(pins.getPinCount(SECOND_ROLL) == 0 ? GUTTER : pins.getPinCount(SECOND_ROLL));
        }
    }

    private static void convertDoubleStrike(Pins pins, StringBuilder sb) {
        if (isRollTwice(pins) && pins.getTotalPins() == 20) {
            sb.append(STRING_STRIKE);
            sb.append(SCORE_SPLIT);
            sb.append(STRING_STRIKE);
        }
    }

    private static boolean isRollTwice(Pins pins) {
        return pins.rollCount() == 2;
    }
}
