package bowling.controller;

import bowling.model.BowlingGame;
import bowling.model.Score;
import bowling.model.User;
import bowling.model.frame.Frame;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class BowlingController {

    public static void main(String[] args) {
        BowlingGame bowlingGame = BowlingGame.of();
        User user = User.valueOf(InputView.getUserName());

        while (!bowlingGame.isEnd()) {
            int frameNo = bowlingGame.getPlayFrameNo();
            int countOfFallenPins = InputView.getFallenPins(frameNo);
            bowlingGame.bowling(countOfFallenPins);

            ResultView.printScoreBoard(frameNo, user.getName(), bowlingGame.getFrames());
            ResultView.printTotalScoreBoard(getTotalScores(bowlingGame.getFrames()));
        }
    }

    private static List<Integer> getTotalScores(Stream<Frame> frames) {
        Integer[] scoreArray = frames.filter(Frame::isEnd)
                .map(Frame::getScore)
                .filter(Score::isEndCalculate)
                .mapToInt(Score::getScore)
                .boxed()
                .toArray(Integer[]::new);

        Arrays.parallelPrefix(scoreArray, Integer::sum);
        return Arrays.asList(scoreArray);
    }

}
