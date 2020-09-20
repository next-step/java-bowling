package view;

import dto.NameDTO;
import dto.ResultFrameDTO;
import dto.ResultFramesDTO;

import java.util.stream.IntStream;

public class ResultView {

    private static final int START_FRAME = 1;
    private static final int MAX_PINS = 10;
    private static final int MIN_PINS = 0;
    private static final String GUTTER = "-";
    private static final String STRIKE = "X";
    private static final String SPARE = "/";

    public void printColumns(int totalFrames) {
        StringBuilder columns = new StringBuilder("| NAME |");
        IntStream.rangeClosed(START_FRAME, totalFrames - 1)
                .forEach(frame -> columns.append(String.format("  %02d |", frame)));
        columns.append(String.format("   %02d  |", totalFrames));
        System.out.println(columns);
    }

    public void printPlayerName(NameDTO nameDTO) {
        System.out.printf("|  %s |", nameDTO.getName());
    }

    public void printPins(ResultFramesDTO resultFramesDTO) {
        StringBuilder frames = new StringBuilder();
        resultFramesDTO.getResultFrameDTOs()
                .forEach(resultFrameDTO -> {
                    printFirstPin(frames, resultFrameDTO);
                    printSecondPin(frames, resultFrameDTO);
                    printBonusPin(frames, resultFrameDTO);
                });
        System.out.println(frames);
    }

    private void printFirstPin(StringBuilder frames, ResultFrameDTO resultFrameDTO) {
        if (!resultFrameDTO.isFirstDone()) {
            frames.append("  ");
            return;
        }
        if (resultFrameDTO.getFirstPin() == MAX_PINS) {
            frames.append(String.format(" %s", STRIKE));
            return;
        }
        String pin = String.valueOf(resultFrameDTO.getFirstPin() == MIN_PINS ? GUTTER : resultFrameDTO.getFirstPin());
        frames.append(String.format(" %s", pin));
    }

    private void printSecondPin(StringBuilder frames, ResultFrameDTO resultFrameDTO) {
        if (!resultFrameDTO.isSecondDone()
                || resultFrameDTO.getFirstPin() == MAX_PINS && !resultFrameDTO.isLastResultFrameDTO()) {
            frames.append("  ");
            return;
        }
        if (resultFrameDTO.getFirstPin() + resultFrameDTO.getSecondPin() == MAX_PINS) {
            frames.append(String.format("|%s", SPARE));
            return;
        }
        String pin = String.valueOf(resultFrameDTO.getSecondPin() == MIN_PINS ? GUTTER : resultFrameDTO.getSecondPin());
        pin = String.valueOf(resultFrameDTO.getSecondPin() == MAX_PINS ? STRIKE : pin);
        frames.append(String.format("|%s", pin));
    }

    private void printBonusPin(StringBuilder frames, ResultFrameDTO resultFrameDTO) {
        if (!resultFrameDTO.isLastResultFrameDTO()) {
            frames.append(" |");
            return;
        }
        if (resultFrameDTO.isBonusDone()) {
            String pin = String.valueOf(resultFrameDTO.getBonusPin() == MIN_PINS ? GUTTER : resultFrameDTO.getBonusPin());
            pin = String.valueOf(resultFrameDTO.getBonusPin() == MAX_PINS ? STRIKE : pin);
            frames.append(String.format("|%s |", pin));
            return;
        }
        frames.append("   |");
    }

    public void printExceptionMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }
}
