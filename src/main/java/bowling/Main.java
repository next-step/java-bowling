package bowling;

//import bowling.domain.Frames;
//import bowling.domain.Result;

import bowling.domain.Game;
import bowling.view.InputScanner;
import bowling.view.ResultViewer;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String name = InputScanner.getName("플레이어 이름은(3 english letters)?: ");
        Game game = Game.start();
        Map<Integer, String> score = new HashMap<>();

        while (!game.isFinish()) {
            int number = game.getPlayNumber();
            int hitCount = InputScanner.getHitCount(String.format("%s프레임 투구 : ", number));

            if (!score.containsKey(number)) {
                score.put(number, "");
            }

            score.put(number, game.hit(hitCount));

            ResultViewer.showResult(10, name, score);
        }
    }
}