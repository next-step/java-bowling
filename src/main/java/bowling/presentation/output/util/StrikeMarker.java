package bowling.presentation.output.util;

import static bowling.presentation.output.constant.ScoreMarking.STRIKE;

public class StrikeMarker implements ScoreMarker {

    private StrikeMarker() {
    }

    public static StrikeMarker newInstance() {
        return new StrikeMarker();
    }

    @Override
    public String mark(String outputScores, int score) {
        return outputScores + STRIKE.value();
    }
}
