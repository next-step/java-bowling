package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Pitch;
import bowling.domain.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResultView {
    private static final String BOARD_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String BOARD_DIVISION = "|";
    private static final String BOARD_FRAME_DIVISION = " |  ";
    private static final String FALL_DOWN_PITCH_OF_FRAME_NO_MESSAGE = "%d프레임 투구 : %d\n";
    private static final String BOARD_BODY_CONTENT = "|  %s ";
    private static final String WHITE_SPACE = " ";
    private static final int ONE = 1;
    private static final int PINS_MIN_COUNT = 0;
    private static final int PINS_MAX_COUNT = 10;

    public static void showBoard(Player player, List<Frame> frames) {
        List<String> frameResultHistories = new ArrayList<>();
        for (Frame frame : frames) {
            showFrame(player, frame, frameResultHistories);
        }
    }

    private static void showFrame(Player player, Frame frame, List<String> frameResultHistories) {
        List<String> pitchResults = new ArrayList<>();
        int pinsCount = PINS_MAX_COUNT;
        Pitch previousPitch = null;
        for (Pitch pitch : frame.pitches()) {
            int fallDownCount = pinsCount - pitch.pinsSize();
            showFallDownPitch(frame.no(), fallDownCount);
            showFrameHeader();
            showPlayerName(player.name());
            showPrevResult(frameResultHistories);
            pitchResults.add(PitchResultHelper.pitchResult(previousPitch, pitch));
            showCurrentResult(pitchResults);
            pinsCount = pitchPinsSize(pitch.pinsSize());
            previousPitch = pitch;
            bodyEnd();
        }
        frameResultHistories.add(String.join(BOARD_DIVISION, pitchResults));
    }

    private static int pitchPinsSize(int pinsSize) {
        if (pinsSize == PINS_MIN_COUNT) {
            return PINS_MAX_COUNT;
        }
        return pinsSize;
    }

    private static void showFallDownPitch(int no, int fallDownCount) {
        System.out.printf(FALL_DOWN_PITCH_OF_FRAME_NO_MESSAGE, no + ONE, fallDownCount);
    }

    private static void showFrameHeader() {
        System.out.println(BOARD_HEADER);
    }

    private static void showPlayerName(String name) {
        System.out.printf(BOARD_BODY_CONTENT, name);
    }

    private static void showPrevResult(List<String> frameResultHistories) {
        if (frameResultHistories.isEmpty()) {
            return;
        }
        System.out.printf(BOARD_BODY_CONTENT, frameResultHistories.stream()
                .map(ResultView::checkLengthAndFillBlank)
                .collect(Collectors.joining(BOARD_FRAME_DIVISION)));
    }

    private static void showCurrentResult(List<String> pitchResults) {
        String result = String.join(BOARD_DIVISION, pitchResults);
        System.out.printf(BOARD_BODY_CONTENT, checkLengthAndFillBlank(result));
    }

    private static String checkLengthAndFillBlank(String result) {
        if (result.length() == ONE) {
            return result + WHITE_SPACE + WHITE_SPACE;
        }
        return result;
    }

    private static void bodyEnd() {
        System.out.print(BOARD_DIVISION);
        newLine();
        newLine();
    }

    public static void newLine() {
        System.out.println();
    }
}
