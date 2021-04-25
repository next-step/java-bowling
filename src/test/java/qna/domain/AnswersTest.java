package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {

    @DisplayName("Answers 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {

        Answers answers = new Answers();

        assertThat(answers).isNotNull();
    }

    @DisplayName("Answers 인스턴스 리스트로 생성 여부 테스트")
    @Test
    void 생성_리스트() {
        // given
        List<Answer> answerList = new ArrayList<>();

        Answers answers = new Answers(answerList);

        assertThat(answers).isNotNull();
    }
}
