package bowling.domain.frame;

public class FinalFrame implements Frame {
    private static final int MAX_TRY_COUNT = 3;

    private Pins pins;
    private int trying;

    public FinalFrame(Pins pins, int trying) {
        this.pins = pins;
        this.trying = trying;
    }

    public static Frame init() {
        return new FinalFrame(Pins.init(), 0);
    }

    public void bowl(int downPin) {
        pins.bowl(downPin);
        //스트라이크, 스페이 시 핀 새로 생성?? ?? 수정 예정
        this.trying++;
    }

    @Override
    public Frame next() {
        //this.pins = Pins.init();
        return new FinalFrame(Pins.init(), 0);
    }

    @Override
    public boolean isLastTryAtFrame() {
        //Strike, Spare 가 있는 경우 , 없는 경우 따라서
        return trying == MAX_TRY_COUNT;
    }

    @Override
    public String printFrameResult() {
        return null;
    }

    @Override
    public String toString() {
        return "FinalFrame{" +
                "pins=" + pins.getDownPin() +
                ", trying=" + trying +
                '}';
    }
}
