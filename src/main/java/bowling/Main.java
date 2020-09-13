package bowling;

import bowling.domain.Game;
import bowling.view.InputScanner;
import bowling.view.ResultViewer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String name = InputScanner.getName("플레이어 이름은(3 english letters)?: ");
        Game game = Game.start();
        Map<Integer, List<String>> score = new HashMap<>();

        while (!game.isFinish()) {
            int number = game.getPlayNumber();
            int hitCount = InputScanner.getHitCount(String.format("%s프레임 투구 : ", number));

//            score.put(number, game.hit(hitCount));
//
//            ResultViewer.showResult(10, name, score);
        }
    }
}