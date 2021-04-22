# 볼링 게임 점수판

## 구현해야할 기능 목록
- [X] 플레이어 이름 입력
    - [X] __ERROR__ : 공백 또는 null 값일 경우
    
- [X] 플레이어 생성
    - [X] 이름 생성
        - [X] __ERROR__ : 영어가 아닐 경우
        - [X] __ERROR__ : 3글자가 아닐 경우

- [X] 일반(1 ~ 9) 프레임 생성
    - [X] 공 던지기 기능
    - [X] 다음 프레임 생성 기능
    - [X] 프레임 완료 확인 여부 기능

    - [X] 총 던질 수 있는 공의 객체 생성
        - [X] __ERROR__ : 던져서 쓰러뜨린 총 개수가 10을 초과할 경우
        - [X] 각 공 던지기 기능
        - [X] 스페어 확인 여부 기능
        - [X] 스트라이크 확인 여부 기능
        - [X] 각 공 던진 여부 확인 기능
        - [X] 각 공이 쓰러뜨린 핀의 개수 및 총 쓰러뜨린 핀의 개수 반환 기능
        
        - [X] 각 공의 객체 생성
            - [X] 공이 던져졌는지 확인 여부 기능
            - [X] 공이 쓰러뜨린 핀의 개수 반환 기능
            - [X] 스트라이크 확인 여부 기능
            - [X] 쓰러뜨린 핀의 개수 더하기 기능
            
            - [X] 투구 당 쓰러뜨린 핀의 개수 생성
                - [X] __ERROR__ : 0 ~ 10 외의 값일 경우  
                - [X] 스트라이크 확인 여부 기능
                - [X] 쓰러뜨린 핀의 개수 더하기 기능
                
    - [] 보너스 점수 생성

- [] 최종(10) 프레임 생성

    - [X] 총 던질 수 있는 공의 객체 생성
        - [X] 1구 스트라이크 확인 여부 기능
            - [X] 스트라이크일 경우 보너스 3구 기회 부여
        - [X] 2구 스페어 확인 여부 기능
            - [X] 스페어일 경우 보너스 3구 기회 부여
        - [X] 각 공 던진 여부 확인 기능
        - [X] 각 공이 쓰러뜨린 핀의 개수 및 총 쓰러뜨린 핀의 개수 반환 기능
                
        - [X] 각 공의 객체 생성
            - [X] 공이 던져졌는지 확인 여부 기능
            - [X] 공이 쓰러뜨린 핀의 개수 반환 기능
            - [X] 스트라이크 확인 여부 기능
            - [X] 쓰러뜨린 핀의 개수 더하기 기능
            
            - [X] 투구 당 쓰러뜨린 핀의 개수 생성
                - [X] __ERROR__ : 0 ~ 10 외의 값일 경우  
                - [X] 스트라이크 확인 여부 기능
                - [X] 쓰러뜨린 핀의 개수 더하기 기능
                
## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)