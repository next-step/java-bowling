package bowling.presentation.output.util;

import static bowling.presentation.output.constant.ScoreMarking.SPARE;

public class SpareMarker implements ScoreMarker {

    private SpareMarker() {
    }

    public static SpareMarker newInstance() {
        return new SpareMarker();
    }

    @Override
    public String mark(String outputScores, int score) {
        return outputScores + SPARE.value();
    }
}
