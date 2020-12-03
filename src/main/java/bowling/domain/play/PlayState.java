package bowling.domain.play;

interface PlayState {
    void playFirst(PlayContext context, int frameNo);

    void playSecond(PlayContext context, int frameNo);

    void playBonus(PlayContext context, int frameNo);
}
