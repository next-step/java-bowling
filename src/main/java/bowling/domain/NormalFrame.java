package bowling.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NormalFrame {
    private static final int SHOT_LIMIT = 2;

    private final List<ShotScore> shotScores;

    private NormalFrame(List<ShotScore> shotScores) {
        this.shotScores = shotScores;
    }

    static NormalFrame init() {
        return new NormalFrame(new ArrayList<>());
    }

    NormalFrame next(int shot) {
        return new NormalFrame(Arrays.asList(ShotScore.of(shot)));
    }

    void shot(int shot) {
        if (shotScores.isEmpty()) {
            shotScores.add(ShotScore.of(shot));
            return;
        }

        if (shotScores.size() == SHOT_LIMIT) {
            throw new IllegalStateException(String.format("shot NormalFrame fail. cannot shot over %d times", SHOT_LIMIT));
        }

        shotScores.add(shotScores.get(0).next(shot));
    }

    boolean isFrameClosed(){
        return shotScores.size() == SHOT_LIMIT || shotScores.stream()
                .map(ShotScore::getScoreType)
                .anyMatch(ScoreType.STRIKE::equals);
    }

    public List<ShotScore> getShotScores() {
        return shotScores;
    }
}
