package bowling.domain.play;

interface PlayState {
    boolean isPlayable(PlayStatus context, int frameNo);
}
