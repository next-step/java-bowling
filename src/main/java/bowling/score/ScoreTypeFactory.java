package bowling.score;

public class ScoreTypeFactory {

    private ScoreTypeFactory() {
    }

    public static ScoreType initiate(Score score) {
        if (score.getScore() == 10) {
            return ScoreType.STRIKE;
        }
        if (score.getScore() == 0) {
            return ScoreType.GUTTER;
        }
        return ScoreType.NORMAL;
    }
}
