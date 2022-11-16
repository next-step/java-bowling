package bowling.domain;

import bowling.domain.score.Score;

public class BowlingGame {
    private final Scoreboards scoreboards;
    private final Round round;

    public BowlingGame(Scoreboards scoreboards) {
        this.scoreboards = scoreboards;
        this.round = new Round();
    }

    public int round() {
        return this.round.value();
    }

    public Scoreboards play(Score score, Name name) {
        this.scoreboards.addScore(score, name, this.round);
        return this.scoreboards;
    }

    public void setNextRound() {
        this.round.nextRound();
    }

    public boolean isEnd() {
        return this.round.isGameEnd();
    }

    public boolean isEndTurn(Name name) {
        return this.scoreboards.isEndTurn(this.round, name);
    }
}
