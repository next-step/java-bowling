package bowling.view;

import bowling.utils.StringUtils;

public final class PlayerNameView {

    private static final int NAME_PADDING_SIZE = 5;

    private final String playerName;

    public PlayerNameView(String playerName) {
        this.playerName = playerName;
    }

    public String playerName() {
        return StringUtils.padLeft(playerName, NAME_PADDING_SIZE);
    }
}
