package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class AnswersTest {

    @Test
    @DisplayName("질문자와 답변자가 다른 경우 답변을 삭제할 수 없다.")
    void delete_실패() {
        Answer answer1 = new Answer(UserTest.JAVAJIGI, new Question("제목", "내용"), "답변");
        Answer answer2 = new Answer(UserTest.JAVAJIGI, new Question("제목22", "내용22"), "답변22");
        Answers answers = new Answers(Arrays.asList(answer1, answer2));
        assertThatThrownBy(() -> answers.delete(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문자와 답변글의 모든 답변자가 같은 경우 삭제가 가능하다.")
    void delete_성공() throws CannotDeleteException {
        Answer answer1 = new Answer(UserTest.JAVAJIGI, new Question("제목", "내용"), "답변");
        Answer answer2 = new Answer(UserTest.JAVAJIGI, new Question("제목22", "내용22"), "답변22");
        Answers answers = new Answers(Arrays.asList(answer1, answer2));
        List<DeleteHistory> deleteHistories = answers.delete(UserTest.JAVAJIGI);
        assertThat(deleteHistories.size()).isEqualTo(2);
    }
}
