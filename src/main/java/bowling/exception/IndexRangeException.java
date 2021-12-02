package bowling.exception;

import bowling.domain.Index;

@SuppressWarnings("serial")
public class IndexRangeException extends IllegalArgumentException {
	public IndexRangeException() {
		super(String.format("프레임의 Index 는 %d ~ %d 사이만 가능합니다.", Index.MIN_OF_INDEX, Index.MAX_OF_INDEX));
	}
}
