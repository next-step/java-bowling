package bowling.view;

import bowling.domain.Round;
import bowling.domain.Scoreboard;
import bowling.domain.Scoreboards;
import bowling.domain.frame.Frame;
import bowling.domain.score.Score;
import bowling.domain.score.scores.Scores;

public class Output {

    private static final int SCORE_STRIKE = 10;
    private static final int ROUND_START = 1;
    private static final int ROUND_END = 10;
    private static final String SEPARATOR = "|";
    private static final String DEFAULT_FRAME_FORMAT = SEPARATOR + "  %-3s ";
    private static final String LAST_FRAME_FORMAT = SEPARATOR + " %-5s";
    private static final String NAME_FORMAT = SEPARATOR + "  %3s ";
    private static final String SCORE_FORMAT = " %3s  ";
    private static final String BLANK_BLOCK = "      ";
    private static final String STRIKE_OUTPUT = "X";
    private static final String GUTTER_OUTPUT = "-";
    private static final String SPARE_OUTPUT = "/";
    private static final String NEW_LINE = "\n";
    private static final String NAME_GUIDE = " NAME ";

    public static void printScoreboard(Scoreboards scoreboards) {
        StringBuilder result = new StringBuilder();
        result.append(guideLine());
        result.append(NEW_LINE);
        for (Scoreboard scoreboard : scoreboards.scoreboards().values()) {
            result.append(currentScoreLine(scoreboard));
            result.append(NEW_LINE);
            result.append(totalScoreLine(scoreboard));
            result.append(NEW_LINE);
        }
        System.out.println(result);
    }


    private static String guideLine() {
        StringBuilder result = new StringBuilder();
        result.append(SEPARATOR);
        result.append(NAME_GUIDE);
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

    private static String currentScoreLine(Scoreboard scoreboard) {
        StringBuilder result = new StringBuilder();
        result.append(String.format(NAME_FORMAT, scoreboard.name().name()));
        for (int round = ROUND_START; round < ROUND_END; round++) {
            Frame frame = scoreboard.frame(new Round(round));
            result.append(String.format(DEFAULT_FRAME_FORMAT, frameFormat(frame)));
        }
        Frame frame = scoreboard.frame(new Round(Round.ROUND_END));
        result.append(String.format(LAST_FRAME_FORMAT, frameFormat(frame)));
        result.append(SEPARATOR);
        return result.toString();
    }

    private static String frameFormat(Frame frame) {
        if (frame.totalScore().regularScores().isSizeEqual(0)) {
            return "";
        }
        if (frame.totalScore().regularScores().isSizeEqual(1)) {
            return firstThrowOutput(frame);
        }
        if (frame.totalScore().regularScores().isSizeEqual(2)) {
            return twiceThrowOutput(frame);
        }
        if (frame.totalScore().regularScores().isSizeEqual(3)) {
            return thirdThrowOutput(frame);
        }
        return "";
    }

    private static String firstThrowOutput(Frame frame) {
        return scoreFormat(frame.totalScore().regularScores().first());
    }

    private static String twiceThrowOutput(Frame frame) {
        Scores scores = frame.totalScore().regularScores();
        if (scores.sum() == SCORE_STRIKE) {
            return scoreFormat(scores.first()) + SEPARATOR + SPARE_OUTPUT;
        }
        return scoreFormat(scores.first()) + SEPARATOR + scoreFormat(scores.second());
    }

    private static String thirdThrowOutput(Frame frame) {
        Score first = frame.totalScore().regularScores().first();
        Score second = frame.totalScore().regularScores().second();
        Score third = frame.totalScore().regularScores().third();
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

    private static String totalScoreLine(Scoreboard scoreboard) {
        StringBuilder result = new StringBuilder();
        result.append(SEPARATOR);
        result.append(BLANK_BLOCK);
        int totalScore = 0;
        for (int round = ROUND_START; round <= ROUND_END; round++) {
            result.append(SEPARATOR);
            Frame frame = scoreboard.frame(new Round(round));
            if (frame.isNotEndScoreAggregation()) {
                result.append(BLANK_BLOCK);
            }
            if (!frame.isNotEndScoreAggregation()) {
                totalScore += frame.totalScore().sumTotalScore();
                result.append(String.format(SCORE_FORMAT, totalScore));
            }
        }
        result.append(SEPARATOR);
        return result.toString();
    }
}
