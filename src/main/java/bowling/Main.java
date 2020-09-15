package bowling;

import bowling.domain.Game;
import bowling.view.InputScanner;
import bowling.view.ResultViewer;

public class Main {
    public static void main(String[] args) {
        String name = InputScanner.getName("플레이어 이름은(3 english letters)?: ");
        Game game = Game.start();

        while (!game.isFinish()) {
            int number = game.getPlayNumber();
            int hitCount = InputScanner.getHitCount(String.format("%s프레임 투구 : ", number));

            ResultViewer.showHead();
            ResultViewer.showResultFrames(name, game.hit(hitCount));
            ResultViewer.showResultScores(game.getSumScores());
        }
    }
}