package bowling.step2.domain;

public class No {
    private static final int COUNT_OF_MAX_FRAME = 10;
    
    private final int no;
    
    public No(final int no) {
        this.no = no;
    }
    
    public int increase() {
        return no + 1;
    }
    
    public boolean isNextFinalFrame() {
        return no + 1 == COUNT_OF_MAX_FRAME;
    }
}
