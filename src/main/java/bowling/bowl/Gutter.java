package bowling.bowl;

public class Gutter extends Ended{

    @Override
    public String toString(){
        return "[Gutter]";
    }

    @Override
    public String getSymbol() {
        return "-|-";
    }
}
