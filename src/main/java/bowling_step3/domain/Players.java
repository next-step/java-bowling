package bowling_step3.domain;

import bowling_step3.domain.state.GameOver;
import bowling_step3.domain.state.Ready;
import bowling_step3.view.Output;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Players {
    private LinkedList<Player> players;

    public Players() {
        this.players = new LinkedList<>();
    }

    public void add(Player player) {
        this.players.add(player);
    }

    public void iteratePlays(Integer round) {
        this.players.stream().forEach(player -> plays(players, round, player));
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
