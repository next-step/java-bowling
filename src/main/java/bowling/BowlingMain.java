package bowling;

import bowling.frame.domain.Board;
import bowling.frame.domain.BoardAssembler;
import bowling.frame.domain.IndexAssembler;
import bowling.pin.domain.Pin;
import bowling.user.domain.User;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingMain {

    public static void main(String[] args) {
        User user = User.of(InputView.getUserName());
        Board board = Board.init(user);
        OutputView.print(BoardAssembler.assemble(board));
        while (!board.isGameOver()) {
            OutputView.printCurrentFrame(IndexAssembler.assemble(board.getLastIndex()));
            board.roll(Pin.of(InputView.getFelled()));
            OutputView.print(BoardAssembler.assemble(board));
        }
    }
}
