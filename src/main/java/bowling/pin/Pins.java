package bowling.pin;

import java.util.Objects;

import bowling.exception.PinsException;

public class Pins {

	private static final int ALL_PINS_COUNT = 10;
	private final int knockingDownPins;

	private Pins(int knockingDownPins) {
		if (knockingDownPins > ALL_PINS_COUNT) {
			throw new PinsException(String.format("넘어진 핀의 총 합이 %d개를 넘을 수 없습니다.", ALL_PINS_COUNT));
		}
		this.knockingDownPins = knockingDownPins;
	}

	public static Pins of(int knockingDownPins) {
		return new Pins(knockingDownPins);
	}

	public int getKnockingDownPins() {
		return this.knockingDownPins;
	}

	public boolean isAllKnockingDown() {
		return this.knockingDownPins == ALL_PINS_COUNT;
	}

	public int addKnockingDownPins(Pins knockingDownPins) {
		int result = this.knockingDownPins + knockingDownPins.knockingDownPins;
		if (result > ALL_PINS_COUNT) {
			throw new PinsException(String.format("넘어진 핀의 총 합이 %d개를 넘을 수 없습니다.", ALL_PINS_COUNT));
		}
		return result;
	}

	public boolean isNotKnockedDownAtAll() {
		return this.knockingDownPins == 0;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Pins pins = (Pins) o;
		return knockingDownPins == pins.knockingDownPins;
	}

	@Override
	public int hashCode() {
		return Objects.hash(knockingDownPins);
	}
}
