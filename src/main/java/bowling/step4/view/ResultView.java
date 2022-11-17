package bowling.step4.view;

import bowling.step4.dto.*;

import java.util.Map;

public class ResultView {

    private static final String WALL_SHAPE = "|";
    private static final String BLANK = " ";
    private static final int COLUMN_WIDTH = 6;
    private static final int COLUMN_LAST_INDEX = 10;

    public static void printScoreBoard(ResultDto resultDto) {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        for (int i = 0; i < resultDto.playerDtoList.size(); i++) {
            printName(resultDto.playerDtoList.get(i).name);
            printPinCount(resultDto.playerDtoList.get(i).framesDto);
            printScore(resultDto.playerDtoList.get(i).framesDto);
        }
    }

    private static void printScore(FramesDto framesDto) {
        System.out.print("|      ");
        Map<Integer, FrameDto> frameMap = framesDto.frameDtoMap;
        for (int i = 1; i <= frameMap.size(); i++) {
            System.out.print(WALL_SHAPE);
            System.out.print(getScoreString(frameMap.get(i).score));
        }
        System.out.println(WALL_SHAPE);
    }

    private static String getScoreString(ScoreDto scoreDto) {
        if (scoreDto.score.isBlank()) {
            return BLANK.repeat(COLUMN_WIDTH);
        }
        String score = scoreDto.score;
        int blankCount = ((COLUMN_WIDTH - score.length()) / 2);
        return BLANK.repeat(blankCount) + score + BLANK.repeat(COLUMN_WIDTH - blankCount - score.length());
    }

    private static void printName(String name) {
        System.out.print(WALL_SHAPE);
        int blankCount = (COLUMN_WIDTH - name.length()) / 2;
        System.out.print(BLANK.repeat(blankCount) + name + BLANK.repeat(COLUMN_WIDTH - blankCount - name.length()));
    }

    private static void printPinCount(FramesDto framesDto) {
        Map<Integer, FrameDto> frameMap = framesDto.frameDtoMap;
        for (int i = 1; i <= COLUMN_LAST_INDEX; i++) {
            System.out.print(WALL_SHAPE);
            System.out.print(getPinCountString(frameMap.get(i).pinCounts));
        }
        System.out.println(WALL_SHAPE);
    }

    private static String getPinCountString(PinCountDto pinCountDto) {
        if (pinCountDto.count.isBlank()) {
            return BLANK.repeat(COLUMN_WIDTH);
        }

        String result = pinCountDto.count;
        int blankCount = ((COLUMN_WIDTH - result.length()) / 2);
        return BLANK.repeat(blankCount) + result + BLANK.repeat(COLUMN_WIDTH - blankCount - result.length());
    }
}
