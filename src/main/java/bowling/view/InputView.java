package bowling.view;

import bowling.util.ScannerUtil;

public class InputView {
    public static String getPlayerName() {
        return ScannerUtil.readLine();
    }
}
