package bowling.domain.State;

public interface State {
    String DELIMITER = "|";
    String STANDBY = " ";
    String GUTTER = "-";
    String SPARE = "/";
    String STRIKE = "X";

    State bowl(int falledPins);

    boolean isFinish();

    String getDisplayText();
}
