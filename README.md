# 볼링 게임 step1 TODO
- [ ] 질문 데이터 삭제
    - [ ] 포스팅 삭제 가능 여부
        - [X] 로그인 사용자=질문자
            - [X] 답변 없을 때 삭제 가능
            - [X] 답변 있을 때
                - [X] 질문자!=답변자: 삭제 불가.
                    - [X] 삭제 요청시 exception
                - [X] 질문자 = 답변자: 삭제 가능
            - [X] 로그인 사용자!=질문자: exception

        - [ ] 질문자 = 답변글의 모든 답변자
        - [ ] 데이터의 상태값을 변경
    - [ ] 답변 삭제 가능 여부
        - [ ] 질문자 != 답변자 -> 삭제 불가
        - [ ] 데이터의 상태값을 변경

- [ ] Delete History 남기기
    - [ ] 질문
    - [ ] 답변