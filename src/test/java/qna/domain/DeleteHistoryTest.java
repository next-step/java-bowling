package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DeleteHistoryTest {

    @DisplayName("DeleteHistory 인스턴스를 Answer 인스턴스 하나로 생성 가능 여부 테스트")
    @Test
    void 생성_Answer() {
        // when
        DeleteHistory deleteHistory = new DeleteHistory(AnswerTest.A1);

        // then
        assertThat(deleteHistory).isNotNull();
    }

    @DisplayName("DeleteHistory 인스턴스를 Question 인스턴스 하나로 생성 가능 여부 테스트")
    @Test
    void 생성_Question() {
        // when
        DeleteHistory deleteHistory = new DeleteHistory(QuestionTest.Q1);

        // then
        assertThat(deleteHistory).isNotNull();
    }
}