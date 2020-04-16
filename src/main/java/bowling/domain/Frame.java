package bowling.domain;

import bowling.dto.FrameDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Frame {
    private static final int SHOT_LIMIT = 2;

    private final ShotScores shotScores;
    private final boolean hasBonus;

    protected Frame(List<ShotScore> shotScores, boolean hasBonus) {
        this.shotScores = new ShotScores(shotScores);
        this.hasBonus = hasBonus;
    }

    static Frame init() {
        return new Frame(new ArrayList<>(), false);
    }

    Frame next(int shot) {
        Frame frame = new Frame(new ArrayList<>(), false);
        frame.shot(shot);
        return frame;
    }

    Frame last(int shot) {
        Frame frame = new Frame(new ArrayList<>(), true);
        frame.shot(shot);
        return frame;
    }

    void shot(int shot) {
        if (shotScores.isSize(getShotLimit())) {
            throw new IllegalStateException(String.format("shot NormalFrame fail. cannot shot over %d times", getShotLimit()));
        }

        shotScores.add(shot, hasBonus);
    }

    boolean isFrameSet() {
        return shotScores.isSize(getShotLimit()) ||
                (cannotShooting());
    }

    private boolean cannotShooting() {
        if (hasBonus) {
            return shotScores.isSize(SHOT_LIMIT) && !shotScores.isClear();
        }
        return shotScores.isClear();
    }

    FrameDto getDto() {
        return new FrameDto(shotScores.getDtoList(), Optional.empty());
    }

    private int getShotLimit() {
        return hasBonus ? SHOT_LIMIT + 1 : SHOT_LIMIT;
    }
}
