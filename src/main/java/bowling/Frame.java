package bowling;

interface Frame {
    public final int NUM_OF_FRAMES = 10;
    public static final int MAX_TRY_COUNT = 2;

    public Frame play(int numberOfFallenPin);

    public boolean isTerminate();

    public String getScores();

    public Frame getNext();

    public boolean isLastFrame();
}
