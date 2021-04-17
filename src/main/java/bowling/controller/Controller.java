package bowling.domain.controller;

import bowling.domain.Player;
import bowling.domain.view.InputView;
import bowling.domain.view.ResultView;

import java.util.List;
import java.util.stream.Collectors;

public class Controller {

    public static void startBowling() {
        List<Player> players = InputView.enterUserNames()
                .stream()
                .map(Player::get)
                .collect(Collectors.toList());

        ResultView.printPlayer(players);
        

    }

}
