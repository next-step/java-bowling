package bowling.domain;

import bowling.dto.FrameDto;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FinalFrame implements Frame {
    private static final int SHOT_LIMIT = 3;

    private final List<ShotScore> shotScores;

    private FinalFrame(List<ShotScore> shotScores) {
        this.shotScores = shotScores;
    }

    static Frame of() {
        return new FinalFrame(new ArrayList<>());
    }

    public void shot(int shot) {
        if (shotScores.isEmpty() || shotScores.get(shotScores.size() - 1).isClear()) {
            shotScores.add(ShotScore.of(shot));
            return;
        }

        if (shotScores.size() == SHOT_LIMIT) {
            throw new IllegalStateException(String.format("shot FinalFrame fail. cannot shot over %d times", SHOT_LIMIT));
        }

        shotScores.add(shotScores.get(shotScores.size() - 1).next(shot));
    }

    @Override
    public Frame next(int shot) {
        throw new NotImplementedException();
    }

    public boolean isFrameClosed() {
        return shotScores.size() == SHOT_LIMIT ||
                shotScores.stream()
                        .filter(this::isNotClear)
                        .count() == 2;
    }

    private boolean isNotClear(ShotScore v) {
        return !v.isClear();
    }

    public FrameDto getDto() {
        return new FrameDto(shotScores.stream()
                .map(ShotScore::getDto)
                .collect(Collectors.toList()));
    }
}
