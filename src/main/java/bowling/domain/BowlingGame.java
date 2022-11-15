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

    public Scoreboards play(Score score, int turn) {
        this.scoreboards.addScore(score, turn, this.round);
        return this.scoreboards;
    }

    public void setNextRound() {
        this.round.nextRound();
    }

    public boolean isEnd() {
        return this.round.isGameEnd();
    }

    public boolean isEndTurn(int turn) {
        return this.scoreboards.isEndTurn(this.round, turn);
    }
}
