package bowling.view;

import java.util.List;

public interface ViewFrameResult {
    String playerName();

    List<String> symbols(int frameNumber);

    String score(int frameNumber);
}
