package bowling.view;

import bowling.ScannerUtil;

public class InputView {
    public static String getPlayerName() {
        return ScannerUtil.readLine();
    }
}
