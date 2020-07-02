package bowling.step3.view;

import bowling.step3.domain.frame.Frames;
import bowling.step3.domain.process.BowlingGame;

import java.util.List;

public class OutputView {
    private static final String NAME_CATEGORY = "| NAME |";
    private static final String FRAME_NUMBER_FORMAT = "  %02d  |";
    private static final String PITCH_PART_FORMAT = "| %4s |  %s|";
    private static final String SCORE_PART_BLANK_FORMAT = "|      |";
    private static final String SCORE_PART_FORMAT = "  %2s  |";
    private static final String DELIMITER_SYMBOL = "|";

    public static void viewFrameBoard(BowlingGame bowlingGame) {
        System.out.println(printTopBoard());
        System.out.println(printScoreBoard(bowlingGame));
    }

    private static String printTopBoard() {
        StringBuilder stringBuilder = new StringBuilder(NAME_CATEGORY);
        for (int i = 0; i < 10; i++){
            stringBuilder.append(String.format(FRAME_NUMBER_FORMAT, i+1));
        }
        return stringBuilder.toString();
    }

    private static String printScoreBoard(BowlingGame bowlingGame) {
        Frames frames =  bowlingGame.getFrames();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(
                            PITCH_PART_FORMAT,bowlingGame.getPlayerName(),
                                            bowlingGame.viewFramesDisplay()));
        addBlankFrame(frames, stringBuilder);
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(SCORE_PART_BLANK_FORMAT);
        int resultScore = 0;
        for (int score : bowlingGame.getFramesScores()){
            resultScore += score;
            stringBuilder.append(String.format(SCORE_PART_FORMAT,resultScore));
        }
        addBlankFrameForScores(bowlingGame.getFramesScores(), stringBuilder);
        return stringBuilder.toString();
    }

    private static void addBlankFrame(Frames frames, StringBuilder stringBuilder) {
        for (int i = 0; i < 10 - frames.getFramesSize(); i++) {
            stringBuilder.append(String.format("%7s",DELIMITER_SYMBOL));
        }
    }

    private static void addBlankFrameForScores(List<Integer> scores, StringBuilder stringBuilder) {
        for (int i = 0; i < 10 - scores.size(); i++) {
            stringBuilder.append(String.format("%7s",DELIMITER_SYMBOL));
        }
    }
}
