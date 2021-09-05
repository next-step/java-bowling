package bowling.step2.outputview.state;

public class Miss implements PitchStatus{
    @Override
    public String result(int count) {
        return String.valueOf(count);
    }
}
