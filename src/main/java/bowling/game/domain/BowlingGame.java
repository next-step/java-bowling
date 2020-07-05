package bowling.game.domain;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import bowling.frame.domain.Board;
import bowling.pin.domain.Pin;
import bowling.user.domain.User;
import java.util.List;

public class BowlingGame {

    private final List<Board> game;

    private BowlingGame(List<Board> game) {
        this.game = game;
    }

    public static BowlingGame of(List<String> users) {
        return users.stream()
            .map(User::of)
            .map(Board::init)
            .collect(collectingAndThen(toList(), BowlingGame::new));
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
            .min(comparing(Board::getLastIndex))
            .orElseThrow(() -> new IllegalArgumentException("모든 게임이 종료 되었습니다."));
    }

    public boolean isGameOver() {
        return game.stream()
            .map(Board::isGameOver)
            .reduce((b1, b2) -> b1 && b2)
            .orElseThrow(IllegalAccessError::new);
    }

    public User getCurrentUser() {
        return getCurrent().getUser();
    }
}