package bowling.view;

import bowling.domain.*;
import bowling.domain.frame.Frame;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class ResultView {

    private static final int START_INDEX = 0;
    private static final int FRAME_START_INDEX = 1;
    private static final int WIDTH = 6;
    private static final String NAME_HEAD = "NAME";
    private static final String DELIMITER = "|";
    private static final String SPACE = " ";
    private static final String NUMBER_FORMAT = "%02d";


    private static void printFramesHeader(int framesCount) {
        System.out.print(DELIMITER + center(NAME_HEAD) + DELIMITER);

        String results = IntStream.range(FRAME_START_INDEX, framesCount + FRAME_START_INDEX)
                .mapToObj(frameIndex -> center(String.format(NUMBER_FORMAT, frameIndex)) + DELIMITER)
                .collect(Collectors.joining());

        System.out.println(results);
    }

    public static void printEmptyFrames(Player player, int maxFrameCount) {


        printFramesEmptyBody(player, maxFrameCount);
        System.out.print(DELIMITER + center(SPACE) + DELIMITER);
        printNotCreatedFrames(START_INDEX, maxFrameCount);
    }

    private static void printFramesEmptyBody(Player player, int framesCount) {

        System.out.print(DELIMITER + center(player.getName()) + DELIMITER);

        String results = IntStream.range(FRAME_START_INDEX, framesCount + FRAME_START_INDEX)
                .mapToObj(frameIndex ->  center(SPACE) + DELIMITER)
                .collect(Collectors.joining());

        System.out.println(results);
    }


    public static void printCurrentFrame(boolean firstTurn, BowlingGame bowlingGame, int maxFrameCount) {
        if(firstTurn){
            printFramesHeader(maxFrameCount);
        }
        printFramesBody(bowlingGame, maxFrameCount);
        printFramesScore(bowlingGame, maxFrameCount);
    }
    public static void printCurrentFrames(List<BowlingGame> bowlingGames, int maxFrameCount) {
        IntStream.range(START_INDEX, bowlingGames.size())
                .forEach(index -> printCurrentFrame(isFirstTurn(index), bowlingGames.get(index), maxFrameCount));
    }

    private static boolean isFirstTurn(int index) {
        return index == START_INDEX;
    }


    private static void printFramesScore(BowlingGame bowlingGame, int maxFrameCount) {
        int currentFramesCount = bowlingGame.countFrames();

        System.out.print(DELIMITER + center(SPACE) + DELIMITER);

        for (int i = 0; i <currentFramesCount; i++) {
            System.out.print(center(parsePitchScore(bowlingGame.searchFrame(i))) + DELIMITER);
        }

        printNotCreatedFrames(currentFramesCount, maxFrameCount);

    }

    private static void printNotCreatedFrames(int currentFramesCount, int maxFrameCount) {
        for (int j = currentFramesCount; j<maxFrameCount; j++) {
            System.out.print(center(SPACE) + DELIMITER);
        }

        System.out.println();
    }

    private static String parsePitchScore(Frame frame) {
        Score currentScore = frame.getScore();
        if (frame.isEnd() && currentScore.getLeftBonusCount() == 0) {
            return String.valueOf(frame.getScore().getScore());
        }

        return SPACE;
    }

    private static void printFramesBody(BowlingGame bowlingGame, int maxFrameCount) {

        int currentFramesCount = bowlingGame.countFrames();

        System.out.print(DELIMITER + center(bowlingGame.getPlayer().getName()) + DELIMITER);

        for (int i = 0; i <currentFramesCount; i++) {
            System.out.print(center(parsePitchResult(bowlingGame.searchFrame(i))) + DELIMITER);
        }

        printNotCreatedFrames(currentFramesCount, maxFrameCount);

    }

    private static String parsePitchResult(Frame searchFrame) {
        return searchFrame.expressState();
    }


    public static String center(String s){
        return center(s, WIDTH);
    }

    public static String center(String s, int size) {
        return String.format("%-" + size  + "s", String.format("%" + (s.length() + (size - s.length()) / 2) + "s", s));
    }


    public static void printInitFrames(List<BowlingGame> bowlingGames, int maxFrameCount) {
        printFramesHeader(maxFrameCount);
        bowlingGames.stream()
                .forEach(bowlingGame -> printEmptyFrames(bowlingGame.getPlayer(), maxFrameCount));
    }


}
