package bowling.view;

import bowling.domain.BowlingGame;
import bowling.domain.Frame;
import bowling.domain.PitchResults;
import bowling.domain.Player;


import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class ResultView {

    private static final int BOWLING_PIN_COUNT = 10;
    private static final int FRAME_START_INDEX = 1;
    private static final int NORMAL_FRAME_MAX_PITCH = 2;
    private static final int FINAL_FRAME_MAX_PITCH = 3;
    private static final int WIDTH = 6;
    private static final String NAME_HEAD = "NAME";
    private static final String DELIMITER = "|";
    private static final String SPACE = " ";
    private static final String NUMBER_FORMAT = "%02d";
    private static final String SPARE = "/";
    private static final String STRIKE = "X";
    private static final String GUTTER = "-";


    private static void printFramesHeader(int framesCount) {
        System.out.print(DELIMITER + center(NAME_HEAD) + DELIMITER);

        String results = IntStream.range(FRAME_START_INDEX, framesCount + FRAME_START_INDEX)
                .mapToObj(frameIndex -> center(String.format(NUMBER_FORMAT, frameIndex)) + DELIMITER)
                .collect(Collectors.joining());

        System.out.println(results);
    }

    public static void printEmptyFrames(Player player, int maxFrameCount) {
        System.out.println();
        printFramesHeader(maxFrameCount);
        printFramesEmptyBody(player, maxFrameCount);
    }

    private static void printFramesEmptyBody(Player player, int framesCount) {

        System.out.print(DELIMITER + center(player.getName()) + DELIMITER);

        String results = IntStream.range(FRAME_START_INDEX, framesCount + FRAME_START_INDEX)
                .mapToObj(frameIndex ->  center(SPACE) + DELIMITER)
                .collect(Collectors.joining());

        System.out.println(results);
    }


    public static void printCurrentFrame(BowlingGame bowlingGame, int maxFrameCount) {
        System.out.println();
        printPitchResult(bowlingGame);
        printFramesHeader(maxFrameCount);
        printFramesBody(bowlingGame, maxFrameCount);
    }

    private static void printFramesBody(BowlingGame bowlingGame, int maxFrameCount) {

        int currentFramesCount = bowlingGame.getFrames().getFrames().size();

        System.out.print(DELIMITER + center(bowlingGame.getPlayer().getName()) + DELIMITER);

        for (int i = 0; i <currentFramesCount; i++) {
            System.out.print(center(parsePitchResult(bowlingGame.getFrames().getFrames().get(i).getPitchResults())) + DELIMITER);
        }

        for (int j = currentFramesCount; j<maxFrameCount; j++) {
            System.out.print(center(SPACE) + DELIMITER);
        }

        System.out.println();

    }

    private static String parsePitchResult(PitchResults pitchResults) {

        if (pitchResults.size() == FINAL_FRAME_MAX_PITCH) {
            return parseLastFramePitchResult(pitchResults);
        }

        if (pitchResults.size() == NORMAL_FRAME_MAX_PITCH) {
            return parseNormalFramePitchResult(pitchResults);

        }

        return pitchResults.getPitchResults().stream().map(pitchResult -> pitchNumberToChar(pitchResult.getPinCount()))
                .collect(Collectors.joining(DELIMITER));

    }

    private static String parseNormalFramePitchResult(PitchResults pitchResults) {
        int totalCount = pitchResults.sumUpCurrentResult();

        if(totalCount == BOWLING_PIN_COUNT){
            return pitchNumberToChar(pitchResults.findResult(0))+ DELIMITER + SPARE;
        }

        return pitchResults.getPitchResults().stream().map(pitchResult -> pitchNumberToChar(pitchResult.getPinCount()))
                .collect(Collectors.joining(DELIMITER));
    }

    private static String parseLastFramePitchResult(PitchResults pitchResults) {
        String first = pitchNumberToChar(pitchResults.findResult(0));
        String second = pitchNumberToChar(pitchResults.findResult(1));

        if (pitchResults.findResult(0) + pitchResults.findResult(1) == BOWLING_PIN_COUNT) {
            second = SPARE;
        }

        return first + DELIMITER + second + DELIMITER + pitchNumberToChar(pitchResults.findResult(2));
    }

    private static String pitchNumberToChar(int pitchNumber) {
        if(pitchNumber == BOWLING_PIN_COUNT){
            return STRIKE;
        }

        if(pitchNumber == 0){
            return GUTTER;
        }

        return String.valueOf(pitchNumber);
    }

    private static void printPitchResult(BowlingGame bowlingGame) {
        Frame currentFrame = bowlingGame.getFrames().getFrames().getLast();

        int pitchResult = currentFrame.getPitchResults().findLast();

        System.out.println(currentFrame.getIndex() +"프레임 투구 : " + pitchResult);
    }

    public static String center(String s){
        return center(s, WIDTH);
    }

    public static String center(String s, int size) {
        return String.format("%-" + size  + "s", String.format("%" + (s.length() + (size - s.length()) / 2) + "s", s));
    }


}
