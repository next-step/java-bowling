package bowling.view.printable;

public class AskRollPrintable extends Printable {

    private final int frameNo;

    public AskRollPrintable(int frameNo) {
        this.frameNo = frameNo;
    }

    @Override
    public void print() {
        print(frameNo + "프레임 투구 : ");
    }
}
