package bowling;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bowling {
    private final int NUM_OF_FRAMES = 10;
    List<Frame> frames = new ArrayList<>();

    private final Frame finalFrame;
    private int currentFrameIndex = 0;

    private Bowling(){
        finalFrame = FinalFrame.of();
        for(int i = 0; i < NUM_OF_FRAMES - 1 ; i ++){
            frames.add(NormalFrame.of());
        }
        frames.add(finalFrame);
    }

    public static Bowling start(){
        return new Bowling();
    }

    public Frame bowl(int num){
        Frame frame = frames.get(currentFrameIndex);

        frame.play(num);

        if(frame.isTerminate() && !isLastFrame()){ //현재 frame이 끝났으면 index 증가 시키고 다음 frame을 리턴
            currentFrameIndex++;
            return frames.get(currentFrameIndex);
        }

        return frame;
    }

    public boolean isLastFrame(){
        return currentFrameIndex == NUM_OF_FRAMES-1;
    }

    public boolean isTerminated(){
        return finalFrame.isTerminate();
    }

    public int getCurrentFrameIndex(){
        return currentFrameIndex;
    }

    public String print(){
        StringBuilder str = new StringBuilder();
        str.append(frames.stream()
                .map(Frame::getScores)
                .collect(Collectors.joining("|")));

        str.append("|");

        return str.toString();
    }
}
