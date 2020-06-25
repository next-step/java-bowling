package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static qna.domain.AnswerTest.A1;

public class AnswersTest {

    @Test
    public void testAddAnswer() {
        Answers answers = new Answers();

        answers.add(new Answer());

        assertThat(answers.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("다른 유저의 답변이 존재 할 때 예외 발생")
    public void throwExceptionWhenThereAreOtherWriterAnswer() {
        Answers answers = new Answers();
        answers.add(A1);

        assertThatExceptionOfType(CannotDeleteException.class).isThrownBy(() -> {
            answers.validate(UserTest.SANJIGI);
        }).withMessageMatching("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}