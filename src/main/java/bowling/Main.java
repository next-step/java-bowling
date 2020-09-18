package bowling;

import bowling.domain.Game;
import bowling.view.InputScanner;
import bowling.view.ResultViewer;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        int playerCount = InputScanner.getPlayerCount("How many people? ");
        List<String> names = InputScanner.getNames(playerCount);

//        Game game = Game.from(name);
//        ResultViewer resultViewer = new ResultViewer(game);
//
//        while (!game.isEnd()) {
//            int hitCount = InputScanner.getHitCount(String.format("%s프레임 투구 : ", game.getPlayFrameNumber()));
//
//            Frame frame = game.hit(hitCount);
//            resultViewer.record(frame);
//
//            resultViewer.printing();
//        }
    }
}