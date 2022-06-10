package bowling.controller;

import bowling.domain.*;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class BowlingController {
    private static final int FINAL_ROUND = 10;

    public static void start(List<String> letters) {
        Users users = new Users(letters.stream().map(User::new)
                .collect(Collectors.toList()));
        FramesList userFrames = new FramesList(users);
        ResultView.printInit(users);
        List<List<Integer>> userScores = createScores(users.size());

        for (int round = 1; round < FINAL_ROUND; round++) {
            play(users, userFrames, userScores, round);
        }

        for (int i = 0; i < users.size(); i++) {
            playFinalRound(userFrames, users, i, userScores);
        }
    }

    private static void play(Users users, FramesList userFrames, List<List<Integer>> userScores, int round) {
        for (int i = 0; i < users.size(); i++) {
            playRound(userFrames, users, i, round, userScores);
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
            Pins pins = InputView.inputBowl(users.getUserLetter(i));
            Frame nextFrame = framesList.getUserFrame(i,round)
                    .bowl(pins.getPins());
            checkEqualNextFrame(framesList, i, round, nextFrame);
            userScores.set(i, framesList.calculateTotalScore(i,round));
            ResultView.print(users, framesList, round, userScores);
        }
    }

    private static void checkEqualNextFrame(FramesList framesList, int i, int round, Frame nextFrame) {
        if (!framesList.getFrame(i, round).equals(nextFrame)) {
            framesList.addNextFrame(i, nextFrame);
        }
    }

    public static void playFinalRound(FramesList framesList, Users users, int i, List<List<Integer>> userScores) {
        while (!framesList.isFramesFinish(i, FINAL_ROUND)) {
            Pins pins = InputView.inputBowl(users.getUserLetter(i));
            framesList.getUserFrame(i,FINAL_ROUND)
                    .bowl(pins.getPins());
            userScores.set(i, framesList.calculateTotalScore(i, FINAL_ROUND));
            ResultView.print(users, framesList, FINAL_ROUND, userScores);
        }
    }

}
