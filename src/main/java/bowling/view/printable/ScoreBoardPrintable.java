package bowling.view.printable;

import bowling.dto.*;

import java.util.List;

import static java.util.Map.Entry;

public class ScoreBoardPrintable extends Printable {
    private static final String header = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";

    private final ScoreBoardDto dto;

    public ScoreBoardPrintable(ScoreBoardDto scoreBoardDto) {
        dto = scoreBoardDto;
    }

    @Override
    public void print() {
        print(header);
        dto.getScoreBoard()
                .entrySet()
                .forEach(this::print);
    }

    private void print(Entry<PlayerDto, RollsAndBoardDto> entry) {
        PlayerDto player = entry.getKey();
        RollsAndBoardDto rollsAndBoardDto = entry.getValue();
        RollsDto rollsDto = rollsAndBoardDto.getRollsDto();
        BoardDto boardDto = rollsAndBoardDto.getBoardDto();

        boardDto.getFramesDto()
                .getFrames()
                .forEach(frameDto -> print(frameDto, rollsDto));
        boardDto.getScoresDto()
                .getScores()
                .forEach(this::print);
    }

    private void print(FrameDto frameDto, RollsDto rollsDto) {
        List<RollDto> rolls = rollsDto.getRolls();
    }

    private void print(ScoreDto scoreDto) {}
}
