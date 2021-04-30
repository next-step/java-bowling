package bowling.domain;

import bowling.exception.NameSizeMissMatchException;

public final class Player {

    private static final int NAME_SIZE_MAXIMUM = 3;

    private final String name;
    private final Frames frames;

    public static final Player from(final String name) {
        return new Player(name);
    }

    private Player(final String name) {
        validateSize(name);
        this.name = name.toUpperCase();
        this.frames = Frames.initialize();
    }

    private final void validateSize(final String name) {
        if (name.isEmpty() || name.length() > NAME_SIZE_MAXIMUM) {
            throw new NameSizeMissMatchException(name);
        }
    }


    public final boolean isFinish(int round) {
        return frames.isFinish(round);
    }

    public void bowl(int round, HitCount hitCount) {
        frames.bowl(round, hitCount);
    }
}
