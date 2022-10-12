package bowling.step2.domain;

import bowling.step2.domain.exception.PlayerNameException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerName {
    private static final String PLAYER_NAME_INPUT_FORMAT = "[A-Z]{3}";
    private final String playerName;
    
    public PlayerName(final String playerName) {
        this.playerName = checkPlayerNameFormat(playerName);
    }
    
    private String checkPlayerNameFormat(final String playerName) {
        final Matcher matcher = Pattern.compile(PLAYER_NAME_INPUT_FORMAT).matcher(playerName);
        if (!matcher.matches()) {
            throw new PlayerNameException();
        }
        
        return playerName;
    }
    
    public String getPlayerName() {
        return playerName;
    }
}
