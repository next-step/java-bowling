package bowling.domain;

import bowling.domain.frame.Frame;

public enum ScoreUI {
    STRIKE("x"),
    SPARE("/"),
    MISS("-");

    private String ui;

    private ScoreUI(String ui) {
        this.ui = ui;
    }

    public String getUi() {
        return this.ui;
    }

    public static String frameOf(Frame frame) {
        if(frame.isFirst()) {
            return "";
        }
        if (frame.isMiss()) {
            return MISS.getUi();
        }
        if (frame.isStrike()) {
            return STRIKE.getUi();
        }
        if (frame.isSpare()) {
            return SPARE.getUi();
        }
        return Integer.toString(frame.getFrameLastScore());
    }

}
