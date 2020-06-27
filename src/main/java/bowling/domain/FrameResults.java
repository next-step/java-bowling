package bowling.domain;

import bowling.domain.exceptions.TooManyFrameResultsException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FrameResults {
    private static final int SPARE_SCORE = 10;
    private static final int MAX_SIZE = 3;

    private List<FrameResult> frameResultList;

    public FrameResults(List<FrameResult> frameResultList) {
        validate(frameResultList);

        this.frameResultList = new ArrayList<>(frameResultList);
    }

    public List<FrameResult> getList() {
        return new ArrayList<>(frameResultList);
    }

    private void validate(List<FrameResult> frameResultList) {
        if (frameResultList.size() > MAX_SIZE) {
            throw new TooManyFrameResultsException("길이가 4이상인 FrameResult 컬렉션은 생성할 수 없습니다.");
        }
    }

    public Integer calculateScore() {
        if (this.frameResultList.contains(FrameResult.SPARE)) {
            return calculateContainsSpare();
        }
        return this.frameResultList.stream()
                .mapToInt(FrameResult::parseToNumber)
                .sum();
    }

    private Integer calculateContainsSpare() {
        List<FrameResult> spareRemovedFrameResults = removeSpare(findSpareIndex());

        return calculateSpareRemovedFrameResults(spareRemovedFrameResults) + SPARE_SCORE;
    }

    private int findSpareIndex() {
        return this.frameResultList.indexOf(FrameResult.SPARE);
    }

    private List<FrameResult> removeSpare(int spareIndex) {
        List<FrameResult> spareRemovedList = new ArrayList<>(this.frameResultList);
        spareRemovedList.remove(spareIndex);
        spareRemovedList.remove(spareIndex - 1);

        return spareRemovedList;
    }

    private int calculateSpareRemovedFrameResults(List<FrameResult> spareRemovedFrameResults) {
        return spareRemovedFrameResults.stream()
                .mapToInt(FrameResult::parseToNumber)
                .sum();
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

    @Override
    public String toString() {
        return "FrameResults{" +
                "frameResultList=" + frameResultList +
                '}';
    }
}
