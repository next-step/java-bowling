package bowling.bowl;

public class Strike extends Ended{


    @Override
    public String toString(){
        return "[Strike]";
    }

    @Override
    public boolean isStrike(){
        return true;
    }

    @Override
    public String getSymbol() {
        return "X";
    }

}
