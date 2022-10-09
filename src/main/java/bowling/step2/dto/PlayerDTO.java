package bowling.step2.dto;

import bowling.step2.domain.Frames;
import bowling.step2.domain.Player;
import bowling.step2.domain.PlayerName;
import bowling.step2.domain.frame.Frame;

import java.util.Collections;
import java.util.List;

public class PlayerDTO {
    private final String playerName;
    private final List<Frame> frames;
    ;
    
    public PlayerDTO(final Player player) {
        final PlayerName playerName = player.getPlayerName();
        this.playerName = playerName.getPlayerName();
        
        final Frames frames = player.getFrames();
        this.frames = frames.getFrames();
    }
    
    public String getPlayerName() {
        return playerName;
    }
    
    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }
}
