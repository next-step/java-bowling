package bowling;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Bowling {
    List<Frame> frames = new LinkedList<>();

    private int currentFrameIndex = 0;
    private Frame frame;

    private Bowling(){
        frame = NormalFrame.of(0);
        // finalFrame = FinalFrame.of();
        //
        // frames.add(NormalFrame.of(0));
        // frames.
        // for (int i = 1; i < NUM_OF_FRAMES - 1 ; i ++) {
        //    // NormalFrame next =
        //   //  frames.add(NormalFrame.of(i));
        // }
        // frames.add(finalFrame);
    }

    public static Bowling start(){
        return new Bowling();
    }

    public void bowl(int num){
        //Frame frame = frames.get(currentFrameIndex);

        frame = frame.play(num);
        //
        // if (frame.isTerminate() && !isLastFrame()) { //현재 frame이 끝났으면 index 증가 시키고 다음 frame을 리턴
        //     currentFrameIndex++;
        //     return frame.getNext();
        // }
        //
        // return frame;
    }
    //
    // public boolean isLastFrame(){
    //     return currentFrameIndex == NUM_OF_FRAMES - 1;
    // }

    public boolean isTerminated(){
         return frame.isLastFrame() &&  frame.isTerminate();
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
