package bowling.domain.state;

public class Strike extends EndState {
    public static Strike instance() {
        return new Strike();
    }
}
