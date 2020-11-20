package bowling.domain.score;

public class ScoreData {

    private Score score;
    private ScoreType scoreType;

    public ScoreData(Score score, ScoreType scoreType) {
        this.score = score;
        this.scoreType = scoreType;
    }

    public Score getScore() {
        return score;
    }

    public ScoreType getScoreType() {
        return scoreType;
    }
}
