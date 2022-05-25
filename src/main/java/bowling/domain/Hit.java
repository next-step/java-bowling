package bowling.domain;

import static bowling.util.Const.*;

public class Hit {
    private final int first;
    private final int second;
    private final int third;

    public Hit(int first) {
        this.first = first;
        this.second = NULL;
        this.third = NULL;
    }

    public Hit(int first, int second) {
        this.first = first;
        this.second = second;
        this.third = NULL;
    }

    public Hit(Hit hit, int lastHit) {
        this.first = hit.first;
        this.second = hit.second;
        this.third = lastHit;
    }

    int remainingPin() {
        return MAX_PIN - this.first;
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

    boolean hasSecond() {
        return this.second > -1;
    }

    String firstStr() {
        return charHandler(this.first);
    }

    String secondStr() {
        if (hasSecond()) {
            return charHandler(this.second);
        }
        throw new NullPointerException("second is empty");
    }

    static String charHandler(int number) {
        if (number == GUTTER_NUMBER) {
            return "-";
        }
        if (number == MAX_PIN) {
            return "X";
        }
        return number + "";
    }

    public boolean last() {
        return this.third != NULL;
    }

    public String thirdStr() {
        return charHandler(this.third);
    }

    public int third() {
        return this.third;
    }
}
