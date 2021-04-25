package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {

    @DisplayName("Answers 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {

        Answers answers = new Answers();

        assertThat(answers).isNotNull();
    }
}
