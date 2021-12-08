package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.UserTest.JAVAJIGI;

class AnswersTest {

    @DisplayName("다른 질문에 대한 답변을 추가하면 예외를 던진다.")
    @Test
    void add_notCompatible_throwsException() {
        // when
        Answer answer = new Answer(JAVAJIGI, question(1), "contents");
        Answers answers = new Answers();
        answers.add(answer);

        //when
        Answer notCompatibleAnswer = new Answer(JAVAJIGI, question(2), "contents");

        //then
        assertThatThrownBy(() -> answers.add(notCompatibleAnswer))
                .isInstanceOf(NotCompatibleAnswerException.class);

    }

    @DisplayName("삭제하면 답변 개수만큼 히스토리를 반환한다.")
    @Test
    void delete_returnDeleteHistories() {
        //given
        Answer answer1 = new Answer(JAVAJIGI, question(1), "contents1");
        Answer answer2 = new Answer(JAVAJIGI, question(1), "contents2");

        Answers answers = new Answers();
        answers.add(answer1);
        answers.add(answer2);

        //when
        List<DeleteHistory> deleteHistories = answers.delete(JAVAJIGI);

        //then
        assertThat(deleteHistories).hasSize(2);
    }

    private Question question(long id) {
        return new Question(id, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
    }

}
