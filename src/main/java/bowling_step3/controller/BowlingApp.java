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
        System.out.println(players);
//        while 10 rounds
//            while n players
//                pitch till Done
//        Player lastPlayer = players.getLast();
//        while (!lastPlayer.gameOver()) {
        for (int i = 1; i <= 10; i++) {
            for (Player player : players) {
                Frames frames = player.frames();
                Frame frame = frames.current();
                while (true) {
                    int randomPin = frame.scores().getRandom();
                    frame = frame.play(randomPin);
//                    Subtotals subtotals = frames.first().createSubtotals();
                    Map<Player, Subtotals> playerSubtotals = new HashMap<>();
                    players.stream().forEach(p -> playerSubtotals.put(p, p.frames().first().createSubtotals()));
//                    Output.printFrames(i, frames, player);
//                    Output.printSubtotals(subtotals, player);
                    Output.printPlayerResult(i, frame, player);
                    Output.printPlayersFrames(i, players, playerSubtotals);
                    if (frame.status() instanceof Ready || frame.status() instanceof GameOver) {
                        frames.renewCurrentIndex();
                        break;
                    }
                }
            }
        }
//
//        Frame frame = frames.first();
//        while (!frame.status().isFinished()) {
//            int randomPin = frame.scores().getRandom();
//            frame = frame.play(randomPin);
//            Subtotals subtotals = frames.first().createSubtotals();
//            Output.printFrames(10, frames, player);
//            Output.printSubtotals(subtotals, player);
//        }
    }
}
