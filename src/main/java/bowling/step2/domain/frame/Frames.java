package bowling.step2.domain.frame;

import java.util.ArrayList;
import java.util.List;

public class Frames {
	private List<NormalFrame> normalFrames;
	private FinalFrame finalFrame;

	public Frames() {
		this.normalFrames = new ArrayList<>();
		this.finalFrame = new FinalFrame();
	}
}
