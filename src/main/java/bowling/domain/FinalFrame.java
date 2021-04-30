package bowling.domain;

public class FinalFrame {
    private Score score;

    public FinalFrame() {
        this.score = new Score(10, 2);
    }

    public String inputScore(String input) {
        return score.updateScore(Integer.parseInt(input));
    }
}
