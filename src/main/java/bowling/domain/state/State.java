package bowling.domain.state;

public interface State {

    State bowl(int pins);

    boolean isFinish();

    String display();
}
