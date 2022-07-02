package bowling_step3.controller;

import bowling_step3.domain.*;
import bowling_step3.domain.state.GameOver;
import bowling_step3.domain.state.Ready;
import bowling_step3.view.Input;
import bowling_step3.view.Output;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class BowlingApp {
    public static void main(String[] args) {
        int numPlayers = Input.scanNumPlayers();
        Players players = new Players();
        for (int i = 0; i < numPlayers; i++) {
            String name = Input.scanPlayer();
            Frames frames = Frames.create();
            players.add(new Player(name, frames));
        }
        Map<Player, Subtotals> playerSubtotals = new HashMap<>();
        for (int i = 1; i <= 10; i++) {
            playerSubtotals = iteratePlays(i, players.players());
        }
        Player winner = Subtotals.winner(playerSubtotals);
        Output.printWinner(winner);
    }


    public static Map<Player, Subtotals> iteratePlays(Integer round, LinkedList<Player> players) {
        return players.stream().map(player -> plays(players, round, player))
                .skip(players.size() - 1)
                .findFirst()
                .get();
    }

    private static Map<Player, Subtotals> plays(LinkedList<Player> players, Integer i, Player player) {
        Frames frames = player.frames();
        Frame frame = frames.current();
        return play(players, i, player, frames, frame);
    }

    private static Map<Player, Subtotals> play(LinkedList<Player> players, int round, Player player, Frames frames, Frame frame) {
        int randomPin = frame.scores().getRandom();
        frame = frame.play(randomPin);
        Map<Player, Subtotals> playerSubtotals = new HashMap<>();
        players.stream().forEach(p -> playerSubtotals.put(p, p.frames().first().createSubtotals()));
        Output.printPlayerResult(round, frame, player);
        Output.printPlayersFrames(round, players, playerSubtotals);
        if (frame.status() instanceof Ready || frame.status() instanceof GameOver) {
            frames.renewCurrentIndex();
            return playerSubtotals;
        }
        return play(players, round, player, frames, frame);
    }
}
