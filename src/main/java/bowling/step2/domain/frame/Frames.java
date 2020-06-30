package bowling.step2.domain.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {

    List<Frame> frames;

    public Frames() {
        this.frames = new ArrayList<>();
        frames.add(new NormalFrame(1));
    }

    public Frames(List<Frame> frames){
        this.frames = frames;
    }

    public void progress(int pitch) {
        //TODO 프레임들 다 채우기 (여기서 호출 -> 다음 프레임 진행(투구-> 점수 -> 프레임 저장)해서 여기다 더해라 라는 것)
        // 바깥에서 while문 돌고 있으므로 frame 하나씩만 추가
        Frame frame = frames.get(frameIndex()).pitch(pitch);

        if(frame.pitchesOver()){
            nextFrame(frame);
        }
    }

    private void nextFrame(Frame frame) {
        frames.set(frameIndex(), frame);
        frames.add(frame.nextFrame());
    }

    private int frameIndex() {
        return currentFrameNo() - 1;
    }

    public int currentFrameNo(){
        return frames.size();
    }

    public boolean isEnd() {
        return frames.stream()
                     .anyMatch(frame -> frame == null);
    }

    public int getFramesSize(){
        return frames.size();
    }

    @Override
    public String toString() {
        return frames.stream()
                     .filter(frame -> frame != null)
                     .map(frame -> String.format("%-4s",frame.toString()))
                     .collect(Collectors.joining("|  "));
    }
}
