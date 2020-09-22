package bowling.model.scoreboard;

import bowling.model.Player;
import bowling.model.frame.Frames;
import bowling.model.frame.dto.FramesDto;
import bowling.model.score.Scores;

import java.util.*;

import static bowling.util.ValidationUtils.throwIfNull;

public class ScoreBoard {
    private final Map<Player, Frames> frames;

    public ScoreBoard() {
        frames = new LinkedHashMap<>();
    }

    public void add(Player player) {
        if (frames.containsKey(player)) {
            throw new IllegalArgumentException("이미 존재하는 플레이어입니다.");
        }
        frames.put(throwIfNull(player), new Frames());
    }

    public Frames get(Player player) {
        if (!frames.containsKey(player)) {
            throw new IllegalArgumentException("존재하지 않는 플레이어입니다.");
        }
        return frames.get(player);
    }

    public Collection<Player> getPlayers() {
        return frames.keySet();
    }

    public boolean isEnded() {
        return frames.values().stream()
                .map(Frames::isEnded)
                .reduce(true, (acc, cur) -> acc = acc & cur);
    }

    public ScoreBoardDto toDto() {
        Map<Player, FramesDto> framesDtos = new LinkedHashMap<>();
        frames.forEach((player, framesInstance) -> framesDtos.put(player, framesInstance.getDto()));

        Map<Player, Scores> scores = new LinkedHashMap<>();
        frames.forEach((player, framesInstance) -> scores.put(player, framesInstance.getScores()));

        return new ScoreBoardDto(framesDtos, scores);
    }
}
