package bowling.domain;

public enum Sign {

    STRIKE("X"),
    SPARE("/"),
    GUTTER("-"),
    MISS("");
    private final String sign;

    Sign(String sign) {
        this.sign = sign;
    }

    public static Sign matchSign(Scores scores) {
        if (scores.isGutter()){
            return GUTTER;
        }
        if (scores.isNormalFrameSpare()) {
            return SPARE;
        }
        if (scores.isFinalFrameSpare()) {
            return SPARE;
        }
        if (scores.isStrike()) {
            return STRIKE;
        }
        return MISS;
    }

    public String getSign() {
        return sign;
    }
}
