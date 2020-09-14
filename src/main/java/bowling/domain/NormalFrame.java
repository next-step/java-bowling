package bowling.domain;

public class NormalFrame implements Frame{

    private int frameNo;
    private ScoringHistory scoringHistory;
    private Frame parent;

    @Override
    public String printableScore() {
        return String.format("")"|"
    }

    @Override
    public void record() {

    }
}
