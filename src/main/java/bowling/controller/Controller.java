package bowling.controller;

import bowling.domain.Board;
import bowling.domain.Player;
import bowling.domain.Score;
import bowling.domain.frame.Frame;
import bowling.domain.frame.LastFrame;
import bowling.domain.frame.NormalFrame;
import bowling.view.Input;
import bowling.view.Output;

import java.util.stream.IntStream;

public class Controller {
    public static void main(String[] args) {
        Board board = new Board();
        Player player = new Player(Input.inputPlayerName());
        Output.outputBoard(board, player);

        IntStream.range(1, 10).forEach(i -> {
            Score firstScore = new Score(Input.inputScore(i));
            Frame frame = new NormalFrame(firstScore);
            board.add(frame);
            Output.outputBoard(board, player);
            if (!firstScore.isStrike()) {
                frame.setSecondScore(new Score(Input.inputScore(i)));
                Output.outputBoard(board, player);
            }
        });

        Score firstScore = new Score(Input.inputScore(10));
        Frame frame = new LastFrame(firstScore);
        board.add(frame);
        Output.outputBoard(board, player);

        Score secondScore = new Score(Input.inputScore(10));
        frame.setSecondScore(secondScore);
        Output.outputBoard(board, player);

        if (firstScore.isStrike()) {
            frame.setThirdScore(new Score(Input.inputScore(10)));
        }
        if (frame.isSpare(firstScore, secondScore)) {
            frame.setThirdScore(new Score(Input.inputScore(10)));
        }
        Output.outputBoard(board, player);
    }
}
