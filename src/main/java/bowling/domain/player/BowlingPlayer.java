package bowling.domain.player;

import bowling.domain.frame.Frames;
import bowling.domain.pin.DownedPins;
import bowling.dto.ScoreDto;
import bowling.dto.StateDtos;

import java.util.List;

public class BowlingPlayer {
    private final Player player;
    private final Frames frames;

    public BowlingPlayer(String name) {
        player = Player.from(name);
        frames = Frames.init();
    }

    public static BowlingPlayer from(String name) {
        return new BowlingPlayer(name);
    }

    public void play(int numbOfDownedPins) {
        DownedPins downedPins = DownedPins.from(numbOfDownedPins);

        frames.downPins(downedPins);
    }

    public boolean isBowlingEnd() {
        return frames.isBowlingEnd();
    }

    public String getName() {
        return player.getName();
    }

    public List<StateDtos> getStates() {
        return frames.getTotalStates();
    }

    public List<ScoreDto> getScores() {
        return frames.getScores();
    }
}
