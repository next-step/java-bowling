package bowling.domain.score;

public enum Sign {
    STRIKE("X"),
    SPARE("|/"),
    GUTTER("-"),
    MISS("|"),
    READY(""),
    CONTINUE(""),
    NONE("-|-");
    
    private String sign;

    Sign(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }
}
