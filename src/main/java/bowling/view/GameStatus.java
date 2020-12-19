package bowling.view;

import bowling.GameService;
import bowling.domain.Frame;
import bowling.domain.FrameStatus;
import bowling.domain.Score;

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
        return IntStream.rangeClosed(1, 10)
                .mapToObj(game::getFrame)
                .map(frame -> frame.map(Frame::getScore))
                .map(score -> score.map(Score::toInt).orElse(null))
                .map(this::toStringScore)
                .map(this::formatString)
                .collect(joining("|"));
    }

    private String getFrameStatus() {
        return IntStream.rangeClosed(1, 10)
                .mapToObj(game::getFrame)
                .map(frame -> frame.map(Frame::getFrameStatus).orElse(null))
                .map(this::toStringFrameStatus)
                .map(this::formatString)
                .collect(joining("|"));
    }

    private String formatString(String score) {
        return String.format("  %-3s ", score);
    }

    private String toStringFrameStatus(FrameStatus frameStatus) {
        if (frameStatus == null) {
            return "";
        }
        return new FrameStatusView(frameStatus).toString();
    }

    private String toStringScore(Integer score) {
        if (score == null) {
            return "";
        }
        return score.toString();
    }
}
