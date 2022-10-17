package bowling.step2.dto;

import bowling.step2.domain.Frames;
import bowling.step2.domain.Player;
import bowling.step2.domain.PlayerName;

public class PlayerDTO {
    private final String playerName;
    private final Frames frames;
    
    
    public PlayerDTO(final Player player) {
        final PlayerName playerName = player.getPlayerName();
        this.playerName = playerName.getPlayerName();
        
        this.frames = player.getFrames();
    }
    
    public String getPlayerName() {
        return playerName;
    }
    
    public Frames getFrames() {
        return frames;
    }
}
