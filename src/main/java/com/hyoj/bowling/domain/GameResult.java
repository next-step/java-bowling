package com.hyoj.bowling.domain;

import com.hyoj.bowling.console.OutputConsole;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class GameResult {
    private final String playerName;
    private final List<Frame> frames;

    public GameResult(String playerName, List<Frame> frames) {
        this.playerName = playerName;
        this.frames = frames;
    }

    @Override
    public String toString() {
        return String.format("| %s |", StringUtils.center(playerName, OutputConsole.TEXT_SIZE))
                + frames;
    }
}
