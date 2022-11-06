package step1.qna.domain;

import java.util.Arrays;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step1.qna.CannotDeleteException;

class AnswersTest {

    private Question Q1;

    private Answer A1;
    private Answer A2;

    @BeforeEach
    void setUp() {
        Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        A1 = new Answer(UserTest.JAVAJIGI, Q1, "Answers Contents1");
        A2 = new Answer(UserTest.SANJIGI, Q1, "Answers Contents2");
    }

    @DisplayName("작성자와 모두 같은 답변자일 경우 삭제 가능")
    @Test
    void answersDelete() {
        Answers answers = Answers.from(Arrays.asList(A1, A1, A1));
        answers.deleteAll(UserTest.JAVAJIGI);
        int countOfDeletedAnswer = (int) answers.getAnswers().stream()
            .filter(answer -> answer.isDeleted())
            .count();
        Assertions.assertThat(countOfDeletedAnswer).isEqualTo(3);
    }

    @DisplayName("작성자와 다른 답변자가 한 명이라도 있을 경우 예외 처리")
    @Test
    void answers_delete() {
        Answers answers = Answers.from(Arrays.asList(A1, A2, A2));
        Assertions.assertThatThrownBy(() -> answers.deleteAll(UserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }

}
