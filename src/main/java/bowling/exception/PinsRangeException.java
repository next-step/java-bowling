package bowling.exception;

import bowling.domain.Pins;

@SuppressWarnings("serial")
public class PinsRangeException extends IllegalArgumentException {
	public PinsRangeException() {
		super(String.format("볼링 핀은 %d ~ %d 사이만 가능합니다.", Pins.MIN_OF_PINS, Pins.MAX_OF_PINS));
	}
}
