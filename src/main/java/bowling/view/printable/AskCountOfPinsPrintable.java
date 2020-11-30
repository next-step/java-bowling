package bowling.view.printable;

public class AskCountOfPinsPrintable extends Printable {

    private final int frameNo;

    public AskCountOfPinsPrintable(int frameNo) {
        this.frameNo = frameNo;
    }

    @Override
    public void print() {
        print(lineSeparator);
        print(String.format("%d프레임 투구 : ", frameNo));
    }
}
