package bowling.domain;

import org.apache.commons.lang3.StringUtils;

import static bowling.domain.BowlingErrorMessage.PLAYER_NAME_BLANK;
import static bowling.domain.BowlingErrorMessage.PLAYER_NAME_TOO_LONG;
import static com.google.common.base.Preconditions.checkArgument;

public class Player {

    private static final int MAX_NAME_LENGTH = 3;

    private final String name;

    public Player(String name) {
        checkArgument(StringUtils.isNotBlank(name), PLAYER_NAME_BLANK);
        checkArgument(name.length() <= MAX_NAME_LENGTH, PLAYER_NAME_TOO_LONG, MAX_NAME_LENGTH);

        this.name = name;
    }

    public static Player of(String name) {
        return new Player(name);
    }

    public String getName() {
        return name;
    }
}
