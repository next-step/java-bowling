package bowling.domain;

import bowling.dto.FrameDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NormalFrame {
    private static final int SHOT_LIMIT = 2;

    private final List<ShotScore> shotScores;

    private NormalFrame(List<ShotScore> shotScores) {
        this.shotScores = shotScores;
    }

    static NormalFrame init() {
        return new NormalFrame(new ArrayList<>());
    }

    NormalFrame next(int shot) {
        return new NormalFrame(Arrays.asList(ShotScore.of(shot)));
    }

    void shot(int shot) {
        if (shotScores.isEmpty()) {
            shotScores.add(ShotScore.of(shot));
            return;
        }

        if (shotScores.size() == SHOT_LIMIT) {
            throw new IllegalStateException(String.format("shot NormalFrame fail. cannot shot over %d times", SHOT_LIMIT));
        }

        shotScores.add(shotScores.get(0).next(shot));
    }

    boolean isFrameClosed() {
        return shotScores.size() == SHOT_LIMIT ||
                shotScores.stream()
                        .anyMatch(ShotScore::isStrike);
    }

    public FrameDto getDto() {
        return new FrameDto(shotScores.stream()
                .map(ShotScore::getDto)
                .collect(Collectors.toList()));
    }
}
