package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {

    private Answers answers;

    @BeforeEach
    void before(){
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        answers = new Answers();
        answers.add(new Answer(0L, UserTest.JAVAJIGI, question, "Answers Contents1"));
        answers.add(new Answer(1L, UserTest.JAVAJIGI, question, "Answers Contents2"));
    }

    @Test
    @DisplayName("답변자와 질문자가 다를 경우 삭제할수 없다.")
    void 삭제_실패(){
        assertThatThrownBy(()->{
            answers.delete(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("답변자와 질문자가 다를 경우 삭제할수 있다.")
    void 삭제_성공_deleteHistory_반환(){
        assertThat(answers.delete(UserTest.JAVAJIGI)).containsExactly(
                new DeleteHistory(ContentType.ANSWER, 0L, UserTest.JAVAJIGI),
                new DeleteHistory(ContentType.ANSWER, 1L, UserTest.JAVAJIGI)
                );
    }

    @Test
    @DisplayName("다른 질문에 대한 답변 추가할 시 예외를 던진다.")
    void 다른질문답변_추가시_예외(){
        Question newQuestion = new Question("new", "new").writeBy(UserTest.JAVAJIGI);
        assertThatThrownBy(()->{
            answers.add(new Answer(0L, UserTest.JAVAJIGI, newQuestion, "Answers Contents1"));
        }).isInstanceOf(IllegalArgumentException.class);
    }
}