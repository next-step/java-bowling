package bowling.step4.domain;

import bowling.step4.dto.PitchDto;
import bowling.step4.dto.ResultDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingGame {
    private final List<Player> players;

    public BowlingGame(List<String> names) {
        List<Player> players = new ArrayList<>();
        for (String name : names) {
            players.add(new Player(name));
        }
        this.players = players;
    }

    public List<Player> players() {
        return new ArrayList<>(players);
    }

    public List<ResultDto> createResult() {
        return this.players.stream().map(player -> {
            ScoreCalculator calculator = new ScoreCalculator(player.frames());
            return new ResultDto(
                    player.name(),
                    player.frames().frameMap().values().stream().map(frame -> PitchDto.from(frame.pitches())).collect(Collectors.toList()),
                    calculator.calculate()
            );
        }).collect(Collectors.toList());
    }
}
