package bowling;

import bowling.domain.Frames;
import bowling.domain.PlayerName;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerRenderer {

    private final String playerName;
    private final List<FrameStateRenderer> frameStateRenderers;

    public PlayerRenderer(PlayerName playerName, Frames frames) {
        this.playerName = String.format("  %s ", playerName);
        this.frameStateRenderers = frames.toFrameStateRenderers();
    }

    public String render() {
        return String.format("|%s|%s|", playerName, renderStates());
    }

    private String renderStates() {
        return frameStateRenderers.stream()
                .map(FrameStateRenderer::renderState)
                .collect(Collectors.joining("|"));
    }
}
