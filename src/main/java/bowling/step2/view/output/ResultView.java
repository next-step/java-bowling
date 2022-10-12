package bowling.step2.view.output;

import bowling.step2.domain.Score;
import bowling.step2.domain.frame.Frame;
import bowling.step2.dto.PlayerDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultView {
    private static final Map<Integer, String> SCORE_TO_DISPLAY = new HashMap<>();
    private static final String FRAME_DISPLAY_FORMAT = "%%%ds%s%%%ds";
    private static final String BOARD_BASE_DISPLAY = "| NAME  |  01   |  02   |  03   |  04   |  05   |  06   |  07   |  08   |  09   |  10   |";
    private static final int MAX_COUNT_OF_FRAME = 10;
    private static final int MAX_LENGTH_OF_PER_FRAME_DISPLAY = 7;
    private static final String DELIMITER = "|";
    private static final String EMPTY = "";
    private static final int READY_SCORE = -1;
    private static final int TEMPORARY_SPARE_NUMBER = 11;
    private static final int GUTTER_SCORE = 0;
    private static final String SPARE_DISPLAY = "/";
    private static final String STRIKE_DISPLAY = "X";
    private static final String GUTTER_DISPLAY = "-";
    
    static {
        SCORE_TO_DISPLAY.put(MAX_COUNT_OF_FRAME, STRIKE_DISPLAY);
        SCORE_TO_DISPLAY.put(TEMPORARY_SPARE_NUMBER, SPARE_DISPLAY);
        SCORE_TO_DISPLAY.put(GUTTER_SCORE, GUTTER_DISPLAY);
    }
    
    public static void printPlayerFramesDisplay(final PlayerDTO playerDTO) {
        System.out.println(BOARD_BASE_DISPLAY);
        System.out.println(getPlayerResultDisplayFormat(playerDTO));
    }
    
    private static String getPlayerResultDisplayFormat(final PlayerDTO playerDTO) {
        return IntStream.range(0, MAX_COUNT_OF_FRAME)
                .mapToObj(index -> getFrameDisplayFormat(playerDTO.getFrames(), index))
                .collect(Collectors.joining(DELIMITER, DELIMITER + parseFrameDisplayPrintFormat(playerDTO.getPlayerName()) + DELIMITER, DELIMITER));
    }
    
    private static String getFrameDisplayFormat(final List<Frame> frames, final int index) {
        if (frames.size() - 1 < index) {
            return parseFrameDisplayPrintFormat(EMPTY);
        }
        
        final Frame frame = frames.get(index);
        return parseFrameDisplayPrintFormat(parseScoresDisplay(frame.getScores()));
    }
    
    private static String parseScoresDisplay(final List<Score> scores) {
        return scores.stream()
                .filter(ResultView::isNotReady)
                .map(ResultView::scoreToDisplay)
                .collect(Collectors.joining(DELIMITER));
    }
    
    private static String scoreToDisplay(final Score score) {
        if (score.isSpare()) {
            return SCORE_TO_DISPLAY.get(TEMPORARY_SPARE_NUMBER);
        }
        
        return SCORE_TO_DISPLAY.getOrDefault(score.getFallenPins(), String.valueOf(score.getFallenPins()));
    }
    
    private static boolean isNotReady(final Score score) {
        return score.getFallenPins() != READY_SCORE;
    }
    
    private static String parseFrameDisplayPrintFormat(final String display) {
        return String.format(String.format(FRAME_DISPLAY_FORMAT, getLeftSpaceLength(display), display, getRightSpaceLength(display)), EMPTY, EMPTY);
    }
    
    private static int getRightSpaceLength(final String display) {
        return getLeftSpaceLength(display) + (MAX_LENGTH_OF_PER_FRAME_DISPLAY - display.length()) % 2;
    }
    
    private static int getLeftSpaceLength(final String display) {
        return (MAX_LENGTH_OF_PER_FRAME_DISPLAY - display.length()) / 2;
    }
}
