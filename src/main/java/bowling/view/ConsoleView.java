package bowling.view;

import bowling.domain.FrameData;
import bowling.domain.PinMark;
import bowling.domain.ScoreSheetReader;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConsoleView {
    private Map<Integer, String> pinMarkSign = new HashMap<>();
    {
        pinMarkSign.put(10, "X");
        pinMarkSign.put(0, "-");
    }

    private String toSign(PinMark pinMark){
        return pinMarkSign.getOrDefault(pinMark.getCountOfFallDownPins(), String.valueOf(pinMark.getCountOfFallDownPins()));
    }

    private PrintWriter writer = new PrintWriter(System.out);

    public void printScoreSheets(List<ScoreSheetReader> readers) {

        List<String> playerNames = readers.stream()
                .map(ScoreSheetReader::readPlayName)
                .collect(Collectors.toList());
        int maxNameLength = playerNames.stream()
                .map(String::length)
                .reduce(Math::max)
                .orElse(8);
        int nameSpan = Math.max(maxNameLength + 2, 8);

        writer.print(String.format("| %1$" + nameSpan + "s | ", "NAME"));
        String frameNos = IntStream.range(0, 10)
                .mapToObj(idx -> {
                    if (idx == 9) {
                        return String.format("%1$4s ", (idx + 1));
                    }
                    return String.format("%1$2s ", (idx + 1));
                })
                .collect(Collectors.joining(" | "));
        writer.print(frameNos);
        writer.println(" |");

        readers.forEach(reader -> {
            writer.print(String.format("|%1$" + nameSpan + "s  | ", reader.readPlayName()));
            while (!reader.isEOF()) {
                FrameData frameData = reader.readFrameData();
                String marked = frameData.getPinMarks()
                        .stream()
                        .map(pinMark -> toSign(pinMark))
                        .collect(Collectors.joining("|"));
                int spanSize = 3;
                if (frameData.getFrameNo() == 10) {
                    spanSize = spanSize + 2;
                }
                writer.print(String.format("%1$" + spanSize + "s", marked));
                writer.print(" | ");
            }
            writer.println();
        });
        writer.flush();
    }
}