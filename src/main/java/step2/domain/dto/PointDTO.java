package step2.domain.dto;

import java.util.Objects;
import java.util.Optional;

public class PointDTO {
    public static final int ZERO = 0;
    private final int currentSize;
    private int maxPitches;
    private final Integer first;
    private final Integer second;
    private final Integer third;

    public int getMaxPitches() {
        return maxPitches;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public Integer getFirst() {
        return Optional.ofNullable(first).orElse(ZERO);
    }

    public Integer getSecond() {
        return Optional.ofNullable(second).orElse(ZERO);
    }

    public Integer getThird() {
        return Optional.ofNullable(third).orElse(ZERO);
    }

    public PointDTO(int maxPitches, int currentSize, Integer first, Integer second, Integer third) {
        this.currentSize = currentSize;
        this.maxPitches = maxPitches;
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public PointDTO(Builder builder) {
        this.currentSize = builder.currentSize;
        this.maxPitches = builder.maxPitches;
        this.first = builder.first;
        this.second = builder.second;
        this.third = builder.third;
    }

    public static Builder Builder(int currentSize, int maxPitches) {
        return new Builder(currentSize, maxPitches);
    }

    public static class Builder {
        private int currentSize;
        private int maxPitches;
        private Integer first;
        private Integer second;
        private Integer third;

        public Builder(int currentSize, int maxPitches) {
            this.currentSize = currentSize;
            this.maxPitches = maxPitches;
        }
        public Builder first(Integer first) {
            this.first = first;
            return this;
        }
        public Builder second(Integer second) {
            this.second = second;
            return this;
        }
        public Builder third(Integer third) {
            this.third = third;
            return this;
        }
        public PointDTO build() {
            return new PointDTO(this);
        }

        public Builder fill(int pitchesCount) {
            if (Objects.isNull(first)) {
                currentSize++;
                return first(pitchesCount);
            }
            if (Objects.isNull(second)) {
                currentSize++;
                return second(pitchesCount);
            }
            if (Objects.isNull(third)) {
                currentSize++;
                return third(pitchesCount);
            }
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointDTO pointDTO = (PointDTO) o;
        return currentSize == pointDTO.currentSize &&
                Objects.equals(first, pointDTO.first) &&
                Objects.equals(second, pointDTO.second) &&
                Objects.equals(third, pointDTO.third);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentSize, first, second, third);
    }
}
