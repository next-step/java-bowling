package bowling.domain;

import bowling.domain.score.Score;
import java.util.Map;

public class Scoreboards {
    private final Map<Name, Scoreboard> scoreboards;

    public Scoreboards(Names names) {
        this.scoreboards = names.createScoreboards();
    }

    public boolean isEndTurn(Round round, Name name) {
        validateName(name);
        Scoreboard scoreboard = this.scoreboards.get(name);
        return !scoreboard.frame(round).isRemainChance();
    }

    public void addScore(Score score, Name name, Round round) {
        validateName(name);
        this.scoreboards.get(name).addScore(score, round);
    }

    private void validateName(Name name) {
        if (!this.scoreboards.containsKey(name)) {
            throw new IllegalArgumentException();
        }
    }

    public Map<Name, Scoreboard> scoreboards() {
        return this.scoreboards;
    }
}
