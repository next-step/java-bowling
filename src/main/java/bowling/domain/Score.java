package bowling.domain;

/**
 * Created : 2020-12-16 오전 8:22
 * Developer : Seo
 */
public class Score {
    public static final int SCORE_INIT = 0;
    public static final String DELIMITER = "|";

    private final int first;
    private int second;
    private int tenFrameBonus;

    public Score(Pins downPins) {
        validateFirst(downPins);
        this.first = downPins.get();
        this.second = SCORE_INIT;
        this.tenFrameBonus = SCORE_INIT;
    }

    private void validateFirst(Pins downPins) {
        if (downPins.get() > Frame.ALL_PINS.get()) {
            throw new IllegalArgumentException("잘못된 점수입니다.");
        }
    }

    public int getFirst() {
        return first;
    }

    public void setSecond(Pins downPins) {
        this.second = downPins.get();
    }

    public int getSecond() {
        return second;
    }

    public void setTenFrameBonus(Pins downPins) {
        this.tenFrameBonus = downPins.get();
    }

    public int getTenFrameBonus() {
        return tenFrameBonus;
    }

    public int get() {
        return first + second + tenFrameBonus;
    }

    public String getSymbol(int frameNo) {
        if (isLastFrameWithBonus(frameNo)) {
            return lastSymbol();
        }

        // Strike
        if (first == Symbol.STRIKE.getScore()) {
            return Symbol.STRIKE.getSymbol();
        }

        // Spare
        if (first + second == Symbol.SPARE.getScore()) {
            return first + DELIMITER + Symbol.SPARE.getSymbol();
        }

        // Gutter
        if (first == Symbol.GUTTER.getScore() && second != Symbol.GUTTER.getScore()) {
            return Symbol.GUTTER.getSymbol() + DELIMITER + second;
        }
        if (first != Symbol.GUTTER.getScore() && second == Symbol.GUTTER.getScore()) {
            return first + DELIMITER + Symbol.GUTTER.getSymbol();
        }
        if (first == Symbol.GUTTER.getScore() && second == Symbol.GUTTER.getScore()) {
            return Symbol.GUTTER.getSymbol() + DELIMITER + Symbol.GUTTER.getSymbol();
        }

        // miss
        return first + DELIMITER + second;
    }

    private boolean isLastFrameWithBonus(int frameNo) {
        if (frameNo == Frames.ALL_FRAMES && tenFrameBonus != Symbol.GUTTER.getScore()) {
            return true;
        }
        return false;
    }

    private String lastSymbol() {
        // X|X|X
        if (first == Symbol.STRIKE.getScore() && second == Symbol.STRIKE.getScore() && tenFrameBonus == Symbol.STRIKE.getScore()) {
            return Symbol.STRIKE.getSymbol() + DELIMITER + Symbol.STRIKE.getSymbol() + DELIMITER + Symbol.STRIKE.getSymbol();
        }
        // X|X|*
        if (first == Symbol.STRIKE.getScore() && second == Symbol.STRIKE.getScore() && tenFrameBonus != Symbol.STRIKE.getScore()) {
            return Symbol.STRIKE.getSymbol() + DELIMITER + Symbol.STRIKE.getSymbol() + DELIMITER + tenFrameBonus;
        }
        // X|*|X
        if (first == Symbol.STRIKE.getScore() && second != Symbol.STRIKE.getScore() && tenFrameBonus == Symbol.STRIKE.getScore()) {
            return Symbol.STRIKE.getSymbol() + DELIMITER + second + DELIMITER + Symbol.STRIKE.getSymbol();
        }
        // *|X|X
        if (first != Symbol.STRIKE.getScore() && second == Symbol.STRIKE.getScore() && tenFrameBonus == Symbol.STRIKE.getScore()) {
            return first + DELIMITER + Symbol.STRIKE.getSymbol() + DELIMITER + Symbol.STRIKE.getSymbol();
        }
        // X|*|*
        if (first == Symbol.STRIKE.getScore() && second != Symbol.STRIKE.getScore() && tenFrameBonus != Symbol.STRIKE.getScore()) {
            return Symbol.STRIKE.getSymbol() + DELIMITER + second + DELIMITER + tenFrameBonus;
        }
        // *|X|*
        if (first != Symbol.STRIKE.getScore() && second == Symbol.STRIKE.getScore() && tenFrameBonus != Symbol.STRIKE.getScore()) {
            return first + DELIMITER + Symbol.STRIKE.getSymbol() + DELIMITER + tenFrameBonus;
        }
        // *|*|X
        if (first != Symbol.STRIKE.getScore() && second != Symbol.STRIKE.getScore() && tenFrameBonus == Symbol.STRIKE.getScore()) {
            return first + DELIMITER + second + DELIMITER + Symbol.STRIKE.getSymbol();
        }
        return "";
    }

    @Override
    public String toString() {
        return "Score{" +
                "first=" + first +
                ", second=" + second +
                ", tenFrameBonus=" + tenFrameBonus +
                '}';
    }
}
