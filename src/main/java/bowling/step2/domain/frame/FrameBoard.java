package bowling.step2.domain.frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FrameBoard {
	private static List<String> results;

	public FrameBoard() {
		results = new ArrayList<>();
	}

	public void add(String mark) {
		results.add(mark);
	}

	public List<String> getResults() {
		return Collections.unmodifiableList(results);
	}
}
