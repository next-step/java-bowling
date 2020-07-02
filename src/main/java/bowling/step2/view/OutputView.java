package bowling.step2.view;

import bowling.step2.domain.frame.Frames;
import bowling.step2.domain.process.BowlingGame;

public class OutputView {
    private static final String NAME_CATEGORY = "| NAME |";
    private static final String FRAME_NUMBER_FORMAT = "  %02d  |";
    private static final String PITCH_PART_FORMAT = "| %4s |  %s|";
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
                                            bowlingGame.viewFrames()));
        addBlankFrame(frames, stringBuilder);
        return stringBuilder.toString();
    }

    private static void addBlankFrame(Frames frames, StringBuilder stringBuilder) {
        for (int i = 0; i < 10 - frames.getFramesSize(); i++) {
            stringBuilder.append(String.format("%7s",DELIMITER_SYMBOL));
        }
    }
}
