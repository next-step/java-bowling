package qna.service;

import qna.CannotDeleteException;
import qna.domain.Question;
import qna.domain.QuestionRepository;
import qna.domain.User;


public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question delete(Question question, User loginUser) throws CannotDeleteException {
        if (!question.isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }

        question.setDeleted(true);
        return questionRepository.save(question);
    }
}
