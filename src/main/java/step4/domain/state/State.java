package step4.domain.state;

public interface State {

    State throwBowl(int falledPins);


    String getScore();
}
