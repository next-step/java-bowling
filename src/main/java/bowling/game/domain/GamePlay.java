package bowling.game.domain;

import bowling.frame.domain.Board;
import bowling.pin.domain.Pin;
import bowling.player.domain.Player;

import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class GamePlay {

    private final List<Board> game;

    private GamePlay(List<Board> game) {
        this.game = game;
    }

    public static GamePlay play(List<String> playersName) {
        // playerName -> player -> Board -> Game
        return playersName.stream()
                .map(Player::of)
                .map(Board::init)
                .collect(collectingAndThen(toList(), GamePlay::new));
    }

    public void roll(Pin felled) {
        getCurrent().roll(felled);
    }

    public List<Board> getGame() {
        return game;
    }

    public Board getCurrent() {
        return game.stream()
                .filter(board -> !board.isGameOver())
                .min(Comparator.comparing(Board::size))
                .orElseThrow(() -> new IllegalArgumentException("모든 게임이 종료 되었습니다."));
    }

    public boolean isGameOver() {
        return game.stream()
                .map(Board::isGameOver)
                .reduce((b1, b2) -> b1 && b2)
                .orElseThrow(IllegalAccessError::new);
    }

    public Player getCurrentPlayer() {
        return getCurrent().getPlayer();
    }

}
