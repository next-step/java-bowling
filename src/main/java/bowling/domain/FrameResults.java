package bowling.domain;

import bowling.domain.exceptions.TooManyFrameResultsException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FrameResults {
    private static final int MAX_SIZE = 3;

    private List<FrameResult> frameResultList;

    public FrameResults(List<FrameResult> frameResultList) {
        validate(frameResultList);

        this.frameResultList = new ArrayList<>(frameResultList);
    }

    private void validate(List<FrameResult> frameResultList) {
        if (frameResultList.size() > MAX_SIZE) {
            throw new TooManyFrameResultsException("길이가 4이상인 FrameResult 컬렉션은 생성할 수 없습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameResults that = (FrameResults) o;
        return Objects.equals(frameResultList, that.frameResultList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameResultList);
    }
}
