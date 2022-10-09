package bowling.step2.domain;

import bowling.step2.domain.exception.PlayerNameException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerName {
    private final String playerName;
    
    public PlayerName(final String playerName) {
        this.playerName = checkPlayerNameFormat(playerName);
    }
    
    private String checkPlayerNameFormat(final String playerName) {
        final Matcher matcher = Pattern.compile("[A-Z]{3}").matcher(playerName);
        if (!matcher.matches()) {
            throw new PlayerNameException();
        }
        
        return playerName;
    }
}
