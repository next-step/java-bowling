package bowling.domain;

import static bowling.domain.Pin.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FinalFrame implements Frame {
	private static final int MAX_PITCH_COUNT = 3;
	private final int index;
	private final List<Pin> pins;

	public FinalFrame() {
		this.index = 9;
		this.pins = new ArrayList<>();
	}

	public void pitch(int count) {
		if (this.isEnd()) {
			throw new IllegalArgumentException("이미 게임이 끝나서 공을 던질 수가 없네요.");
		}
		pins.add(
			pins.isEmpty() || getLastPin().isEnd()
				? new Pin(count)
				: getLastPin().next(count)
		);
	}

	public boolean isEnd() {
		if (pins.isEmpty()) {
			return false;
		}
		if (pins.size() == MAX_PITCH_COUNT - 1
			&& pins.stream()
				.mapToInt(Pin::getCount).sum() < MAX_PIN_COUNT) {
			return true;
		}
		return pins.size() >= MAX_PITCH_COUNT;
	}

	@Override
	public Frame next() {
		throw new IllegalArgumentException("최종 프레임은 다음 프레임을 만들 수 없습니다.");
	}

	List<Pin> getPins() {
		return pins;
	}

	private Pin getLastPin() {
		if (getPins().isEmpty()) {
			throw new IllegalArgumentException("해당 프레임에 핀이 없습니다.");
		}
		return pins.get(pins.size() - 1);
	}

	@Override
	public List<String> getScore() {
		return pins.stream()
			.map(Pin::getSymbolValue)
			.collect(Collectors.toList());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		FinalFrame that = (FinalFrame)o;
		return index == that.index &&
			Objects.equals(pins, that.pins);
	}

	@Override
	public int hashCode() {
		return Objects.hash(index, pins);
	}
}
