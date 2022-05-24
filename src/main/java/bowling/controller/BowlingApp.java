package bowling.controller;

import bowling.domain.Player;
import bowling.view.Input;
import bowling.view.Output;

public class BowlingApp {
    public static void main(String[] args) {
        String name = Input.scan();
        Player player = new Player(name);
        Output.printFrame(player);
        player.plays();
    }
}
