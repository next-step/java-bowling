package refactor;

public class BowlingApp {
    public static void main(String[] args) {
        String name = Input.scan();
        Frames frames = Frames.create();
        frames.play(0);
//        Frame frame = frames.get(0);
//        frame = frame.pitch();
//        frames.add(frame);
        Output.printFrame(frames);
    }
}
