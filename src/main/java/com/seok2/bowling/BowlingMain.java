package com.seok2.bowling;

import com.seok2.bowling.frame.domain.Board;
import com.seok2.bowling.frame.domain.BoardAssembler;
import com.seok2.bowling.pin.domain.Pin;
import com.seok2.bowling.user.domain.User;
import com.seok2.bowling.user.domain.UserAssembler;
import com.seok2.bowling.user.dto.UserDTO;
import com.seok2.bowling.view.InputView;
import com.seok2.bowling.view.OutputView;

public class BowlingMain {

    public static void main(String[] args) {
        User user = User.of(InputView.getUserName());
        Board board = Board.init(user);
        OutputView.print(BoardAssembler.assemble(board));
        while (!board.isGameOver()) {
            OutputView.printCurrentFrame(board.size());
            board.roll(Pin.of(InputView.getFelled()));
            OutputView.print(BoardAssembler.assemble(board));
        }
    }

}
