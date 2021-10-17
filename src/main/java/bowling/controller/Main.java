package bowling.controller;

import static bowling.view.InputView.ask;
import static bowling.view.InputView.askDigit;
import static bowling.view.OutputView.printResult;

import bowling.model.User;
import bowling.model.frame.Board;
import bowling.model.frame.Frame;
import bowling.model.frame.NormalFrame;

public class Main {
    public static void main(String[] args) {
        String userName = ask("플레이어 이름은(3 english letters)?:");
        User user = new User(userName);

        Frame currentFrame = new NormalFrame(1);
        Frame firstFrame = currentFrame;

        while (!currentFrame.isFinish()) {
            int falledPins = askDigit(currentFrame.getNo() + "프레임 투구 : ");
            currentFrame = currentFrame.bowl(falledPins);
            Board board = firstFrame.createBoard();
            printResult(userName, board);
        }
    }
}
