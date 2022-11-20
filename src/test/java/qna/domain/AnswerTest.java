package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");


    @Test
    @DisplayName("삭제 실패 답변 중 다른 사람이 쓴 글 테스트")
    public void delete(){
        assertThatThrownBy(() -> {
            A1.isOwner(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("삭제 성공 질문자와 답변자가 같은 경우 테스트")
    public void deleteSuccessSameUser() throws Exception {
        A2.isOwner(UserTest.SANJIGI);
        assertThat(A2.isDeleted()).isTrue();
    }
}
