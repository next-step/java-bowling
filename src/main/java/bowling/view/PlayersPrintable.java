package bowling.view;

import bowling.domain.frame.FrameEnum;
import bowling.dto.*;

import java.util.Iterator;
import java.util.List;

import static bowling.asset.Const.MAX_FRAME_NUMBER;

class PlayersPrintable extends Printable {
    PlayersPrintable(PlayersDto playersDto) {
        append("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        playersDto.getPlayers()
                .forEach(this::append);
        append(lineSeparator);
    }

    private void append(PlayerDto player) {
        BowlDto bowlDto = player.getBowlDto();
        FramesDto framesDto = bowlDto.getFramesDto();
        PinsDto pinsDto = framesDto.getPinsDto();
        FrameEnumsDto frameEnumsDto = framesDto.getFrameEnumsDto();

        append(lineSeparator);
        append(String.format("|  %s |", player.getName()));
        Iterator<PinDto> itr = pinsDto.getPins().iterator();
        List<FrameEnumDto> frameEnums = frameEnumsDto.getFrameEnums();
        frameEnums.forEach(frameEnumDto -> append(frameEnumDto, itr));
        appendBlank(frameEnums.size());

        append(lineSeparator);
        append("|      |");
        List<ScoreDto> scores = bowlDto.getScoresDto()
                .getScores();
        scores.forEach(this::append);
        appendBlank(scores.size());
    }

    private void appendBlank(int size) {
        for (int i = 0; i < MAX_FRAME_NUMBER - size; i++) {
            append("      |");
        }
    }

    private void append(FrameEnumDto frameEnumDto, Iterator<PinDto> itr) {
        FrameEnum frameEnum = frameEnumDto.getFrameEnum();
        String str = "";
        if (frameEnum == FrameEnum.STRIKE) {
            str = " X";
            itr.next();
        }
        if (frameEnum == FrameEnum.SPARE) {
            str = String.format("%s|/",
                    toString(itr.next()));
            itr.next();
        }
        if (frameEnum == FrameEnum.MISS) {
            str = String.format("%s|%s",
                    toString(itr.next()),
                    toString(itr.next()));
        }
        if (frameEnum == FrameEnum.UNFINISHED) {
            str = toString(itr.next());
        }
        append(spacing(str));
    }

    private void append(ScoreDto scoreDto) {
        append(spacing(Integer.toString(
                scoreDto.getScore()
        )));
    }

    private String toString(PinDto pinDto) {
        int count = pinDto.getCountOfDownPins();
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
