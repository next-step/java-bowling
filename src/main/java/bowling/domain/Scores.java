package bowling.domain;
import java.util.List;
import java.util.ListIterator;

import static bowling.util.Const.*;

public class Scores {
    private List<Score> scores;
    public Scores(List<Score> scores){
        this.scores = scores;
    }

    public String playFrame(PlayerName playerName) {
        String scoreBoard = BEGIN_STR + playerName + DELIMITER_STR;
        scoreBoard += this.scores
                .stream()
                .map(v -> Score.scoreBoard(v))
                .reduce("", (acc, cur) -> acc + cur + END_STR);
        return scoreBoard;
    }

    public PlayFrames plays(PlayerName playerName) {
        ListIterator<Score> iterator = this.scores.listIterator();
        PlayFrames playFrames = new PlayFrames();
        while (iterator.hasNext()) {
            int i = iterator.nextIndex();
            Score score = this.scores.get(i);
            Score newScore = Score.play(score);
            this.scores.set(i, newScore);
            playFrames.add(new PlayFrame(i + 1, this.playFrame(playerName)));
            if (newScore.done()) {
                iterator.next();
            }
        }
        return playFrames;
    }
}
