package bowling.dto;

import bowling.domain.Frame;
import bowling.domain.ShotScore;

import java.util.List;
import java.util.stream.Collectors;

public class FrameDto {
    private final List<ShotScore> shotScores;
    private final Integer frameScore;

    public FrameDto(Frame frame) {
        this.shotScores = frame.shotScores();
        this.frameScore = frame.getFrameScore();
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

    public Integer getFrameScore() {
        return frameScore;
    }
}
