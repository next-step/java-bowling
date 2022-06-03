package bowling.domain;

import bowling.exception.InvalidNameException;
import bowling.util.LanguageUtil;

public class Player {

    private static final int NAME_LENGTH = 3;

    private final String name;

    public Player(String name) {
        if (validateName(name)) {
            throw new InvalidNameException();
        }
        this.name = name;
    }

    private boolean validateName(String name) {
        return name.length() != NAME_LENGTH || !LanguageUtil.isEnglish(name);
    }
}
