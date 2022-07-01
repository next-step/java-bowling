package bowling_step3.controller;

import bowling_step3.domain.*;
import bowling_step3.domain.state.GameOver;
import bowling_step3.domain.state.Ready;
import bowling_step3.view.Input;
import bowling_step3.view.Output;

import java.util.*;

public class BowlingApp {
    public static void main(String[] args) {
        int numPlayers = Input.scanNumPlayers();
        LinkedList<Player> players = new LinkedList<>();
        for (int i = 0; i < numPlayers; i++) {
            String name = Input.scanPlayer();
            Frames frames = Frames.create();
            players.add(new Player(name, frames));
        }
        for (int i = 1; i <= 10; i++) {
            for (Player player : players) {
                Frames frames = player.frames();
                Frame frame = frames.current();
                while (true) {
                    int randomPin = frame.scores().getRandom();
                    frame = frame.play(randomPin);
                    Map<Player, Subtotals> playerSubtotals = new HashMap<>();
                    players.stream().forEach(p -> playerSubtotals.put(p, p.frames().first().createSubtotals()));
                    Output.printPlayerResult(i, frame, player);
                    Output.printPlayersFrames(i, players, playerSubtotals);
                    if (frame.status() instanceof Ready || frame.status() instanceof GameOver) {
                        frames.renewCurrentIndex();
                        break;
                    }
                }
            }
        }
    }
}
