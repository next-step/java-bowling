package qna.global.utils;

import qna.domain.Answer;
import qna.domain.Question;
import qna.domain.User;
import qna.global.exception.CannotDeleteException;

import java.util.List;

public class QnaValidation {

    private static final String INVALID_QUESTION_REMOVE_PERMISSTION = "질문을 삭제할 권한이 없습니다.";
    private static final String INVALID_ANSWER_REMOVE = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";

    private QnaValidation() {
    }

    public static void validateQuestionOwner(User loginUser, Question question) throws CannotDeleteException {
        if (!question.isOwner(loginUser)) {
            throw new CannotDeleteException(INVALID_QUESTION_REMOVE_PERMISSTION);
        }
    }

    public static void validateAnswer(User loginUser, List<Answer> answers) throws CannotDeleteException {
        if (answers.size() != 0) {
            answers.stream()
                    .filter(answer -> answers.size() != 0)
                    .filter(answer -> answer.isOwner(loginUser))
                    .findFirst()
                    .orElseThrow(() -> new CannotDeleteException(INVALID_ANSWER_REMOVE));
        }
    }

}
