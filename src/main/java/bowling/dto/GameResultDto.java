package bowling.dto;

import bowling.domain.result.GameResult;

public class GameResultDto {
    private final String name;
    private final FrameResultsDto frameResultsDto;


    private GameResultDto(String name, FrameResultsDto frameResultsDto) {
        this.name = name;
        this.frameResultsDto = frameResultsDto;
    }

    public static GameResultDto of(GameResult gameResult) {
        return new GameResultDto(gameResult.userName(), FrameResultsDto.of(gameResult.getResults()));
    }

    public String getName() {
        return name;
    }

    public FrameResultsDto getFrameResultsDto() {
        return frameResultsDto;
    }

    @Override
    public String toString() {
        return "GameResultDto{" +
                "name='" + name + '\'' +
                ", frameResultsDto=" + frameResultsDto +
                '}';
    }
}
