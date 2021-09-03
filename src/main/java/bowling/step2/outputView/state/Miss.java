package bowling.step2.outputView.state;

public class Miss implements PitchStatus{
    @Override
    public String result(int count) {
        return String.valueOf(count);
    }
}
