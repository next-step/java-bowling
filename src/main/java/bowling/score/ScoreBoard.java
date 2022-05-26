package bowling.score;

import bowling.frame.LastFrame;
import bowling.status.Status;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.Collections.*;

public class ScoreBoard {

    private final LinkedList<Integer> scores = new LinkedList<>();

    private ScoreBoard() {
    }

    public static ScoreBoard create() {
        return new ScoreBoard();
    }

    public void addScoreBoard(Score score) {
        if (scores.isEmpty()) {
            scores.add(score.score());
            return;
        }
        scores.add(scores.getLast() + score.score());
    }

    public List<Integer> scores() {
        return unmodifiableList(scores);
    }

    public void lastBonusScore(LastFrame nextFrame) {
        List<Status> myAllStatus = nextFrame.findMyAllStatus();

        Status firstStatus = myAllStatus.get(0);
        Score score = firstStatus.createScore();

        IntStream.range(1, myAllStatus.size()).forEach(index -> {
            if (score.canCalculate()) {
                score.bonusScore(myAllStatus.get(index).ownScore());
            }
        });

        addScoreBoard(score);
    }
}
