package bowling.view;

import bowling.GameService;
import bowling.domain.Frame;
import bowling.domain.FrameStatus;
import bowling.domain.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

public class GameStatus {
    private final GameService game;

    public GameStatus(GameService game) {
        this.game = game;
    }

    public String getScoring() {
        return "|  " + game.getPlayerName() + " " +
               "|" + getFrameStatus() +
               "|";
    }

    public String getScore() {
        return "|      " +
               "|" + getFrameScore() +
               "|";
    }

    private String getFrameScore() {
        List<Integer> collect = IntStream.rangeClosed(1, 10)
                .mapToObj(game::getFrame)
                .map(frame -> frame.map(Frame::getScore))
                .collect((Supplier<ArrayList<Integer>>) ArrayList::new,
                         ((integers, score) -> {
                             integers.add(score.map(Score::toInt).orElse(null));
                         }),
                         ((integers, integers2) -> {
                             throw new IllegalStateException();
                         }));

        return collect.stream()
                .map(score -> {
                    if (score == null) {
                        return "";
                    }
                    return score.toString();
                })
                .map(score -> {
                    return String.format("  %-3s ", score);
                })
                .collect(joining("|"));
    }

    private String getFrameStatus() {
        return IntStream.rangeClosed(1, 10)
                .mapToObj(game::getFrame)
                .map(frame -> frame.map(Frame::getFrameStatus).orElse(null))
                .map(this::toStringFrameStatus)
                .map(Object::toString)
                .map(score -> String.format("  %-3s ", score))
                .collect(joining("|"));
    }

    private String toStringFrameStatus(FrameStatus frameStatus) {
        if (frameStatus == null) {
            return "";
        }
        return new FrameStatusView(frameStatus).toString();
    }
}
