package bowling2.domain.frame;

import java.util.List;

public class FinalFrame2 extends Frame2{

    // TODO(jack.comeback) : 마지막 프레임한테 현재 프레임 물어볼 때 로직 구현
    @Override
    public Frame2 askCurrentFrame() {
        return null;
    }

    public FinalFrame2() {
    }

    public FinalFrame2(int index) {
        super(index);
    }

    public FinalFrame2(int restOfPins, List<Integer> fallenPins) {
        super(restOfPins, fallenPins);
    }

    public FinalFrame2(int index, int restOfPins, List<Integer> fallenPins) {
        super(index, restOfPins, fallenPins);
    }

    public FinalFrame2(int index, Frame2 prev, Frame2 next) {
        super(index, prev, next);
    }
}
