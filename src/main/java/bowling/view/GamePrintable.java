package bowling.view;

import bowling.domain.FrameEnum;
import bowling.dto.*;

import java.util.Iterator;
import java.util.List;

import static bowling.asset.Const.MAX_FRAME_NO;
import static java.util.Map.Entry;

public class GamePrintable extends Printable {
    private final StringBuilder sb = new StringBuilder();
    private final GameDto dto;

    GamePrintable(GameDto gameDto) {
        dto = gameDto;
    }

    @Override
    public void print() {
        append("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        dto.getGame()
                .entrySet()
                .forEach(this::append);
        append(lineSeparator);
        print(sb);
    }

    private void append(String str) {
        sb.append(str);
    }

    private void append(Entry<PlayerDto, PlayerStatusDto> entry) {
        PlayerStatusDto playerStatusDto = entry.getValue();
        RollsDto rollsDto = playerStatusDto.getRollsDto();
        BoardDto boardDto = playerStatusDto.getBoardDto();

        append(lineSeparator);
        append(String.format("|  %s |", entry.getKey()
                .getName()));
        Iterator<RollDto> rollItr = rollsDto.getRolls().iterator();
        List<FrameDto> frames = boardDto.getFramesDto()
                .getFrames();
        frames.forEach(frameDto -> append(frameDto, rollItr));
        appendBlank(frames.size());

        append(lineSeparator);
        append("|      |");
        List<ScoreDto> scores = boardDto.getScoresDto()
                .getScores();
        scores.forEach(this::append);
        appendBlank(scores.size());
    }

    private void appendBlank(int size) {
        for (int i = 0; i < MAX_FRAME_NO - size; i++) {
            append("      |");
        }
    }

    private void append(FrameDto frameDto, Iterator<RollDto> rollItr) {
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
        if (frameEnum == FrameEnum.UNFINISHED) {
            str = rollToStr(rollItr.next());
        }
        append(spacing(str));
    }

    private void append(ScoreDto scoreDto) {
        append(spacing(Integer.toString(
                scoreDto.getScore()
        )));
    }

    private String rollToStr(RollDto rollDto) {
        int count = rollDto.getCountOfPins();
        return count <= 0
                ? "-"
                : Integer.toString(count);
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
