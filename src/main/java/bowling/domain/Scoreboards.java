package bowling.domain;

import bowling.domain.score.Score;
import java.util.ArrayList;
import java.util.List;

public class Scoreboards {
    private final List<Scoreboard> scoreboards;

    public Scoreboards() {
        this.scoreboards = new ArrayList<>();
    }

    public void add(Scoreboard scoreboard) {
        this.scoreboards.add(scoreboard);
    }

    public boolean isEndTurn(Round round, int turn) {
        Scoreboard scoreboard = this.scoreboards.get(turn);
        return !scoreboard.frame(round).isRemainChance();
    }

    public void addScore(Score score, int turn, Round round) {
        this.scoreboards.get(turn).addScore(score, round);
    }

    public List<Scoreboard> scoreboards() {
        return this.scoreboards;
    }
}
