package bowling.service.dto;

import bowling.domain.result.GameResult;

public class GameResultDto {
    private final String name;
    private final FrameResultsDto frameResultsDto;


    private GameResultDto(String name, FrameResultsDto frameResultsDto) {
        this.name = name;
        this.frameResultsDto = frameResultsDto;
    }

    public static GameResultDto of(GameResult gameResult) {
        return null;
    }

}
