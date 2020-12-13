package bowling.view;

import bowling.model.User;
import bowling.model.frame.FrameNumber;
import bowling.model.frame.FrameResult;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static utils.StringUtils.center;

public class ResultView {

    private static final int FRAME_PADDING = 6;
    private static final String FRAME_DELIMITER = "|";
    private static final String NAME_COLUMN = "NAME";
    private ResultView(){}

    public static void printHeadFrame(){
        List<String> frameNumbers = IntStream.rangeClosed(FrameNumber.MIN_FRAME_NUMBER, FrameNumber.MAX_FRAME_NUMBER)
                .mapToObj(number -> FrameNumber.from(number).toString())
                .collect(Collectors.toList());

        printFrame(Optional.empty(), FrameResult.from(frameNumbers));
    }

    public static void printFrame(User user, FrameResult frameResult){
        printFrame(Optional.of(user.toString()), frameResult);
    }

    private static void printFrame(Optional<String> name, FrameResult frameResult){
        String frame = frameResult.stream()
                .map(viewElement -> center(viewElement, FRAME_PADDING))
                .collect(Collectors.joining(FRAME_DELIMITER));

        System.out.print(FRAME_DELIMITER + center(name.orElse(NAME_COLUMN),FRAME_PADDING));
        System.out.println(FRAME_DELIMITER + frame + FRAME_DELIMITER);
    }
}
