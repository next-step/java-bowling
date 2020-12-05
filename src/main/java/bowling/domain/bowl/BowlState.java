package bowling.domain.bowl;

interface BowlState {
    boolean isPlayable(Bowl bowl, int frameNumber);
}
