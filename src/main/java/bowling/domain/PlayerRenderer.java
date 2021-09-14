package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerRenderer implements Renderer {

    private static final String PLAYER_NAME_RENDERING_FORMAT = "  %s ";
    private static final String PLAYER_TOTAL_STATE_RENDERING_FORMAT = "|%s|%s|";
    private static final String FRAME_DELIMITER = "|";

    private final String playerName;
    private final List<Renderer> frameStateRenderers;

    public PlayerRenderer(PlayerName playerName, Frames frames) {
        this.playerName = String.format(PLAYER_NAME_RENDERING_FORMAT, playerName);
        this.frameStateRenderers = frames.toFrameStateRenderers();
    }

    @Override
    public String render() {
        return String.format(PLAYER_TOTAL_STATE_RENDERING_FORMAT, playerName, renderStates());
    }

    private String renderStates() {
        return frameStateRenderers.stream()
                .map(Renderer::render)
                .collect(Collectors.joining(FRAME_DELIMITER));
    }
}
