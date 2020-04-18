package bowling.domain.State;

public interface State {
    String STRIKE = "X";
    String DELIMITER = "|";
    String STANDBY="";

    State bowl(int felledPins);

    boolean isFinish();

    String getDesc();
}
