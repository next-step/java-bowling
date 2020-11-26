package bowling.view.printable;

public class ScoreBoardPrintable extends Printable {
    private static final String header = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";

    @Override
    public void print() {
        print(header);
    }
}
