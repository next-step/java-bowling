package bowling.presentation.output.util;

import static bowling.presentation.output.constant.ScoreMarking.NO_POINT;

public class NoPointMarker implements ScoreMarker {

    private NoPointMarker() {
    }

    public static NoPointMarker newInstance() {
        return new NoPointMarker();
    }

    @Override
    public String mark(String outputScores, int score) {
        return outputScores + NO_POINT.value();
    }
}
