package bowling.step2.outputView.state;

public class Spare implements PitchStatus{
    @Override
    public String result(int count) {
        return "/";
    }
}
