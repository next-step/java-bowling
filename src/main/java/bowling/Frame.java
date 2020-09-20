package bowling;

interface Frame {
    public static final int MAX_TRY_COUNT = 2;

    public void play(int numberOfFallenPin);

    public boolean isTerminate();

    public String getScores();
}
