package bowling.domain.state;

public interface FrameState {

    String BOWLING_STATE_SPLIT_DELIMITER = "|";
    String BOWLING_PRINT_GUTTER = "-";
    String BOWLING_STATE_SPARE = "/";
    String BOWLING_STATE_STRIKE = "X";

    FrameState bowl(int pin);

    String printResult();

    int totalScore();
}
