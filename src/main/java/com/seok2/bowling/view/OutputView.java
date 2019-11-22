package com.seok2.bowling.view;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import com.seok2.bowling.frame.domain.Board;
import com.seok2.bowling.frame.domain.Index;
import com.seok2.bowling.frame.dto.BoardDTO;
import com.seok2.bowling.frame.dto.FrameDTO;
import com.seok2.bowling.frame.dto.ScoreDTO;
import com.seok2.bowling.user.dto.UserDTO;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OutputView {

    private static final String CURRENT_FRAME_TXT = "{0} 프레임 투구 : ";
    private static final String HEADER = "| NAME  |   01   |   02   |   03   |   04   |   05   |   06  |   07   |   08   |   09   |  10   |";
    private static final String SCORE = "|{0}  |{1}   |{2}   |{3}   |{4}   |{5}   |{6}  |{7}   |{8}   |{9}   | {10} |";
    private static final String TOTAL = "|       |{0}   |{1}   |{2}   |{3}   |{4}   |{5}  |{6}   |{7}   |{8}   | {9} |";
    private static final String EMPTY_STRING = "";
    private static final String STRING_FORMAT = "%5s";

    private OutputView() {
    }

    public static void print(BoardDTO boardDTO) {
        System.out.println(HEADER);
        System.out.println(MessageFormat.format(SCORE, toScoreArray(boardDTO)));
        System.out.println(MessageFormat.format(TOTAL, toTotalScoreArray(boardDTO)));
    }

    private static String[] toScoreArray(BoardDTO boardDTO) {
        List<String> result = new ArrayList<>(Arrays.asList(String.format(STRING_FORMAT, boardDTO.getUser().getName())));
        boardDTO.getBoardDTO().stream()
            .map(FrameDTO::getRecord)
            .map(view -> String.format(STRING_FORMAT, view))
            .collect(collectingAndThen(toList(), result::addAll));
        rightPadding(result);
        return result.stream().toArray(String[]::new);
    }

    private static String[] toTotalScoreArray(BoardDTO boardDTO) {

        List<Integer> result = boardDTO.getBoardDTO().stream()
            .map(FrameDTO::getScoreDTO)
            .filter(ScoreDTO::isNotPending)
            .map(ScoreDTO::getScore)
            .collect(toList());

        return accumulate(result).stream().toArray(String[]::new);
    }

    private static List<String> accumulate(List<Integer> list) {
        List<String> result = new ArrayList<>();
        int current = 0;
        for (Integer score : list) {
            current += score;
            result.add(String.format(STRING_FORMAT, current));
        }
        rightPadding(result);
        return result;
    }

    private static void rightPadding(List<String> result) {
        for (int size = result.size(); size <= Index.MAX; size++) {
            result.add(String.format(STRING_FORMAT, EMPTY_STRING));
        }
    }

    public static void printCurrentFrame(int size) {
        System.out.println();
        System.out.print(MessageFormat.format(CURRENT_FRAME_TXT, size));
    }
}
