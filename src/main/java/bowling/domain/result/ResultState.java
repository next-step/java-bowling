package bowling.domain.result;

public interface ResultState {

    String getPrintMark();

    boolean isInstanceOf(Class<? extends ResultState> clazz);

}
