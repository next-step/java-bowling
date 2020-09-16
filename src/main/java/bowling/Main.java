package bowling;

import bowling.domain.Game;
import bowling.view.InputScanner;
import bowling.view.ResultViewer;

public class Main {
    public static void main(String[] args) {
        String name = InputScanner.getName("플레이어 이름은(3 english letters)?: ");

        Game game = Game.start(name);

        while (!game.isEnd()) {
            ResultViewer.print(
                    InputScanner.getHitCount(String.format("%s프레임 투구 : ", game.getPlayFrameNumber())),
                    game
            );
        }
    }
}