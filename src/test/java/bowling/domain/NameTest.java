package bowling.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {

    @ParameterizedTest
    @ValueSource(strings = {"abc", ""})
    void verifiesValidNames(String name){

    }
}
