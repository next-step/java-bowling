package bowling.controller;

import bowling.domain.Player;
import bowling.service.BowlingService;
import bowling.ui.PlayerInputView;

public class BowlingApplication {

    public static void main(String[] args) {
        BowlingService bowlingService = new BowlingService();
        Player player = bowlingService.getInstanceFromInput(new PlayerInputView(), Player::from);
        System.out.println(player.getName());
    }
}
