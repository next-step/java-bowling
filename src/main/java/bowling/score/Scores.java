package bowling.score;

import bowling.frame.ShootScore;
import bowling.status.Status;

import java.util.LinkedList;
import java.util.Queue;

public class Scores {

    private final Queue<Score> scores = new LinkedList<>();

    private Scores() { }

    public static Scores create() {
        return new Scores();
    }

    public void calculateBonusScore(ShootScore nextShoot, ScoreBoard scoreBoard) {
        scores.stream()
                .filter(Score::canCalculate)
                .forEach(score -> score.bonusScore(nextShoot.getShootScore()));

        if (endScore()) {
            scoreBoard.addScoreBoard(scores.poll());
        }
    }

    private boolean endScore() {
        return !scores.isEmpty() && !scores.peek().canCalculate();
    }

    public void addScore(Status status, ScoreBoard scoreBoard) {
        Score score = status.createScore();
        if (score.canCalculate()) {
            scores.add(score);
            return;
        }
        scoreBoard.addScoreBoard(score);
    }

}
