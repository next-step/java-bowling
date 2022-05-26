package bowling.score;

import bowling.frame.ShootScore;
import bowling.status.Status;

import java.util.ArrayList;
import java.util.List;

public class Scores {

    private final List<Score> scores = new ArrayList<>();

    private Scores() { }

    public static Scores create() {
        return new Scores();
    }

    public void calculateBonusScore(ShootScore nextShoot) {
        scores.stream()
                .filter(Score::canCalculate)
                .forEach(score -> score.bonusScore(nextShoot.getShootScore()));
    }

    public void addScore(Status status) {
        scores.add(status.createScore());
    }

}
