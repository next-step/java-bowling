package bowling.controller;

import bowling.domain.PlayFrames;
import bowling.domain.Player;
import bowling.view.Input;
import bowling.view.Output;

import static bowling.util.Const.HEADER_STR;

public class BowlingApp {
    public static void main(String[] args) {
        String name = Input.scan();
        Player player = new Player(name);
        Output.print(HEADER_STR);
        Output.print(player.getPlayFrame());
        PlayFrames playFrames = player.plays();
        Output.printFrames(playFrames);
    }
}
