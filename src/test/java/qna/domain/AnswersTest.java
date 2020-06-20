package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


public class AnswersTest {

    @Test
    @DisplayName("answers 생성 테스트")
    void create() {
        assertThatCode(() -> new Answers()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("전체 삭제 예외 테스트")
    void deleteAllException(){
        Answers answers = new Answers();
        answers.add(new Answer(User.GUEST_USER, QuestionTest.Q1, "answer 1234567"));

        assertThatIllegalArgumentException()
                .isThrownBy(() -> answers.deleteAll(AnswerTest.A1.getWriter()));
    }

    @Test
    @DisplayName("전체 삭제 검증 테스트")
    void deleteAll(){
        Answers answers = new Answers();
        answers.add(new Answer(User.GUEST_USER, QuestionTest.Q1, "answer 1234567"));
        answers.add(new Answer(User.GUEST_USER, QuestionTest.Q2, "answer 1234567"));

        List<DeleteHistory> deleteHistoryList = answers.deleteAll(User.GUEST_USER);

        assertThat(deleteHistoryList).isNotEmpty();
        assertThat(deleteHistoryList).hasSize(2);
    }
}
