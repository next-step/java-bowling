package bowling.util;

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
}
