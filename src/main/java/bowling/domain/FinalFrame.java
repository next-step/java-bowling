package bowling.domain;

import static bowling.domain.Pin.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FinalFrame implements Frame {
	private static final int MIN_PITCH_COUNT = 2;
	private static final int MAX_PITCH_COUNT = 3;
	private final int index;
	private final Pins pins;

	public FinalFrame() {
		this.index = 9;
		this.pins = new Pins();
	}

	public void pitch(int count) {
		if (this.isEnd()) {
			throw new IllegalArgumentException("이미 게임이 끝나서 공을 던질 수가 없네요.");
		}
		pins.pitch(count);
	}

	public boolean isEnd() {
		if (pins.isEmpty()) {
			return false;
		}
		if (pins.size() == MIN_PITCH_COUNT && pins.sumAll() < MAX_PIN_COUNT) {
			return true;
		}
		return pins.overPitching(MAX_PITCH_COUNT);
	}

	@Override
	public Frame next() {
		throw new IllegalArgumentException("최종 프레임은 다음 프레임을 만들 수 없습니다.");
	}

	public List<Pin> getPins() {
		return Collections.unmodifiableList(pins.getPins());
	}

	private Pin getLastPin() {
		if (getPins().isEmpty()) {
			throw new IllegalArgumentException("해당 프레임에 핀이 없습니다.");
		}
		return pins.getLastPin();
	}

	@Override
	public List<String> getScore() {
		return pins.getScore();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		FinalFrame that = (FinalFrame)o;
		return Objects.equals(pins, that.pins);
	}

	@Override
	public int hashCode() {
		return Objects.hash(pins);
	}
}
