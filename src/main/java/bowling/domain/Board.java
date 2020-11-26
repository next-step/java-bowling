package bowling.domain;

class Board {
    private final Player player;
    private final Frames frames;

    Board(Player player, Frames frames) {
        this.player = player;
        this.frames = frames;
    }

    public void addFrame(int first, int second) {
        Frame frame = new Frame(
                CountOfPins.of(first),
                CountOfPins.of(second)
        );
        frames.add(frame);
    }
}
