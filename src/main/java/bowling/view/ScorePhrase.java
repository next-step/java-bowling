package bowling.view;

import bowling.domain.FrameScore;

import java.util.Arrays;

public enum ScorePhrase {
    STRIKE(FrameScore.STRIKE, "  X   |"),
    SPARE(FrameScore.SPARE, "  %s|/ |"),
    ONGOING(FrameScore.ONGOING, "  %s|"),
    FINISH(FrameScore.FINISH, "  %s|%s |");

    private final FrameScore frameScore;
    private final String printString;

    ScorePhrase(FrameScore frameScore, String printString) {
        this.frameScore = frameScore;
        this.printString = printString;
    }

    public String getPrintString() {
        return printString;
    }

    public FrameScore getFrameScore() {
        return frameScore;
    }

    public static String getScoreString(FrameScore frameScore) {
        return Arrays.stream(values())
                .filter(vo -> vo.getFrameScore().equals(frameScore))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Need to handling new FrameScore Enum"))
                .getPrintString();
    }

}