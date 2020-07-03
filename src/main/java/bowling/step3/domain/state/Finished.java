package bowling.step3.domain.state;

abstract class Finished implements State {
    private static final String FRAME_END = "프레임 종료";

    @Override
    public State pitch(Pins pitch) {
        throw new RuntimeException(FRAME_END);
    }

    @Override
    public boolean isFinish() {
        return true;
    }
}
