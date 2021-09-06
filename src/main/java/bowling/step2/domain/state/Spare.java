package bowling.step2.domain.state;

public class Spare implements PitchStatus{
    @Override
    public String result(int count) {
        return "/";
    }
}
