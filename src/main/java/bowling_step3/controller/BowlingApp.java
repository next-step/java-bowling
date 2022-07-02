package bowling_step3.controller;

import bowling_step3.domain.*;
import bowling_step3.view.Input;
import java.util.stream.Stream;

public class BowlingApp {
    public static void main(String[] args) {
        int numPlayers = Input.scanNumPlayers();
        Players players = new Players();
        for (int i = 0; i < numPlayers; i++) {
            String name = Input.scanPlayer();
            Frames frames = Frames.create();
            players.add(new Player(name, frames));
        }
        Stream.iterate(1, i -> i <= 10, i -> ++i).forEach(round -> players.iteratePlays(round));
    }
}
