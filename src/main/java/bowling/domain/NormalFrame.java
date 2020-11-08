package bowling.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class NormalFrame {
	private static final int MAX_FRAME_INDEX = 8;
	private final int index;
	private final List<Pin> pins;

	public NormalFrame(int index) {
		this.validate(index);
		this.index = index;
		this.pins = new ArrayList<>();
	}

	private void validate(int index) {
		if (index < 0) {
			throw new IllegalArgumentException("0보다 작을 수 없습니다.");
		}
		if (index > MAX_FRAME_INDEX) {
			throw new IllegalArgumentException("일반 프레임의 갯수는 9개를 초과할 수 없습니다.");
		}
	}

	public static NormalFrame firstFrame() {
		return new NormalFrame(0);
	}

	public NormalFrame next() {
		return new NormalFrame(index + 1);
	}

	public void pitch(int count) {
		this.pins.add(new Pin(count));
	}

	public List<Pin> getPins() {
		return pins;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		NormalFrame that = (NormalFrame)o;
		return index == that.index &&
			Objects.equals(pins, that.pins);
	}

	@Override
	public int hashCode() {
		return Objects.hash(index, pins);
	}
}
