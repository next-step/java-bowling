package bowling.view;

import bowling.model.Name;

public class NameView extends InputView {

    private static final String GET_PLAYER_NAME_MESSAGE = "플레이어 이름은 (3 english letters)? : ";

    public static Name getPlayerName() {
        return new Name(getStringValue(GET_PLAYER_NAME_MESSAGE));
    }
}
