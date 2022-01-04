package bowling.controller;

import bowling.domain.Board;
import bowling.domain.Frame;
import bowling.domain.Player;
import bowling.domain.Score;
import bowling.view.Input;
import bowling.view.Output;

public class Controller {
    public static void main(String[] args) {
        Board board = new Board();
        Player player = new Player(Input.inputPlayerName());
        Output.outputBoard(board,player);

        for (int railNumber = 1; railNumber < 11; railNumber++) {
            Score firstScore = new Score(Input.inputScore(railNumber));
            Frame frame = new Frame(firstScore);

            if (!firstScore.isStrike()) {
                frame.setSecondScore(new Score(Input.inputScore(railNumber)));
            }
            board.add(frame);
            Output.outputBoard(board,player);
        }
    }
}
