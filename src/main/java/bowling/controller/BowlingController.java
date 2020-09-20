package bowling.controller;

import bowling.model.BowlingGame;
import bowling.model.BowlingGames;
import bowling.model.Score;
import bowling.model.frame.Frame;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class BowlingController {

    public static void main(String[] args) {
        int countOfUsers = InputView.getCountOfUsers();
        BowlingGames bowlingGames = BowlingGames.of(InputView.getUserNames(countOfUsers));

        while (!bowlingGames.isEnd()) {
            String userName = bowlingGames.getTurnToUser();
            int countOfPins = InputView.getFallenPins(userName);
            bowlingGames.bowling(countOfPins);
            printResults(bowlingGames.getBowlingGames());
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

    private static void printResults(Stream<BowlingGame> bowlingGames) {
        bowlingGames.forEach(bowlingGame -> {
            ResultView.printScoreBoard(bowlingGame.getFramesSize(), bowlingGame.getUserName(), bowlingGame.getFrames());
            ResultView.printTotalScoreBoard(getTotalScores(bowlingGame.getFrames()));
        });
    }

}
