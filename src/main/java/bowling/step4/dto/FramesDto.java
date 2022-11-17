package bowling.step4.dto;


import bowling.step4.domain.Frame;
import bowling.step4.domain.Frames;

import java.util.HashMap;
import java.util.Map;

public class FramesDto {
    public final Map<Integer, FrameDto> frameDtoMap;

    public FramesDto(Map<Integer, FrameDto> frameDtoMap) {
        this.frameDtoMap = frameDtoMap;
    }

    public static FramesDto from(Frames frames) {
        Map<Integer, FrameDto> map = new HashMap<>();
        Map<Integer, Frame> frameMap = frames.frameMap();
        for (int i = 1; i<= frameMap.size(); i++) {
            map.put(i, FrameDto.from(frameMap.get(i)));
        }
        return new FramesDto(map);
    }
}
