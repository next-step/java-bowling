package bowling.domain;

import static bowling.Messages.WARNING_PLAYERNAME_MUST_ENGLISH;
import static bowling.Messages.WARNING_PLAYERNAME_NOT_ALLOWED_LENGTH;

public class PlayerName {
    private static final int CRITERIA_NAME_LENGTH = 3;

    private String name;

    public PlayerName(String name) {
        validateNameLength(name);
        validateNameInEnglish(name);
        this.name = name;
    }

    private void validateNameLength(String name) {
        if (name.length() != CRITERIA_NAME_LENGTH) {
            throw new IllegalArgumentException(WARNING_PLAYERNAME_NOT_ALLOWED_LENGTH);
        }
    }

    private void validateNameInEnglish(String name){
        if(name.toLowerCase() == name.toUpperCase()){
            throw new IllegalArgumentException(WARNING_PLAYERNAME_MUST_ENGLISH);
        }
    }
}