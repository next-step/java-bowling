package bowling.presentation.output.util;

import static bowling.common.Pin.MAX;
import static bowling.common.Pin.NO_POINT;

public class ScoreMarkerFactory {

    private ScoreMarkerFactory() {
    }

    public static ScoreMarkerFactory newInstance() {
        return new ScoreMarkerFactory();
    }


    public ScoreMarker scoreMarker(int score) {
        if (score == MAX.value()) {
            return StrikeMarker.newInstance();
        }
        if (score == NO_POINT.value()) {
            return NoPointMarker.newInstance();
        }
        return NormalScoreMarker.newInstance();
    }

    public ScoreMarker scoreMarker(int prev, int score) {
        if (prev != MAX.value() && prev + score == MAX.value()) {
            return SpareMarker.newInstance();
        }
        if (prev == MAX.value() && score == MAX.value()) {
            return StrikeMarker.newInstance();
        }
        if (score == NO_POINT.value()) {
            return NoPointMarker.newInstance();
        }
        return NormalScoreMarker.newInstance();
    }


}
