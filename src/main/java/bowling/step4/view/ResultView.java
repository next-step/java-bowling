package bowling.step4.view;

import bowling.step4.dto.*;

import java.util.List;
import java.util.Map;

public class ResultView {

    private static final String WALL_SHAPE = "|";
    private static final String BLANK = " ";
    private static final String ZERO_TEXT = "-";
    private static final String STRIKE_TEXT = "X";
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

    private static String getPinCountString(List<PinCountDto> pinCountDtos) {
        if (pinCountDtos.size() == 0) {
            return BLANK.repeat(COLUMN_WIDTH);
        }

        int pitchCounts = pinCountDtos.size();
        String result = "";

        if (pitchCounts == 1) {
            result += getText(pinCountDtos.get(0).count);
        }

        if (pitchCounts == 2) {
            result += getTextInCaseOfTwo(pinCountDtos);
        }

        if (pitchCounts == 3) {
            result += getTextInCaseOfThree(pinCountDtos);
        }

        int blankCount = ((COLUMN_WIDTH - result.length()) / 2);
        return BLANK.repeat(blankCount) + result + BLANK.repeat(COLUMN_WIDTH - blankCount - result.length());
    }


    private static String getTextInCaseOfThree(List<PinCountDto> pitches) {

        if (pitches.get(0).count != 10 && pitches.get(0).count + pitches.get(1).count == 10) {
            return String.format("%s|/|%s", getText(pitches.get(0).count), getText(pitches.get(2).count));
        }

        if (pitches.get(1).count + pitches.get(2).count == 10) {
            return String.format("%s|%s|/", getText(pitches.get(0).count), getText(pitches.get(1).count));
        }

        return String.format("%s|%s|%s", getText(pitches.get(0).count), getText(pitches.get(1).count), getText(pitches.get(2).count));
    }

    private static String getTextInCaseOfTwo(List<PinCountDto> pitches) {
        if (pitches.get(0).count != 10 && pitches.get(0).count + pitches.get(1).count == 10) {
            return String.format("%s|/", getText(pitches.get(0).count));
        }
        return String.format("%s|%s", getText(pitches.get(0).count), getText(pitches.get(1).count));
    }

    private static String getText(int count) {
        if (count == 10) {
            return STRIKE_TEXT;
        }
        if (count == 0) {
            return ZERO_TEXT;
        }
        return String.valueOf(count);
    }
}
