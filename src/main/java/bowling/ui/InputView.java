package bowling.ui;

import bowling.domain.value.Pins;
import bowling.domain.value.Player;
import bowling.utils.InputUtils;

public class InputView {
    public int inputPlayerNumber() {
        return InputUtils.inputToInteger("How many people? ");
    }

    public Player inputPlayerName(int playerNumber) {
        String playerName = InputUtils.input(String.format("플레이어 %d의 이름은(3 english letters)?: ", playerNumber));

        return Player.from(playerName);
    }

    public Pins inputPins(Player player) {
        int pins = InputUtils.inputToInteger(String.format("%s's turn : ", player.getName()));

        return Pins.from(pins);
    }
}
