package io;

import domain.frame.Frame;
import domain.frame.Frames;
import domain.state.State;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OutputResult {

    public final static String SYMBOL_DELIMITER = "|";
    public final static String EMPTY_SPACE = "   ";
    private final static String TITLES = "NAME|  01  | 02  | 03  | 04  | 05  | 06  | 07  | 08  | 09  | 10  ";
    private final static String BOARD_DELIMITER = " | ";

    public static void printBoard(String name, Frames frames) {
//        System.out.println(TITLES);
//        List<String> result = new ArrayList<>();
//        result.add(name);
//
//        List<String> symbols = frames.stream()
//                .map(Frame::getState)
//                .map(State::toSymbol)
//                .collect(Collectors.toList());
//        result.addAll(symbols);
//        System.out.println(String.join(BOARD_DELIMITER, result));
    }
}
