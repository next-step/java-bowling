package qna.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {
    private Answers answers;

    @BeforeEach
    void initAnswers() {
        answers = new Answers();
    }

    @Test
    @DisplayName("일급객체 생성")
    void createTest(){
        assertThat(answers.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("일급객체에 객체 추가하기.(add)")
    void addAnswerTest() {
        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A2);

        assertThat(answers.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Answer 객체들 일괄 삭제하기. (작성한 사용자만 삭제가능)")
    void deleteAnswerTest1() throws CannotDeleteException {
        answers.add(AnswerTest.A1);

        assertThat(answers.delete(UserTest.JAVAJIGI)).isTrue();
    }

    @Test
    @DisplayName("Answer 객체들 일괄 삭제하기. (작성자가 아닌 사람이 삭제시 예외 발생)")
    void deleteAnswerTest2() throws CannotDeleteException {
        answers.add(AnswerTest.A1);
        assertThatThrownBy(()->answers.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}