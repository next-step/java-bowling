package bowling.view;

import bowling.domain.Score;

public enum PinView {


    AND("|"),
    SPARE("/"),

    STRIKE("X"),
    GUTTER("-");

    private final String view;


    PinView(String view) {
        this.view = view;
    }

    public static String valueOf(Score score) {
        if (score.isStrike()) {
            return STRIKE.view;
        }
        if (score.isGutter()) {
            return GUTTER.view;
        }
        return String.valueOf(score.getScore());
    }

    public String getView() {
        return view;
    }
}
