package bowling.dto;

import bowling.domain.frame.Frame;
import bowling.domain.shot.Shot;

import java.util.List;
import java.util.stream.Collectors;

public class FrameShotDto {
    private final List<Shot> shots;

    public FrameShotDto(Frame normalFrame) {
        this.shots = normalFrame.shots();
    }

    public List<String> getShots() {
        return shots
                .stream()
                .map(this::parseShotScore)
                .collect(Collectors.toList());
    }

    private String parseShotScore(Shot shot) {
        return ShotScoreParser.of(shot.scoreType()).parse(shot.singleScore());
    }
}
