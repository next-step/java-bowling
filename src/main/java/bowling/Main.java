package bowling;

import bowling.domain.Frame;
import bowling.domain.Game;
import bowling.view.InputScanner;
import bowling.view.ResultViewer;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        int playerCount = InputScanner.getPlayerCount("How many people? ");
        List<String> names = InputScanner.getNames(playerCount);
        Map<String, ResultViewer> resultViewerMap = ResultViewer.makeResultMap(names);

        Game game = Game.from(names);

        while (!game.isEnd()) {
            String playerName = game.getPlayerName();

            int hitCount = InputScanner.getHitCount(String.format("%s's turn : ", game.getPlayerName()));

            Frame frame = game.hit(hitCount);
            resultViewerMap.get(playerName).record(frame);

            ResultViewer.printAll(names, resultViewerMap);
        }
    }
}