package bowling.application;

import bowling.domain.Board;
import bowling.domain.Boards;
import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import bowling.domain.player.Players;
import bowling.domain.score.Score;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {

    public Boards startGame(Players players) {
        List<Board> boards = new ArrayList<>();

        for (Player player : players.getContent()) {
            Board board = Board.of(player, Frames.create());
            boards.add(board);
        }

        return Boards.of(boards);
    }

    public void addScore(Boards boards, Score score) {
        boards.addScore(score);
    }
}
