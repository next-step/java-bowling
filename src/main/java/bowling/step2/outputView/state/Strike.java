package bowling.step2.outputView.state;

public class Strike implements PitchStatus {
    @Override
    public String result(int count) {
        return "X";
    }
}
