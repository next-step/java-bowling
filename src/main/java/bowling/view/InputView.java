package bowling.view;

import bowling.ViewUtils;

public class InputView {
    private static final String PLAYER_NAME_INFORMATION = "플레이어 이름은(3 english letters)?: ";
    private static final String FELLED_PIN_COUNT_INFORMATION = "[%d 프레임] 투구 : ";
    public String getPlayerName() {
        ViewUtils.printLine(PLAYER_NAME_INFORMATION);

        return ViewUtils.readLine();
    }

    public int getFelledPin(int frameNumber) {
        ViewUtils.printLine(String.format(FELLED_PIN_COUNT_INFORMATION, frameNumber));
        return ViewUtils.readLineToInt();
    }
}
