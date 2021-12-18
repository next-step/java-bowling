package bowling.service;

import bowling.domain.Board;
import bowling.domain.frame.Pin;
import bowling.domain.frame.Round;
import bowling.domain.game.BowlingGame;
import bowling.service.dto.BoardDto;

public class BowlingService {
    private BowlingGame bowlingGame;

    public BowlingService(BowlingGame bowlingGame) {
        this.bowlingGame = bowlingGame;
    }

    public BoardDto bowl(Pin pin) {
        Board board = new Board(bowlingGame.bowl(pin));
        return BoardDto.of(board);
    }

    public boolean isGameEnd() {
        return bowlingGame.isGameEnd();
    }

    public Round round() {
        return bowlingGame.round();
    }

}
