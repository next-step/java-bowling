package bowling.ui;

import bowling.domain.value.FrameNumber;
import bowling.domain.value.Pins;
import bowling.domain.value.Player;
import bowling.utils.InputUtils;

public class InputView {
    public Player inputPlayerName() {
        String playerName = InputUtils.input("플레이어 이름은(3 english letters)?: ");

        return Player.from(playerName);
    }

    public Pins inputPins(FrameNumber frameNumber) {
        int pins = InputUtils.inputToInteger(String.format("%d 프레임 투구 : ", frameNumber.getFrameNumber()));

        return Pins.from(pins);
    }
}
