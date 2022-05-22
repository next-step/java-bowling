package bowling.domain;

import java.util.Optional;

public class Hit {
    private final int first;
    private final Optional<Integer> second;

    public Hit(int first) {
        this.first = first;
        this.second = Optional.empty();
    }

    public Hit(int first, int second) {
        this.first = first;
        this.second = Optional.of(second);
    }

    int remainingPin() {
        return 10 - this.first;
    }

    int first() {
        return this.first;
    }

    int second() {
        if (this.second.isPresent()) {
            return this.second.get();
        }
        throw new NullPointerException("second is empty");
    }

    String firstStr() {
        return gutterHandler(this.first);
    }

    String secondStr() {
        if (this.second.isPresent()) {
            return gutterHandler(this.second.get());
        }
        throw new NullPointerException("second is empty");
    }

    private static String gutterHandler(int number) {
        if (number == 0) {
            return "-";
        }
        return number + "";
    }
}
