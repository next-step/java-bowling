package bowling.dto;

import bowling.domain.Frame;
import bowling.domain.frameScore.DefaultFrameScore;
import bowling.domain.Player;
import bowling.domain.frameScore.FrameScore;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerDto {
    private final String name;
    private final List<FrameShotDto> frameShots;
    private final List<Integer> frameScores;

    public PlayerDto(Player player) {
        this.name = player.name();
        this.frameShots = player.frames()
                .getFrames()
                .stream()
                .map(FrameShotDto::new)
                .collect(Collectors.toList());
        this.frameScores = player.frames()
                .getFrames()
                .stream()
                .map(Frame::getFrameScore)
                .filter(FrameScore::isCalculated)
                .map(FrameScore::getScore)
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public List<FrameShotDto> getFrameShots() {
        return frameShots;
    }

    public List<Integer> getFrameScores() {
        return frameScores;
    }
}
