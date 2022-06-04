package bowling.controller;

import bowling.domain.Player;
import bowling.domain.Score;
import bowling.domain.ScoreType;
import bowling.domain.Subtotal;
import bowling.view.Input;
import bowling.view.Output;

import java.util.ListIterator;

public class BowlingApp {
    public static void main(String[] args) {
        String name = Input.scan();
        Player player = new Player(name);
        Output.printFrame(player);

        player.plays();
        Output.printFrames(player);
    }
}