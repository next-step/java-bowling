package bowling.domain.game;

import bowling.domain.frame.FrameResultDto;
import bowling.domain.frame.Frames;
import bowling.domain.Player;
import bowling.domain.point.Point;

import java.util.List;

public class Bowling {

    private final Player player;
    private final Frames frames;

    private Bowling(Player player, Frames frames) {
        this.player = player;
        this.frames = frames;
    }

    public static Bowling of(Player player, Frames frames) {
        return new Bowling(player, frames);
    }


    public String getPlayerName() {
        return player.getName();
    }

    public int getCurrentFrameNumber() {
        return frames.getCurrentFramePosition();
    }

    public void pitch(Point pointPitch) {
        frames.roll(pointPitch);
    }

    public List<FrameResultDto> getResult() {
        return frames.getFrameResults();
    }

    public boolean isGameFinished() {
        return frames.isFinished();
    }

}
