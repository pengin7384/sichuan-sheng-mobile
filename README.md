# sichuan-sheng-mobile 

Data

void addCombo(int tic) :
�޺� �߰��� ���� �޼ҵ��̴�. tic�� �ð��� ��Ÿ���� �ش� �ð����� �߰��� �޺��� �޼��ؾ߸� �޺��� �̾�����.

int[][] loadStage(int stage) :
�������� ������ �������� �־��ֱ����� �޼ҵ��̴�.
1ź���� 5ź������ �ܿ����� ���õ������� 6ź�� ���ǰ�� ��尡 ���õ�����.

boolean isCorrect(int x, int y, int x2, int y2) :
x,y���� x2,y2�� ���Ű����� �ùٸ� ������� ��ȯ. dfs�� Ȱ��.

int getCombo() :
���� �޺�Ƚ���� ������ ��ȯ.

Path
��ǥ���� ����üó�� �����ߴ�.

int getX() :
x��ǥ�� ��ȯ

int getY() :
y��ǥ�� ��ȯ

void setX(int x) :
x��ǥ�� ����

void setY(int y) :
y��ǥ�� ����

int getDirection() :
������ ��ȯ

void setDirection(int direction) :
������ ����

Map
�ʿ� ������ �����͵��� ������ְ� �ش� �����͸� �������ִ� Ŭ����

Map(int time, int kind, int mode, int bomb, int item, int item2, int item3) :
������. ������� �ð�/ī�������� ����/���Ӹ��/��ź����/������1,2,3�� ����

addScore(int score) :
������ �߰����ִ� �޼ҵ�

void editData(int[][] data) :
���� �������� �������ִ� �޼ҵ�

void newData() :
�����ϰ� ���� �������ִ� �޼ҵ� (���ǰ�� ���)

void mixData() :
���� �����ִ� �޼ҵ� (��, ���豸��, �¸�ī��� ������ ����)

void dfs(int x, int y, int direction, int corner_count) :
��Ʈ��ŷ�� ������ �޼ҵ�. ��/��/��/�� 4������ (x,y) �������� ������ ���� ���� ��� Ž�����ش�. direction�� ������ ��Ÿ���� corner_count�� ���� Ž������ ��ΰ� �� �� �������� ��Ÿ����.

void addDirection() :
�̵� ��θ� path�� �߰�.

int totalCard() :
���� ī�� ������ ��ȯ.

int totalPossible() :
���� �� ���¿��� ���Ű����� �а� �� ������ �����ִ� �޼ҵ�. dfs2�� Ȱ��.

void dfs2(int x, int y, int direction, int corner_count) :
dfs�� �ſ� �����ϸ� �� �޼ҵ�� (x,y)���� 2�� �̳��� ���̸鼭 ������ �� �ִ� ���� ���� ���� ��ǥ�� �ִ��� �˻�.

Path[] getPathData() :
��θ� ��ȯ.

Path deleteCardLikeThis(int _x, int _y) :
�ʿ��� (_x, _y)�� �������� ī�带 �������� 1�� �������ش�.



