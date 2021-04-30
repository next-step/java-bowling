package bowling.domain;

public class NormalFrame {
    private Score score;

    public NormalFrame() {
        this.score = new Score(10, 2);
    }

    public String inputScore(String input) {
        return score.updateScore(Integer.parseInt(input));
    }
}
