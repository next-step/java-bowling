package bowling;

import bowling.domain.Frame;
import bowling.domain.Scoring;

import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

public class GameStatus {
    private GameService game;

    public GameStatus(GameService game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return "|  " + game.getPlayerName() + " " +
               "|" + getFrameStatus() +
               "|";
    }

    private String getFrameStatus() {
        return IntStream.rangeClosed(1, 10)
                .mapToObj(i -> game.getFrame(i))
                .map(frame -> {
                    return frame.flatMap(Frame::getScoring)
                            .map(scoring -> {
                                if (scoring == Scoring.STRIKE) {
                                    return "X";
                                }
                                return "NULL";
                            }).orElse("");
                })
                .map(score -> String.format("  %-3s ", score))
                .collect(joining("|"));
    }
}
