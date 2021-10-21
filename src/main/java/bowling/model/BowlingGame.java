package bowling.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import bowling.model.frame.Board;
import bowling.model.frame.NormalFrame;

public class BowlingGame {
    private LinkedList<Player> players = new LinkedList<>();

    public BowlingGame() {
    }

    public BowlingGame(List<String> playerNames) {
        for (String name : playerNames) {
            players.add(new Player(name, new NormalFrame(1)));
        }
    }

    public void register(String userName) {
        players.add(new Player(userName, new NormalFrame(1)));
    }

    public boolean isEndGame() {
        return players.isEmpty();
    }

    public Player getCurrentPlayer() {
        return players.getFirst();
    }

    public Board bowl(int falledPins) {
        Player player = getCurrentPlayer();
        boolean isFinish = player.bowl(falledPins);

        if (!player.isEndGame() && isFinish) {
            players.addLast(players.removeFirst());
        }

        if (player.isEndGame()) {
            players.removeFirst();
        }

        return player.createBoard();
    }

    public String getName() {
        return getCurrentPlayer().getName();
    }

    public int getNo() {
        return getCurrentPlayer().getFrameNo();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingGame game = (BowlingGame) o;
        return players.equals(game.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(players);
    }
}
