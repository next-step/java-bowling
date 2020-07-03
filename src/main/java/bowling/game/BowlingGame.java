package bowling.game;

import bowling.game.frame.Frames;
import bowling.player.domain.Player;
import bowling.player.vo.Name;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

public class BowlingGame {
    private final Queue<Player> players;
    private final ScoreBoard scoreBoard;

    public BowlingGame(List<String> names) {
        List<Player> players = createPlayers(names);
        this.players = new LinkedList<>(players);
        this.scoreBoard = new ScoreBoard(players);
    }

    public boolean isEndGame() {
        return players.isEmpty();
    }

    public void bowlCurrentPlayer(final int pinCount) {
        Player currentPlayer = players.peek();

        Frames frames = scoreBoard.findByPlayer(currentPlayer);
        frames.bowlCurrentFrame(pinCount);

        if (frames.isEndAllFrames()) {
            players.poll();
            return;
        }

        if (!frames.hasRemainChance()) {
            frames.createNextFrame();
            players.poll();
            players.add(currentPlayer);
        }
    }

    public Name getCurrentPlayerName() {
        return players.peek().getName();
    }

    public Map<Player, Frames> getPlayersFrames() {
        return scoreBoard.getPlayersFrames();
    }

    private List<Player> createPlayers(final List<String> names) {
        return names.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }
}
