package bowling.domain.play;

interface PlayState {
    boolean isContinue(PlayStatus context, int frameNo);
}
