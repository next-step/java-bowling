package bowling.step2.outputview.state;

public class Spare implements PitchStatus{
    @Override
    public String result(int count) {
        return "/";
    }
}
