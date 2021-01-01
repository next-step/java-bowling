package step2.domain.state;

public class StateFactory {

    public static State ready() {
        return new Ready();
    }

}
