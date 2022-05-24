package bowling.domain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static bowling.util.Const.*;

public class Player {
    private final PlayerName playerName;
    private final List<Score> scores;

    public Player(String name) {
        this.playerName = new PlayerName(name);
        this.scores = IntStream.rangeClosed(1, 10).mapToObj(v -> new Score()).collect(Collectors.toList());
    }

    public String playFrame() {
        String scoreBoard = BEGIN_STR + this.playerName + DELIMITER_STR;
        scoreBoard += this.scores
                .stream()
                .map(v -> Score.scoreBoard(v))
                .reduce("", (acc, cur) -> acc + cur + END_STR);
        return scoreBoard;
    }

    public static int pitch(int max) {
        return RANDOM.nextInt(max + 1);
    }

    public PlayFrames plays() {
        ListIterator<Score> iterator = this.scores.listIterator();
        PlayFrames playFrames = new PlayFrames();
        while (iterator.hasNext()) {
            int i = iterator.nextIndex();
            Score score = this.scores.get(i);
            Score newScore = Score.play(score);
            this.scores.set(i, newScore);
            playFrames.add(new PlayFrame(i + 1, this.playFrame()));
            if (newScore.done()) {
                iterator.next();
            }
        }
        return playFrames;
    }
}
