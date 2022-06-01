package bowling.view;


import bowling.utils.InputUtils;

public class InputView {
    public static String inputPlayerName() {
        return InputUtils.scan("플레이어 이름은(3 english letters)?: ");
    }

    public static int inputScore(int frameIndex) {
        return InputUtils.scanNumber(frameIndex + "프레임 투구: ");
    }
}
