package bowling.view;

import bowling.domain.state.State;
import bowling.service.dto.BoardDto;
import bowling.service.dto.FrameResultDto;
import bowling.view.enums.StateView;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public static final String EMPTY_FRAME_RESULT_STRING = "";
    public static final String EMPTY_SCORE_RESULT_STRING = "";
    private static final String BOARD_ROUND_FORMAT = "| NAME |  %02d  |  %02d  |  %02d  |  %02d  |  %02d  |  %02d  |  %02d  |  %02d  |  %02d  |  %02d  |";
    private static final String SCORE_FORMAT = "|      |  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|";
    private static final String RESULT_FORMAT = "  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|";
    private static final String NAME_FORMAT = "|  %-3s |";
    private static final int ROUND_MAX_SIZE = 10;
    private static final int NO_SCORE_VALUE = -1;

    public void renderBoard(BoardDto boardDto) {
        System.out.println(String.format(BOARD_ROUND_FORMAT, allRoundToIntArray(boardDto)));
        System.out.println(String.format(NAME_FORMAT, userName(boardDto)) + String.format(RESULT_FORMAT, gameResultToStringArray(boardDto)));
        System.out.println(String.format(SCORE_FORMAT, scoresToStringArray(boardDto)));
    }

    private String[] scoresToStringArray(BoardDto boardDto) {
        int totalScore = 0;
        String[] results = new String[ROUND_MAX_SIZE];
        List<Integer> scoreResults = createScoreResults(boardDto);
        for (int i = 0; i < scoreResults.size(); i++) {
            totalScore += scoreResults.get(i);
            results[i] = String.valueOf(totalScore);
        }
        for (int i = scoreResults.size(); i < ROUND_MAX_SIZE; i++) {
            results[i] = EMPTY_SCORE_RESULT_STRING;
        }
        return results;
    }

    private List<Integer> createScoreResults(BoardDto boardDto) {
        List<FrameResultDto> frameResultDtos = boardDto.getGameResultDto().getFrameResultsDto().getFrameResultDtos();
        List<Integer> scoreResults = frameResultDtos.stream()
                .map(FrameResultDto::getScore)
                .filter(score -> score != NO_SCORE_VALUE)
                .collect(Collectors.toList());
        return scoreResults;
    }

    private String[] gameResultToStringArray(BoardDto boardDto) {
        String[] results = new String[ROUND_MAX_SIZE];
        List<String> frameViewResults = createFrameResults(boardDto);
        for (int i = 0; i < ROUND_MAX_SIZE; i++) {
            results[i] = findFrameViewResultByIndex(frameViewResults, i);
        }
        return results;
    }

    private List<String> createFrameResults(BoardDto boardDto) {
        List<FrameResultDto> frameResultDtos = boardDto.getGameResultDto().getFrameResultsDto().getFrameResultDtos();
        List<String> frameViewResults = frameResultDtos
                .stream()
                .map(FrameResultDto::getStates)
                .map(this::statesToViewFormat)
                .collect(Collectors.toList());
        return frameViewResults;
    }

    private String findFrameViewResultByIndex(List<String> frameViewResults, int index) {
        if (0 <= index && index < frameViewResults.size()) {
            return frameViewResults.get(index);
        }

        return EMPTY_FRAME_RESULT_STRING;
    }

    private String statesToViewFormat(List<State> states) {
        return states.stream()
                .map(state -> {
                    StateView stateView = StateView.valueOf(state);
                    return stateView.convert(state);
                }).collect(Collectors.joining("|"));

    }

    private String userName(BoardDto boardDto) {
        return boardDto.getGameResultDto().getName();
    }

    private Integer[] allRoundToIntArray(BoardDto boardDto) {
        return boardDto.getAllRounds().toArray(new Integer[ROUND_MAX_SIZE]);
    }
}
