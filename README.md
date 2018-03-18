# sichuan-sheng-mobile 

Data

void addCombo(int tic) :
콤보 추가를 위한 메소드이다. tic은 시간을 나타내며 해당 시간내에 추가로 콤보를 달성해야만 콤보가 이어진다.

int[][] loadStage(int stage) :
스테이지 정보를 수동으로 넣어주기위한 메소드이다.
1탄부터 5탄까지는 단원들이 세팅돼있으며 6탄은 모의고사 모드가 세팅돼있음.

boolean isCorrect(int x, int y, int x2, int y2) :
x,y에서 x2,y2가 제거가능한 올바른 경로인지 반환. dfs를 활용.

int getCombo() :
현재 콤보횟수가 몇인지 반환.

Path
좌표값을 구조체처럼 정의했다.

int getX() :
x좌표를 반환

int getY() :
y좌표를 반환

void setX(int x) :
x좌표를 설정

void setY(int y) :
y좌표를 설정

int getDirection() :
방향을 반환

void setDirection(int direction) :
방향을 설정

Map
맵에 관련한 데이터들이 저장돼있고 해당 데이터를 관리해주는 클래스

Map(int time, int kind, int mode, int bomb, int item, int item2, int item3) :
생성자. 순서대로 시간/카드종류의 개수/게임모드/폭탄여부/아이템1,2,3을 세팅

addScore(int score) :
점수를 추가해주는 메소드

void editData(int[][] data) :
맵을 수동으로 세팅해주는 메소드

void newData() :
랜덤하게 맵을 생성해주는 메소드 (모의고사 모드)

void mixData() :
맵을 섞어주는 메소드 (벽, 열쇠구멍, 승리카드는 섞이지 않음)

void dfs(int x, int y, int direction, int corner_count) :
백트래킹을 적용한 메소드. 상/하/좌/우 4방향중 (x,y) 기준으로 막히지 않은 곳을 모두 탐색해준다. direction은 방향을 나타내고 corner_count는 현재 탐색중인 경로가 몇 번 꺾였는지 나타낸다.

void addDirection() :
이동 경로를 path에 추가.

int totalCard() :
남은 카드 개수를 반환.

int totalPossible() :
현재 맵 상태에서 제거가능한 패가 몇 개인지 구해주는 메소드. dfs2를 활용.

void dfs2(int x, int y, int direction, int corner_count) :
dfs와 매우 유사하며 이 메소드는 (x,y)에서 2번 이내로 꺾이면서 도달할 수 있는 같은 값을 가진 좌표가 있는지 검사.

Path[] getPathData() :
경로를 반환.

Path deleteCardLikeThis(int _x, int _y) :
맵에서 (_x, _y)와 같은값의 카드를 무작위로 1개 제거해준다.



