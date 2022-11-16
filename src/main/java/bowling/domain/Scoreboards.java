package bowling.domain;

import bowling.domain.score.Score;
import java.util.Map;

public class Scoreboards {
    private final Map<Name, Scoreboard> scoreboards;

    public Scoreboards(Names names) {
        this.scoreboards = names.createScoreboards();
    }

    public boolean isEndTurn(Round round, Name name) {
        Scoreboard scoreboard = this.scoreboards.get(name);
        return !scoreboard.frame(round).isRemainChance();
    }

    public void addScore(Score score, Name name, Round round) {
        this.scoreboards.get(name).addScore(score, round);
    }

    public Map<Name, Scoreboard> scoreboards() {
        return this.scoreboards;
    }
}
