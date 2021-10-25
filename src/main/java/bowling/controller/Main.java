package bowling.controller;

import static bowling.view.InputView.ask;
import static bowling.view.InputView.askDigit;
import static bowling.view.OutputView.printResult;

import bowling.model.BowlingGame;
import bowling.model.frame.Board;

public class Main {
    public static void main(String[] args) {
        BowlingGame bowlingGame = new BowlingGame();
        int numberOfPlayers = askDigit("How many people?");
        for (int i = 1; i <= numberOfPlayers; i++) {
            String userName = ask("플레이어 이름은(3 english letters)?:");
            bowlingGame.register(userName);
        }

        while (!bowlingGame.isEndGame()) {
            String playerName = bowlingGame.getName();
            int falledPins = askDigit(playerName + "의 " + bowlingGame.getNo() + "프레임 투구 : ");
            Board board = bowlingGame.bowl(falledPins);
            printResult(playerName, board);
        }
    }
}
