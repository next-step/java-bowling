package bowling;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import bowling.domain.Board;
import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingMain {

    private static final int INITIAL_PLAYER_NUMBER = 1;

    public static void main(String[] args) {
        List<Player> players = players();
        Board board = new Board(players);

        ResultView.header();
        ResultView.board(board);

        while (!board.isEnd()) {
            players.forEach(player -> play(board, player));
        }
    }

    private static List<Player> players() {
        return IntStream.rangeClosed(INITIAL_PLAYER_NUMBER, InputView.numberOfPlayers())
                    .mapToObj(number -> new Player(InputView.playerName(number)))
                    .collect(Collectors.toList());
    }

    private static void play(Board board, Player player) {
        Frames frames = board.frameOf(player);
        Frame currentFrame = frames.current();
        while (!currentFrame.isEnd()) {
            frames.bowl(InputView.fallenPinsOf(player));
            ResultView.header();
            ResultView.board(board);
        }
    }

}
