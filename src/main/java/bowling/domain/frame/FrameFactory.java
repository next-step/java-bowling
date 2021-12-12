package bowling.domain.frame;

public class FrameFactory {

    private FrameFactory() {

    }

    //TODO : FrameSupplier 인터페이스 하나 만들어서 DefaultFrameSupplier 하나 구현하셔도 별 문제 없을꺼같긴해요
    public static Frame getReadyFrame(Round round) {
        if (round.isLast()) {
            return FinalFrame.readyFrame();
        }

        return NormalFrame.readyFrame(round);
    }
}
