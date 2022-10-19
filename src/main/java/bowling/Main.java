package bowling;

import bowling.domain.PlayerName;
import bowling.view.InputView;
import bowling.view.ResultView;

public class Main {
    public static void main(String[] args) {
        PlayerName playerName = InputView.scanName();

        ResultView.printFrame(playerName);

        InputView.closeScan();
    }
}
