package bowling.controller;

import bowling.domain.player.Player;
import bowling.view.InputView;

public class Application {

    public static void main(String[] args) {
        Player player = new Player(InputView.inputPlayerName());
    }
}
