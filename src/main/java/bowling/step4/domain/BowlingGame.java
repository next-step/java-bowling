package bowling.step4.domain;

import bowling.step4.dto.ResultDto;

import java.util.ArrayList;
import java.util.List;

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
        return players;
    }
    public ResultDto createResult() {
        return ResultDto.from(this.players);
    }
}
