package bowling.domain.state;

import java.util.Objects;

public class Progress implements State {
    private final boolean retry;

    public Progress() {
        this(false);
    }

    public Progress(boolean retry) {
        this.retry = retry;
    }

    @Override
    public boolean progressing() {
        return true;
    }

    @Override
    public boolean retryable() {
        return retry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Progress progress = (Progress) o;
        return retry == progress.retry;
    }

    @Override
    public int hashCode() {
        return Objects.hash(retry);
    }

    @Override
    public String toString() {
        return "Progress{" +
                "retry=" + retry +
                '}';
    }
}
