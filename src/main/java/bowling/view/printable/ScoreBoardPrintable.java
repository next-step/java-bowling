package bowling.view.printable;

import bowling.domain.FrameEnum;
import bowling.dto.*;

import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.Map.Entry;

public class ScoreBoardPrintable extends Printable {
    private static final String header = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final int MAX_FRAME_NO = 10;

    private final ScoreBoardDto dto;

    public ScoreBoardPrintable(ScoreBoardDto scoreBoardDto) {
        dto = scoreBoardDto;
    }

    @Override
    public void print() {
        print(lineSeparator);
        print(header);
        dto.getScoreBoard()
                .entrySet()
                .forEach(this::print);
    }

    private void print(Entry<PlayerDto, RollsAndBoardDto> entry) {
        RollsAndBoardDto rollsAndBoardDto = entry.getValue();
        RollsDto rollsDto = rollsAndBoardDto.getRollsDto();
        BoardDto boardDto = rollsAndBoardDto.getBoardDto();

        print(lineSeparator);
        print(String.format("|  %s |", entry.getKey()
                .getName()));
        Iterator<RollDto> rollItr = rollsDto.getRolls().iterator();
        List<FrameDto> frames = boardDto.getFramesDto()
                .getFrames();
        frames.forEach(frameDto -> print(frameDto, rollItr));
        printBlank(MAX_FRAME_NO - frames.size());

        print(lineSeparator);
        print("|      |");
        List<ScoreDto> scores = boardDto.getScoresDto()
                .getScores();
        scores.forEach(this::print);
        printBlank(MAX_FRAME_NO - scores.size());
    }

    private void printBlank(int loop) {
        IntStream.range(0, loop)
                .forEach(i -> print("      |"));
    }

    private void print(FrameDto frameDto, Iterator<RollDto> rollItr) {
        FrameEnum frameEnum = frameDto.getFrameEnum();
        String str = "";
        if (frameEnum == FrameEnum.STRIKE) {
            str = " X";
            rollItr.next();
        }
        if (frameEnum == FrameEnum.SPARE) {
            str = String.format("%s|/",
                    rollToStr(rollItr.next()));
            rollItr.next();
        }
        if (frameEnum == FrameEnum.MISS) {
            str = String.format("%s|%s",
                    rollToStr(rollItr.next()),
                    rollToStr(rollItr.next()));
        }
        print(spacing(str));
    }

    private void print(ScoreDto scoreDto) {
        print(spacing(Integer.toString(
                scoreDto.getScore()
        )));
    }

    private String rollToStr(RollDto rollDto) {
        int count = rollDto.getCountOfPins();
        if (count <= 0) {
            return "-";
        }
        return Integer.toString(count);
    }

    private String spacing(String str) {
        int len = str.length();
        return len == 0
                ? "      |"
                : len == 1
                ? String.format("  %s   |", str)
                : len == 2
                ? String.format("  %s  |", str)
                : len == 3
                ? String.format("  %s |", str)
                : len == 4
                ? String.format(" %s |", str)
                : len == 5
                ? String.format(" %s|", str)
                : String.format("%s|", str);
    }
}
