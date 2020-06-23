package bowling.score;

public class ScoreTypeFactory {

    private ScoreTypeFactory() {
    }

    public static ScoreType initiate(Score score) {
        if (score.isMaximumScore()) {
            return ScoreType.STRIKE;
        }
        return score.isMinimumScore() ? ScoreType.GUTTER : ScoreType.NORMAL;
    }
}
