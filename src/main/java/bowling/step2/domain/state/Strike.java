package bowling.step2.domain.state;

public class Strike implements PitchStatus {
    @Override
    public String result(int count) {
        return "X";
    }
}
