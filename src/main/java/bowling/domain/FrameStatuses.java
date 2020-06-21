package bowling.domain;

import java.util.List;
import java.util.Objects;

public class FrameStatuses {
    private List<FrameStatus> frameStatusList;

    public FrameStatuses(List<FrameStatus> frameStatusList) {
        this.frameStatusList = frameStatusList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameStatuses that = (FrameStatuses) o;
        return Objects.equals(frameStatusList, that.frameStatusList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameStatusList);
    }
}
