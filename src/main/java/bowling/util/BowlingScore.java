package bowling.util;

import java.util.Arrays;

public class BowlingScore {
    public String firstTransfer(Integer score) {
        if (score == 10) {
            return "X";
        }
        return Integer.toString(score);
    }
    public String secondTransfer(Integer firstScore, Integer secondScore) {
        int roundTotalScore = firstScore + secondScore;
        if (roundTotalScore == 10) {
            return firstScore + "|-";
        }
        return firstScore + "|" + Integer.toString(secondScore);
    }

    public String thirdTransfer(String previousScore, Integer thirdScore) {
        int previousTotalScore = 0;
        String[] string = previousScore.split("\\|");
        for (String previouseScore: string) {
            previousTotalScore += Integer.parseInt(previouseScore);
        }

        if (previousTotalScore == 10) {
            return previousScore + "|-";
        }
        return previousScore + "|" + Integer.toString(thirdScore);
    }
}
