package bowling;

import bowling.domain.*;
import bowling.dto.FrameStatusDto;
import bowling.view.FramesConsoleOutput;
import bowling.view.PitchConsoleInput;
import bowling.view.PlayerConsoleInput;

import java.util.List;
import java.util.stream.Collectors;

public class BowlingGame {

    public static void main(String[] args) {
        PlayerCount playerCount = new PlayerCount(PlayerConsoleInput.askPlayerCount());
        Players players = new Players(PlayerConsoleInput.askNames(playerCount.count()));
        Lane lane = new Lane(playerCount);
        FramesConsoleOutput.print(players.names(), toFrameStatusDtos(lane));
        do {
            lane.addPitch(new Pitch(PitchConsoleInput.askPitch(lane.currentPlayerName(players))));
            FramesConsoleOutput.print(players.names(), toFrameStatusDtos(lane));
        } while (!lane.isAllEnd());

    }

    private static List<FrameStatusDto> toFrameStatusDtos(final Lane lane) {
        return lane.allFrames()
                .stream()
                .map(BowlingGame::toFramesStatusDto)
                .collect(Collectors.toList());
    }

    private static FrameStatusDto toFramesStatusDto(final Frames frames) {
        return new FrameStatusDto(frames.allFramePitchValues(), frames.scores());
    }

}
