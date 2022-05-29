package bowling.view;

import bowling.domain.BowlingGame;
import bowling.domain.bowl.Bowl;
import bowling.domain.bowl.type.BowlType;
import bowling.domain.pitch.PitchResult;
import bowling.domain.player.Player;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Output {
    private static final String BOARD_HEAD = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String PLAYER_NAME = "|  %s |";
    private static final String SYMBOL_RESULT_FRAME = "  %-3s |";
    private static final String SCORE_RESULT_FRAME = "  %-3d |";
    private static final String SCORE_EMPTY_RESULT_FRAME = "|      |";
    private static final String EMPTY_RESULT_FRAME = "      |";
    private static final String SYMBOL_DELIMITER = "|";
    private static final String SPARE_SYMBOL = "/";
    private static final String STRIKE_SYMBOL = "X";
    private static final String GUTTER_SYMBOL = "-|-";
    private static final int MAX_FRAME_COUNT = 10;

    public static void printBoard(BowlingGame bowlingGame){
        Frames frames = bowlingGame.getFrames();
        Player player = bowlingGame.getPlayer();

        System.out.println(BOARD_HEAD);
        System.out.println(getPlayerName(player) + getSymbolBody(frames));
        System.out.println(getScoreBody(frames.getScores()));
    }

    public static String getPlayerName(Player player) {
        return String.format(PLAYER_NAME, player.getName());
    }

    public static String getSymbolBody(Frames frames) {
        StringBuilder res = new StringBuilder();
        for(Frame frame: frames.getFrames()){

            String symbol = frame.getBowls()
                    .stream()
                    .map(Bowl::getPitchResult)
                    .filter(pitchResult -> !pitchResult.getBowlType().equals(BowlType.READY))
                    .map(Output::getSymbol)
                    .collect(Collectors.joining(SYMBOL_DELIMITER));
            res.append(String.format(SYMBOL_RESULT_FRAME, symbol));
        }
        return res.toString();
    }

    private static String getSymbol(PitchResult pitchResult) {
        BowlType type = pitchResult.getBowlType();
        if(type.equals(BowlType.SPARE)){
            return pitchResult.getFirstHitCount()+SYMBOL_DELIMITER+SPARE_SYMBOL;
        }
        if(type.equals(BowlType.GUTTER)){
            return GUTTER_SYMBOL;
        }
        if(type.equals(BowlType.STRIKE)){
            return STRIKE_SYMBOL;
        }
        if(type.equals(BowlType.MISS)){
            return pitchResult.getFirstHitCount() +SYMBOL_DELIMITER+ pitchResult.getSecondHitCount();
        }
        return String.valueOf(pitchResult.getFirstHitCount());
    }

    public static String getScoreBody(List<Integer> scores){
        String scoreBody = SCORE_EMPTY_RESULT_FRAME;

        scoreBody += scores.stream()
                .map(score -> String.format(SCORE_RESULT_FRAME, score))
                .collect(Collectors.joining());

        scoreBody += getEmptyFrameResults(MAX_FRAME_COUNT - scores.size());

        return scoreBody;
    }

    public static String getEmptyFrameResults(int size) {
        return IntStream.range(0, size)
                .mapToObj(i->EMPTY_RESULT_FRAME)
                .collect(Collectors.joining());
    }
}
