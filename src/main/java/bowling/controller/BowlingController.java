package bowling.controller;

import bowling.domain.Player;
import bowling.domain.frame.FrameResultDto;
import bowling.domain.frame.Frames;
import bowling.domain.game.BowlingGameResult;
import bowling.domain.point.Point;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.List;

public class BowlingController {

    public static void runBowlingGame() {
        Player player = InputView.inputPlayerName();

        BowlingGameResult bowlingGameResult = BowlingGameResult.of(player, Frames.init());

        while (!bowlingGameResult.isGameOver()) {
            int frameNumber = bowlingGameResult.getFrameNumber();
            Point pointPitch = InputView.inputPitchBowl(frameNumber);

            bowlingGameResult.roll(pointPitch);
            List<FrameResultDto> result = bowlingGameResult.getResult();
            result.forEach(frameResultDto -> {
                List<Point> points = frameResultDto.getPoints();
                points.forEach(point ->
                        System.out.println("포인트 : " + point.getPoint()));
                System.out.println("스코어 : " + frameResultDto.getScoreDto().getClass().getName());
                System.out.println("스코어타입 : " + frameResultDto.getScoreType());
            });
            OutputView.printResult(bowlingGameResult.getPlayerName(), bowlingGameResult.getResult());
        }
    }


}
