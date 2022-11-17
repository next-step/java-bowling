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
        ScoreCalculator calculator = new ScoreCalculator();
        return new ResultDto(
                player.name(),
                player.frames().frameMap().values().stream().map(frame -> PitchDto.from(frame.pitches())).collect(Collectors.toList()),
                calculator.calculate(player.frames())
        );
    }
}
