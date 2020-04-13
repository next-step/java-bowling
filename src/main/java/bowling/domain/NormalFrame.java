package bowling.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NormalFrame {
    private final List<ShotScore> shotScores;
    private static final int SHOT_LIMIT = 2;

    private NormalFrame(List<ShotScore> shotScores) {
        this.shotScores = shotScores;
    }

    static NormalFrame init() {
        return new NormalFrame(new ArrayList<>());
    }

    NormalFrame next(int shot) {
        return new NormalFrame(Arrays.asList(ShotScore.of(shot)));
    }

    ShotScore shot(int shot) {
        if (shotScores.isEmpty()) {
            return addScore(ShotScore.of(shot));
        }

        if (shotScores.size() == SHOT_LIMIT) {
            throw new IllegalStateException(String.format("shot NormalFrame fail. cannot shot over %d times", SHOT_LIMIT));
        }

        return addScore(shotScores.get(0).next(shot));
    }

    private ShotScore addScore(ShotScore next) {
        shotScores.add(next);
        return next;
    }
}
