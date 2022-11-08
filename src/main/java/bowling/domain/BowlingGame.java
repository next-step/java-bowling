package bowling.domain;

public class BowlingGame {

    private final Scoreboard scoreboard;
    private final Round round;

    public BowlingGame(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
        this.round = new Round();
    }

    public int round() {
        return this.round.value();
    }

    public Scoreboard play(Score score) {
        this.scoreboard.addScore(score, this.round);
        return this.scoreboard;
    }

    public boolean isEnd() {
        Frame frame = this.scoreboard.frame(this.round);
        return this.round.isGameEnd(frame);
    }
}
