package bowling.view;

import bowling.domain.frame.FrameEnum;
import bowling.dto.*;

import java.util.Iterator;
import java.util.List;

import static bowling.asset.Const.MAX_FRAME_NO;
import static java.util.Map.Entry;

class GamePrintable extends Printable {
    GamePrintable(GameDto gameDto) {
        append("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        gameDto.getGame()
                .entrySet()
                .forEach(this::append);
        append(lineSeparator);
    }

    private void append(Entry<PlayerDto, PlayerStatusDto> entry) {
        PlayerStatusDto playerStatusDto = entry.getValue();
        PinsDto pinsDto = playerStatusDto.getRollsDto();
        BoardDto boardDto = playerStatusDto.getBoardDto();

        append(lineSeparator);
        append(String.format("|  %s |", entry.getKey()
                .getName()));
        Iterator<PinDto> rollItr = pinsDto.getRolls().iterator();
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

    private void append(FrameDto frameDto, Iterator<PinDto> rollItr) {
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

    private String rollToStr(PinDto pinDto) {
        int count = pinDto.getCountOfPins();
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
