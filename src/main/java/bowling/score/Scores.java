package bowling.score;

import bowling.frame.LastFrame;
import bowling.frame.ShootScore;
import bowling.status.Status;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

import static java.util.Collections.*;

public class Scores {

    private final LinkedList<Integer> scoreBoard = new LinkedList<>();
    private final Queue<Score> bonusScores = new LinkedList<>();

    private Scores() { }

    public static Scores create() {
        return new Scores();
    }

    public void calculateBonusScore(ShootScore nextShoot) {
        bonusScores.stream()
                .filter(Score::canCalculate)
                .forEach(score -> score.bonusScore(nextShoot.getShootScore()));

        if (endScore()) {
            addScoreBoard(bonusScores.poll());
        }
    }

    private boolean endScore() {
        return !bonusScores.isEmpty() && !bonusScores.peek().canCalculate();
    }

    public void addScore(Score score) {
        if (score.canCalculate()) {
            bonusScores.add(score);
            return;
        }
        addScoreBoard(score);
    }

    public void lastBonusScore(LastFrame lastFrame) {
        List<Status> myAllStatus = lastFrame.findMyAllStatus();

        Status firstStatus = myAllStatus.get(0);
        Score score = firstStatus.createScore();

        IntStream.range(1, myAllStatus.size()).forEach(index -> {
            if (score.canCalculate()) {
                score.bonusScore(myAllStatus.get(index).ownScore());
            }
        });

        addScoreBoard(score);
    }

    private void addScoreBoard(Score score) {
        if (scoreBoard.isEmpty()) {
            scoreBoard.add(score.score());
            return;
        }
        scoreBoard.add(scoreBoard.getLast() + score.score());
    }

    public List<Integer> scoreBoard() {
        return unmodifiableList(scoreBoard);
    }
}
