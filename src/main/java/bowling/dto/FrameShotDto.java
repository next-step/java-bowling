package bowling.dto;

import bowling.domain.NormalFrame;
import bowling.domain.ShotScore;

import java.util.List;
import java.util.stream.Collectors;

public class FrameShotDto {
    private final List<ShotScore> shotScores;

    public FrameShotDto(NormalFrame normalFrame) {
        this.shotScores = normalFrame.shotScores();
    }

    public List<String> getShotScores() {
        return shotScores
                .stream()
                .map(this::parseShotScore)
                .collect(Collectors.toList());
    }

    private String parseShotScore(ShotScore shotScore) {
        return ShotScoreParser.of(shotScore.scoreType()).parse(shotScore.singleScore());
    }
}
