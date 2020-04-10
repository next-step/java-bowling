package bowling.domain;

public class Frame {
    private Score firstScore;
    private Score secondScore;

    public Frame(int firstScore, int secondScore) {
        this.firstScore = Score.of(firstScore);
        this.secondScore = Score.of(secondScore);
    }
}