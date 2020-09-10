package bowling.ui.result;

public interface Display {
    String getName();

    String toResults();

    default String toDisplay() {
        return String.format("|%s|%s|", toName(), toResults());
    }

    default String toName() {
        return String.format("  %4s  ", getName());
    }

    default String toResult(String result) {
        return String.format(" %5s  ", result);
    }
}
