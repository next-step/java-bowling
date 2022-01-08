package bowling.controller;

import bowling.domain.*;
import bowling.view.Input;
import bowling.view.Output;

public class Controller {
    public static void main(String[] args) {
        Board board = new Board();
        Player player = new Player(Input.inputPlayerName());
        Output.outputBoard(board,player);

        for (int index = 1; index < 10; index++) {
            Score firstScore = new Score(Input.inputScore(index));
            Frame frame = new NormalFrame(firstScore);
            board.add(frame);
            Output.outputBoard(board,player);
            if (!firstScore.isStrike()) {
                frame.setSecondScore(new Score(Input.inputScore(index)));
                Output.outputBoard(board,player);
            }
        }

        Score firstScore = new Score(Input.inputScore(10));
        Frame frame = new LastFrame(firstScore);
        board.add(frame);
        Output.outputBoard(board, player);
        frame.setSecondScore(new Score(Input.inputScore(10)));
        Output.outputBoard(board, player);
        if (frame.isStrike()) {
            frame.setThirdScore(new Score(Input.inputScore(10)));
        }
        Output.outputBoard(board, player);
    }
}
