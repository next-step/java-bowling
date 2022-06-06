package bowling.controller;

import bowling.domain.Frame;
import bowling.domain.FramesList;
import bowling.domain.Pins;
import bowling.domain.Users;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.ArrayList;
import java.util.List;


public class BowlingController {
    private static final int FINAL_ROUND = 10;

    public static void start(Users users) {
        FramesList userFrames = new FramesList(users);
        ResultView.printInit(users);
        List<List<Integer>> userScores = createScores(users.size());
        for (int round = 1; round < FINAL_ROUND; round++) {
            for (int i = 0; i < users.size(); i++) {
                playRound(userFrames, users, i, round, userScores);
            }
        }

        for (int i = 0; i < users.size(); i++) {
            playFinalRound(userFrames, users, i, userScores);
        }
    }

    private static List<List<Integer>> createScores(int size) {
        List<List<Integer>> userScores = new ArrayList<>();
        for(int i = 0; i<size; i++) {
            userScores.add(new ArrayList<>());
        }
        return userScores;
    }


    public static void playRound(FramesList framesList, Users users, int i, int round, List<List<Integer>> userScores) {
        while (!framesList.isFramesFinish(i, round)) {
            Pins pins = InputView.inputBowl(users.getUsers().get(i).getLetters());
            Frame nextFrame = framesList.getUserFrames().get(i).getFrame(round).bowl(pins.getPins());
            if (!framesList.getFrame(i, round).equals(nextFrame)) {
                framesList.getUserFrames().get(i).add(nextFrame);
            }
            userScores.set(i, framesList.getUserFrames().get(i).calculateTotalScore(round));
            ResultView.printState(users, framesList, round, userScores);
        }
    }

    public static void playFinalRound(FramesList framesList, Users users, int i, List<List<Integer>> userScores) {
        while (!framesList.isFramesFinish(i, FINAL_ROUND)) {
            Pins pins = InputView.inputBowl(users.getUsers().get(i).getLetters());
            framesList.getUserFrames().get(i).getFrame(FINAL_ROUND).bowl(pins.getPins());
            List<Integer> totalList = framesList.getUserFrames().get(i).calculateTotalScore(FINAL_ROUND);
            userScores.set(i, framesList.getUserFrames().get(i).calculateTotalScore(FINAL_ROUND));
            ResultView.printState(users, framesList, FINAL_ROUND, userScores);
        }
    }

}
