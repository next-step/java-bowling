package bowling.view;

import bowling.domain.state.State;

import java.util.LinkedList;

class FrameBoardView {

    private static final int MIN_FRAME_SIZE = 0;
    private static final int MAX_FRAME_SIZE = 10;

    static void getFrameBoard(LinkedList<State> states, String name) {
        System.out.print(String.format("|  %s |", name));
        for (State state : states) {
            getFrame(state);
        }

        FrameBoardView.getFinalFrameBoard(states);
        getFirstBowlFrame(states);
        BowlingFrameView.getCollectFrame(getFrameSize(states));
    }

    private static void getFrame(State state) {
        getFrameByFinish(state);
    }

    private static void getFrameByFinish(State state) {
        if (state.isFinish()) {
            BowlingFrameView.getFinishFrame(state.display());
        }
    }

    private static void getFinalFrameBoard(LinkedList<State> states) {
        if (states.size() > MIN_FRAME_SIZE) {
            getFinalFrameBoardByLastState(states.getLast());
        }
    }

    private static void getFinalFrameBoardByLastState(State state) {
        getFinalFrameCheck(state);
    }

    private static void getFinalFrameCheck(State state) {
        if (state.isFinish()) {
        }
    }

    private static void getFirstBowlFrame(LinkedList<State> states) {
        if (states.size() > MIN_FRAME_SIZE) {
            getFirstBowlFrameBoard(states.getLast());
        }
    }

    private static void getFirstBowlFrameBoard(State state) {
        if (!state.isFinish()) {
            BowlingFrameView.getReadyFrame(state.display());
        }
    }

    private static int getFrameSize(LinkedList<State> states) {
        int size = MIN_FRAME_SIZE;
        if (states.size() > MIN_FRAME_SIZE) {
            State state = states.getLast();
        }
        return size;
    }
}
