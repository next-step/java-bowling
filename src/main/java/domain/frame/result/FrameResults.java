package domain.frame.result;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hspark on 02/12/2018.
 */
public class FrameResults {
	private List<FrameResult> frameResults;

	public FrameResults(List<FrameResult> frameResults) {
		this.frameResults = frameResults;
	}

	public FrameResult getByFrameNumber(int frameNumber) {
		return frameResults.stream().filter(frameResult -> frameResult.isSameFrameNumber(frameNumber))
			.findAny().orElseThrow(IllegalArgumentException::new);
	}

	public int getFrameResultCount() {
		return frameResults.size();
	}

	public List<String> toList(){
		return frameResults.stream().map(FrameResult::toString).collect(Collectors.toList());
	}

}
