# 볼링 게임 점수판

## 2단계

### 목표

점수 계산을 제외한 볼링 게임 점수판을 구현하는 것

### 기능 목록

- 입력
    - `이름`
        - 3자리의 영문자만 입력할 수 있다.
    - `투구`
        - 첫번째 투구는 0 ~ 10 사이의 정수만 입력할 수 있다.
        - 두번째 투구는 프레임의 투구 수가 x 일 때, 0 ~ (10 - x) 사이의 정수만 입력할 수 있다.
        - 프레임이 진행 중인 상태라면 투구를 입력해야 한다.
    - 10프레임이 종료되면, 입력을 종료해야 한다.
- 출력
    - `볼림 점수판`
    - 투구 결과에 따른 출력 형식
        - 스트라이크 : `X`
        - 스페어 : `9 | /`
        - 미스 : `8 | 1`
        - 거터 : `-`
    - `투구`를 입력할 때마다 볼링 점수판을 출력해야 한다.
- 도메인
    - 볼링 게임
        - 볼링 게임은 10개의 프레임으로 구성되어 있다.
    - 프레임
        - 프레임은 최대 10개의 볼링핀을 가질 수 있다.
        - 프레임은 투구 기록을 가지고 있어야 한다.
        - 프레임은 무조건 1번의 투구를 할 수 있다.
        - 새로운 프레임은 10개의 핀이 있는 상태로 시작해야 한다.
        - 각 프레임은 진행중 또는 종료 상태를 가진다.
            - 프레임이 진행중인 상태라면 투구를 입력받을 수 있다.
            - 프레임이 종료된 상태라면 해당 프레임에서는 더 이상 투구를 입력받을 수 없다.
            - 프레임이 종료되면, 다음 프레임으로 넘어가야 한다.
        - 10 프레임을 제외하고, 각 프레임은 최대 2번의 투구를 할 수 있다.
            - 첫번째 투구에서 10개의 핀을 모두 쓰러트다면, 해당 프레임을 종료해야 한다.
            - 첫번째 투구에서 10개의 핀을 모두 쓰러트리지 못했다면, 남은 핀이 있는 상태로 시작해야 한다.
            - 두번째 투구를 진행하면, 해당 프레임을 종료해야 한다.
        - 10 프레임은 최대 3번의 투구를 할 수 있다.
            - 첫번째 투구에서 10개의 핀을 모두 쓰러트렸다면, 두번째 투구는 10개의 핀이 있는 상태로 시작해야 한다.
                - 두번째 투구에서 10개의 핀을 모두 쓰러뜨렸다면, 세번째 투구는 10개의 핀이 있는 상태로 시작해야 한다.
                - 두번째 투구에서 10개의 핀을 모두 쓰러트리지 못했다면, 세번째 투구는 남은 핀이 있는 상태로 시작해야 한다.
            - 첫번째 투구에서 10개의 핀을 모두 쓰러트리지 못했다면, 두번째 투구는 남은 핀이 있는 상태로 시작해야 한다.
                - 두번째 투구에서 남아 있는 모든 핀을 쓰러트렸다면, 세번째 투구는 10개의 핀이 있는 상태로 시작해야 한다.
                - 두번째 투구에서 남아 있는 모든 핀을 쓰러트리지못했다면, 해당 프레임을 종료해야 한다.
    - 볼링핀
    - 프레임 결과
        - 프레임이 종료되면, 프레임 결과를 가질 수 있다.
            - 스트라이크 : 프레임의 첫번째 투구에서, 10개의 핀을 쓰러뜨린 상태
            - 스페어 : 프레임의 두번째 투구에서 10개의 핀을 쓰러뜨린 상태
            - 미스 : 프레임의 두번째 투구에서 10개의 핀을 모두 쓰러트리지 못한 상태
        - 프레임이 진행중이면, 프레임 결과를 가져올 수 없어야 한다.

## 3단계

### 목표

점수 계산을 포함하여 볼링 게임 점수판을 구현하는 것

### 기능 목록

- 점수 계산 방법
  - 10 프레임을 제외한 프레임
    - 스트라이크 : 다음 2번의 투구까지 점수를 합산해야 한다.
    - 스페어 : 다음 1번의 투구까지 점수를 합산해야 한다.
    - 미스 : 해당 프레임에서 쓰러트린 핀의 개수가 득점이 된다.
  - 10 프레임
    - 해당 프레임에서 쓰러트린 핀의 개수 (보너스 점수 없음.)
