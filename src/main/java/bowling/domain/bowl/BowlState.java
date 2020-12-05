package bowling.domain.bowl;

interface BowlState {
    boolean isPlayable(Bowl context, int frameNo);
}
