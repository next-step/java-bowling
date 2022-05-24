package bowling.domain;

import static bowling.util.Const.GUTTER_NUMBER;
import static bowling.util.Const.MAX_PIN;

public enum ScoreType {
    STRIKE("X"), SPARE("|/"), MISS("|"), GUTTER("-|-"), SECOND("|"), NULL(" ");
    private final String symbol;
    ScoreType(String symbol) {
        this.symbol = symbol;
    }
    public static ScoreType of(int first) {
        if (first == MAX_PIN) {
            return STRIKE;
        }
        return SECOND;
    }

    public static ScoreType of(int first, int second) {
        int total = first + second;
        if (total == MAX_PIN) {
            return SPARE;
        }
        if (total == GUTTER_NUMBER) {
            return GUTTER;
        }
        if (total < MAX_PIN) {
            return MISS;
        }
        throw new RuntimeException("unreachable score: " + total);
    }

    public String toSymbol() {
        return this.symbol;
    }
//        if (!score.isPresent()) {
//            return format(" ");
//        }
//        if (score.scoreType == ScoreType.STRIKE) {
//            return format("X");
//        }
//        if (score.scoreType == ScoreType.SECOND) {
//            return format(score.hit.firstStr());
//        }
//        if (score.scoreType == ScoreType.GUTTER) {
//            return format("-|-");
//        }
//        if (score.scoreType == ScoreType.MISS) {
//            return format(score.hit.firstStr() + "|" + score.hit.secondStr());
//        }
//        if (score.scoreType == ScoreType.SPARE) {
//            return format(score.hit.first() + "|/");
//        }
//        throw new RuntimeException("unreachable " + score);
    }
