package bowling;

import bowling.domain.Frame;
import bowling.domain.FrameStatus;
import bowling.view.FrameStatusView;

import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

public class GameStatus {
    private final GameService game;

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
                .mapToObj(game::getFrame)
                .map(frame -> frame.map(Frame::getFrameStatus).orElse(null))
                .map(this::toString)
                .map(Object::toString)
                .map(score -> String.format("  %-3s ", score))
                .collect(joining("|"));
    }

    private String toString(FrameStatus frameStatus) {
        if (frameStatus == null) {
            return "";
        }
        return new FrameStatusView(frameStatus).toString();
    }
}
