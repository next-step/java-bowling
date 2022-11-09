package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Score;
import bowling.domain.Scoreboard;
import bowling.domain.Scores;

public class Output {

    public static final int SCORE_STRIKE = 10;
    public static final int ROUND_START = 1;
    public static final int ROUND_END = 10;
    private static final String SEPARATOR = "|";
    private static final String defaultFrameFormat = SEPARATOR + "  %-3s ";
    private static final String lastFrameFormat = SEPARATOR + " %-5s";
    private static final String nameFormat = SEPARATOR + "  %3s ";
    private static final String STRIKE_OUTPUT = "X";
    private static final String GUTTER_OUTPUT = "-";
    private static final String SPARE_OUTPUT = "/";

    public static void printScoreboard(Scoreboard scoreboard) {
        StringBuilder result = new StringBuilder();
        result.append(firstLine());
        result.append("\n");
        result.append(secondLine(scoreboard));
        System.out.println(result);
    }

    private static String firstLine() {
        StringBuilder result = new StringBuilder();
        result.append(SEPARATOR);
        result.append(" NAME ");
        for (int round = ROUND_START; round <= ROUND_END; round++) {
            result.append(SEPARATOR);
            result.append("  ");
            result.append(roundFormat(round));
            result.append("  ");
        }
        result.append(SEPARATOR);
        return result.toString();
    }

    private static String roundFormat(int round) {
        if (round < 10) {
            return "0" + round;
        }
        return String.valueOf(round);
    }

    private static String secondLine(Scoreboard scoreboard) {
        StringBuilder result = new StringBuilder();
        result.append(String.format(nameFormat, scoreboard.name().name()));
        for (int round = ROUND_START; round < ROUND_END; round++) {
            Frame frame = scoreboard.frames().get(round - 1);
            result.append(String.format(defaultFrameFormat, frameFormat(frame)));
        }
        Frame frame = scoreboard.frames().get(ROUND_END - 1);
        result.append(String.format(lastFrameFormat, frameFormat(frame)));
        result.append(SEPARATOR);
        return result.toString();
    }

    private static String frameFormat(Frame frame) {
        if (frame.scores().isEmpty()) {
            return "";
        }
        if (frame.scores().size() == 1) {
            return firstThrowOutput(frame);
        }
        if (frame.scores().size() == 2) {
            return twiceThrowOutput(frame);
        }
        if (frame.scores().size() == 3) {
            return thirdThrowOutput(frame);
        }
        return "";
    }

    private static String firstThrowOutput(Frame frame) {
        return scoreFormat(frame.scores().first());
    }

    private static String twiceThrowOutput(Frame frame) {
        Scores scores = frame.scores();
        if (scores.sum() == SCORE_STRIKE) {
            return scoreFormat(scores.first()) + SEPARATOR + SPARE_OUTPUT;
        }
        return scoreFormat(scores.first()) + SEPARATOR + scoreFormat(scores.second());
    }

    private static String thirdThrowOutput(Frame frame) {
        Score first = frame.scores().first();
        Score second = frame.scores().second();
        Score third = frame.scores().third();
        if (!first.isStrike() && Scores.sumScores(first, second) == SCORE_STRIKE) {
            return scoreFormat(first) + SEPARATOR + SPARE_OUTPUT + SEPARATOR + scoreFormat(third);
        }
        if (first.isStrike() && Scores.sumScores(second, third) == SCORE_STRIKE) {
            return scoreFormat(first) + SEPARATOR + scoreFormat(second) + SEPARATOR + SPARE_OUTPUT;
        }
        return scoreFormat(first) + SEPARATOR + scoreFormat(second) + SEPARATOR + scoreFormat(third);
    }

    private static String scoreFormat(Score score) {
        if (score.isStrike()) {
            return STRIKE_OUTPUT;
        }
        if (score.value() == 0) {
            return GUTTER_OUTPUT;
        }
        return String.valueOf(score.value());
    }
}
