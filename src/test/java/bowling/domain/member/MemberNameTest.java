package bowling.domain.member;

import bowling.domain.member.exception.InvalidMemberNameLengthException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("참여자명 테스트")
public class MemberNameTest {
    @DisplayName("참여자명 생성")
    @Test
    public void createMemberName() {
        MemberName name = MemberName.of("ABC");

        assertThat(name.getName()).isEqualTo("ABC");
    }

    @DisplayName("글자수 맞지 않게 이름 생성")
    @ParameterizedTest
    @CsvSource(value = {"A", "AB", "ABCD"})
    public void invalidName(String name) {
        assertThatThrownBy(() -> {
            MemberName.of(name);
        }).isInstanceOf(InvalidMemberNameLengthException.class);
    }
}