package bowling_step3.controller;

import bowling_step3.domain.*;
import bowling_step3.domain.state.GameOver;
import bowling_step3.domain.state.Ready;
import bowling_step3.view.Input;
import bowling_step3.view.Output;

import java.util.*;
import java.util.stream.Stream;

public class BowlingApp {
    public static void main(String[] args) {
        int numPlayers = Input.scanNumPlayers();
        LinkedList<Player> players = new LinkedList<>();
        for (int i = 0; i < numPlayers; i++) {
            String name = Input.scanPlayer();
            Frames frames = Frames.create();
            players.add(new Player(name, frames));
        }
        Stream.iterate(1, i -> i <= 10, i -> ++i).forEach(i ->
            players.stream().forEach(player -> plays(players, i, player))
        );
    }

    private static void plays(LinkedList<Player> players, Integer i, Player player) {
        Frames frames = player.frames();
        Frame frame = frames.current();
        play(players, i, player, frames, frame);
    }

    private static void play(LinkedList<Player> players, int round, Player player, Frames frames, Frame frame) {
        int randomPin = frame.scores().getRandom();
        frame = frame.play(randomPin);
        Map<Player, Subtotals> playerSubtotals = new HashMap<>();
        players.stream().forEach(p -> playerSubtotals.put(p, p.frames().first().createSubtotals()));
        Output.printPlayerResult(round, frame, player);
        Output.printPlayersFrames(round, players, playerSubtotals);
        if (frame.status() instanceof Ready || frame.status() instanceof GameOver) {
            frames.renewCurrentIndex();
            return;
        }
        play(players, round, player, frames, frame);
    }
}
