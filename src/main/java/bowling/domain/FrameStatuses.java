package bowling.domain;

import bowling.domain.exceptions.TooManyFrameStatusesException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FrameStatuses {
    private static final int MAX_SIZE = 3;

    private List<FrameStatus> frameStatusList;

    public FrameStatuses(List<FrameStatus> frameStatusList) {
        validate(frameStatusList);

        this.frameStatusList = new ArrayList<>(frameStatusList);
    }

    private void validate(List<FrameStatus> frameStatusList) {
        if (frameStatusList.size() > MAX_SIZE) {
            throw new TooManyFrameStatusesException("길이가 4이상인 FrameStatus 컬렉션은 생성할 수 없습니다.");
        }
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
