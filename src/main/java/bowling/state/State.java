package bowling.state;

public interface State {
    int FELLED_ZERO_PINS = 0;
    int FELLED_ALL_PINS = 10;
    String DELIMITER = "|";
    String STANDBY = " ";
    String STRIKE = "X";
    String SPARE = "/";
    String GUTTER = "-";

    State bowl(int felledPins);

    boolean isFinished();

    String view();
}
