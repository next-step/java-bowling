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


    public void pitch(Point pointPitch) {
        frames.pitch(pointPitch);
    }

    public List<FrameResultDto> getResult() {
        return frames.getFrameResultDtos();
    }

    public boolean isGameFinished() {
        return frames.isFinished();
    }
    public boolean isFrameFinished(int frameIndex){
        return frames.hasScoreTurn(frameIndex);
    }

}
