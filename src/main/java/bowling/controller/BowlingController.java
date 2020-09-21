package bowling.controller;

import bowling.model.BowlingGame;
import bowling.model.Score;
import bowling.model.User;
import bowling.model.BowlingUsers;
import bowling.model.frame.Frame;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class BowlingController {

    public static void main(String[] args) {
        int countOfUsers = InputView.getCountOfUsers();
        BowlingUsers bowlingUsers = BowlingUsers.of(InputView.getUserNames(countOfUsers));

        while (!bowlingUsers.isEnd()) {
            String userName = bowlingUsers.getTurnUserName();
            int countOfPins = InputView.getFallenPins(userName);
            bowlingUsers.bowling(countOfPins);
            printResults(bowlingUsers.getUsers());
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

    private static void printResults(Stream<User> users) {
        users.forEach(user -> {
            BowlingGame bowlingGame = user.getBowlingGame();
            ResultView.printScoreBoard(bowlingGame.getFramesSize(), user.getName(), bowlingGame.getFrames());
            ResultView.printTotalScoreBoard(getTotalScores(bowlingGame.getFrames()));
        });
    }

}
