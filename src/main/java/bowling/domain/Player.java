package bowling.domain;

import bowling.view.Output;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static bowling.util.Const.RANDOM;

public class Player {
    private final PlayerName playerName;
    private final List<Score> scores;

    public Player(String name) {
        this.playerName = new PlayerName(name);
        this.scores = IntStream.rangeClosed(1, 10).mapToObj(v -> new Score()).collect(Collectors.toList());
    }

    public String scoreBoard() {
        String scoreBoard = "| " + this.playerName + "  |  "; // name should be formatted
        scoreBoard += this.scores
                .stream()
                .map(v -> Score.scoreBoard(v))
                .reduce("", (acc, cur) -> acc + cur + "|  ");
        return scoreBoard;
    }

    public static int pitch(int max) {
        return RANDOM.nextInt(max + 1);
    }

    public void plays() {
        ListIterator<Score> iterator = this.scores.listIterator();
        while (iterator.hasNext()) {
            int i = iterator.nextIndex();
            Score score = this.scores.get(i);
            Score newScore = Score.play(score);
            this.scores.set(i, newScore);
            System.out.println("Frame " + (i + 1));
            Output.print("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
            Output.print(this.scoreBoard());
            if (newScore.done()) {
                iterator.next();
            }
        }
    }
}
