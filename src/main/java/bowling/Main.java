package bowling;

import bowling.view.GameView;
import bowling.view.InputScanner;
import bowling.view.ResultViewer;

public class Main {
    public static void main(String[] args) {
        String name = InputScanner.getName("플레이어 이름은(3 english letters)?: ");

        GameView gameView = new GameView(name);

        while (!gameView.isFinish()) {
            ResultViewer.print(
                    InputScanner.getHitCount(String.format("%s프레임 투구 : ", gameView.getPlayNumber())),
                    gameView
            );
        }
    }
}