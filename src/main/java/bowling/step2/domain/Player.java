package bowling.step2.domain;

import bowling.step2.dto.CountOfFallenPinsDTO;

public class Player {
    private final PlayerName playerName;
    private final Frames frames;
    
    public Player(final String playerName) {
        this.playerName = new PlayerName(playerName);
        this.frames = new Frames();
    }
    
    public boolean bowl(final CountOfFallenPinsDTO countOfFallenPinsDTO) {
        return frames.bowl(countOfFallenPinsDTO);
    }
    
    public PlayerName getPlayerName() {
        return playerName;
    }
    
    public Frames getFrames() {
        return frames;
    }
}