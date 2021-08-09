package bowling.domain.player;


import bowling.domain.frame.Frames;
import bowling.domain.pin.Pins;
import bowling.domain.dto.StateDatas;

import java.util.List;

public class Bowler {
    private final Player player;
    private final Frames frames;

    private Bowler(String name) {
        player = Player.of(name);
        frames = Frames.of();
    }

    public static Bowler of(String name) {
        return new Bowler(name);
    }

    public String getName() {
        return player.getName();
    }

    public boolean isBowlingFinish() {
        return frames.isBowlingFinish();
    }

    public List<StateDatas> getStates() {
        return frames.getAllStates();
    }

    public void playBowling(int pins) {
        Pins hitPins = Pins.of(pins);
        frames.hitPins(hitPins);
    }

}
