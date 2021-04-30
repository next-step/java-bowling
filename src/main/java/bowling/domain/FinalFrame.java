package bowling.domain;

public class FinalFrame {
    private Score score;

    public FinalFrame() {
        this.score = new Score(10, 2);
    }

    public String inputScore(String input) {
        return checkBenefit(score.updateScore(Integer.parseInt(input)));
    }

    private String checkBenefit(String formattedScore) {
        if ("X".equals(formattedScore)) {
            addBonusGame(2);
        }
        if ("/".equals(formattedScore)) {
            addBonusGame(1);
        }
        return formattedScore;
    }

    private void addBonusGame(int availability) {
        this.score = new Score(10, availability);
    }
}
