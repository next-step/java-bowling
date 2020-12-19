package bowling.controller;

import bowling.domain.Player;
import bowling.domain.frame.FrameResultDto;
import bowling.domain.frame.Frames;
import bowling.domain.game.Bowling;
import bowling.domain.point.Point;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.List;

public class BowlingController {

    public static void runBowlingGame() {
        Player player = InputView.inputPlayerName();

        Bowling bowling = Bowling.of(player, Frames.init());

        while (!bowling.isGameFinished()) {
            int frameNumber = bowling.getCurrentFrameNumber();
            Point pointPitch = InputView.inputPitchBowl(frameNumber);

            bowling.pitch(pointPitch);
            List<FrameResultDto> result = bowling.getResult();
            result.forEach(frameResultDto -> {
                List<Point> points = frameResultDto.getPoints();
                points.forEach(point ->
                        System.out.println("포인트 : " + point.getPoint()));
                System.out.println("스코어 : " + frameResultDto.getScoreDto().getClass().getName());
                System.out.println("스코어타입 : " + frameResultDto.getScoreType());
            });
            OutputView.printResult(bowling.getPlayerName(), bowling.getResult());
        }
    }


}
