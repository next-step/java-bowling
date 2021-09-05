package bowling.step2.outputview.state;

public class Strike implements PitchStatus {
    @Override
    public String result(int count) {
        return "X";
    }
}
