package bowling.step4.dto;

import bowling.step4.domain.Player;

import java.util.List;
import java.util.stream.Collectors;

public class ResultDto {

    public final String name;
    public final List<PitchDto> pitchesDto;
    public final List<ScoreDto> scoresDto;

    public ResultDto(String name, List<PitchDto> pitchesDto, List<ScoreDto> scoresDto) {
        this.name = name;
        this.pitchesDto = pitchesDto;
        this.scoresDto = scoresDto;
    }


    public static ResultDto from(Player player) {
        return new ResultDto(
                player.name(),
                player.frames().frameMap().entrySet().stream().map(it -> PitchDto.from(it.getValue().pitches())).collect(Collectors.toList()),
                player.frames().frameMap().entrySet().stream().map(it -> ScoreDto.from(it.getValue().score())).collect(Collectors.toList())
        );
    }
}
