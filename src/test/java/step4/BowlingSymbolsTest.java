package step4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import step4.domain.BowlingSymbols;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingSymbolsTest {

    @DisplayName("볼링 심볼 일급콜렉션 push 테스트")
    @ParameterizedTest
    @CsvSource(value = {"10,0,X", "0,10,-|/", "5,5,5|/", "3,4,3|4", "1,0,1|-", "0,1,-|1"})
    void pushForNormal(int first, int second, String result) {
        BowlingSymbols symbols = BowlingSymbols.of(2);
        symbols.push(first);
        symbols.push(second);
        System.out.println(symbols.getSymbol());

        assertThat(symbols.getSymbol()).isEqualTo(result);
    }

    @DisplayName("생성 테스트")
    @Test
    void createOf() {
        BowlingSymbols of = BowlingSymbols.of(2);
        assertThat(of.size()).isEqualTo(0);
    }

}
