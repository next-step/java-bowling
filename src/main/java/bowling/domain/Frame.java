package bowling.domain;

import bowling.dto.FrameDto;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    private static final int SHOT_LIMIT = 2;

    private final ShotScores shotScores;
    private final boolean hasBonus;

    protected Frame(List<ShotScore> shotScores, boolean hasBonus) {
        this.shotScores = new ShotScores(shotScores);
        this.hasBonus = hasBonus;
    }

    public static Frame init() {
        return new Frame(new ArrayList<>(), false);
    }

    public Frame next(int shot) {
        Frame frame = new Frame(new ArrayList<>(), false);
        frame.shot(shot);
        return frame;
    }

    public Frame last(int shot) {
        Frame frame = new Frame(new ArrayList<>(), true);
        frame.shot(shot);
        return frame;
    }

    public void shot(int shot) {
        if (shotScores.isSize(getShotLimit())) {
            throw new IllegalStateException(String.format("shot NormalFrame fail. cannot shot over %d times", getShotLimit()));
        }

        shotScores.add(shot, hasBonus);
    }

    public boolean isFrameClosed() {
        return shotScores.isSize(getShotLimit()) ||
                (cannotShooting());
    }

    private boolean cannotShooting() {
        if (hasBonus) {
            return shotScores.isSize(SHOT_LIMIT) && !shotScores.isClear();
        }
        return shotScores.isClear();
    }

    public FrameDto getDto() {
        return new FrameDto(shotScores.getDtoList());
    }

    private int getShotLimit() {
        return hasBonus ? SHOT_LIMIT + 1 : SHOT_LIMIT;
    }
}
