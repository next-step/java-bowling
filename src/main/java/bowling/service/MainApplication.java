package bowling.service;

import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public class MainApplication {
    public static void main(String[] args) {
        String userName = InputView.inputName();
        Player player = new Player(userName);
        ResultView.printScoreBoard(player);
    }
}
