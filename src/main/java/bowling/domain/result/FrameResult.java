package bowling.domain.result;

import java.util.Objects;

public class FrameResult {
    private final String viewString;

    public FrameResult(String viewString) {
        this.viewString = viewString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FrameResult that = (FrameResult) o;
        return Objects.equals(viewString, that.viewString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(viewString);
    }

    @Override
    public String toString() {
        return "FrameResult{" +
                "viewString='" + viewString + '\'' +
                '}';
    }
}
