package bowling.domain;
import static bowling.util.Const.NULL;

public class Hit {
    private final int first;
    private final int second;

    public Hit(int first) {
        this.first = first;
        this.second = NULL;
    }

    public Hit(int first, int second) {
        this.first = first;
        this.second = second;
    }

    int remainingPin() {
        return 10 - this.first;
    }

    int first() {
        return this.first;
    }

    int second() {
        if (hasSecond()) {
            return this.second;
        }
        throw new NullPointerException("second is empty");
    }

    private boolean hasSecond() {
        return this.second > -1;
    }

    String firstStr() {
        return gutterHandler(this.first);
    }

    String secondStr() {
        if (hasSecond()) {
            return gutterHandler(this.second);
        }
        throw new NullPointerException("second is empty");
    }

    static String gutterHandler(int number) {
        if (number == 0) {
            return "-";
        }
        return number + "";
    }
}
