package bowling.bowl;

public class Gutter extends Ended{

    @Override
    public String toString(){
        return "[Gutter]";
    }


    @Override
    public boolean isGutter(){
        return true;
    }

    @Override
    public String getSymbol() {
        return "-|-";
    }
}
