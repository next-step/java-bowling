package bowling.dto;

import bowling.domain.frame.Frame;
import bowling.domain.shot.Shot;

import java.util.List;
import java.util.stream.Collectors;

public class FrameShotDto {
    private final List<Shot> shots;

    public FrameShotDto(Frame frame) {
        this.shots = frame.shots();
    }

    public List<String> getShots() {
        return shots
                .stream()
                .map(this::parseShot)
                .collect(Collectors.toList());
    }

    private String parseShot(Shot shot) {
        return ShotParser.of(shot.scoreType()).parse(shot.singleScore());
    }
}
