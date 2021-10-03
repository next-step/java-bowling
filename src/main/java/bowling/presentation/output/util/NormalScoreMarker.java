package bowling.presentation.output.util;

public class NormalScoreMarker implements ScoreMarker {

    private NormalScoreMarker() {
    }

    public static NormalScoreMarker newInstance() {
        return new NormalScoreMarker();
    }

    @Override
    public String mark(String outputScores, int score) {
        return outputScores + score;
    }
}
