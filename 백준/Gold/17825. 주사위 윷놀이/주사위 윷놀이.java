import java.io.*;
import java.util.*;

public class Main {
	static int maxScore = 0;
	static Box[] boxes = new Box[33];

	public static class Horse {
		int idx;

		Horse() {
			this.idx = 0;
		}
	}

	public static class Box {
		int idx, val;
		List<Integer> redGo;
		List<Integer> blueGo;

		Box(int idx) {
			this.idx = idx;
			this.redGo = new ArrayList<>();
			this.blueGo = new ArrayList<>();
			// 파랑이들
			if (this.idx == 5)
				this.blueGo.add(21);
			else if (this.idx == 10)
				this.blueGo.add(28);
			else if (this.idx == 15)
				this.blueGo.add(27);
			// 빨강이
			if ((this.idx >= 0 && this.idx < 20) || this.idx == 28 || this.idx == 30
					|| (this.idx >= 21 && this.idx < 24)) {
				this.redGo.add(this.idx + 1);
			} else if (this.idx >= 25 && this.idx <= 27) {
				this.redGo.add(this.idx - 1);
			} else if (this.idx == 24) {
				this.redGo.add(30);
			} else if (this.idx == 20) {
				this.redGo.add(32);
			} else if (this.idx == 31) {
				this.redGo.add(20);
			} else if (this.idx == 29) {
				this.redGo.add(24);
			}

			if (this.idx > 0 && this.idx <= 20) {
				this.val = idx * 2;
			} else {
				switch (idx) {
				case 21:
					this.val = 13;
					break;
				case 22:
					this.val = 16;
					break;
				case 23:
					this.val = 19;
					break;
				case 25:
					this.val = 26;
					break;
				case 26:
					this.val = 27;
					break;
				case 27:
					this.val = 28;
					break;
				case 30:
					this.val = 30;
					break;
				case 31:
					this.val = 35;
					break;
				case 28:
					this.val = 22;
					break;
				case 29:
					this.val = 24;
					break;
				case 0:
					this.val = 0;
					break;
				case 32:
					this.val = 0;
					break;
				case 24:
					this.val = 25;
					break;
				}
			}
		}

		@Override
		public String toString() {
			return idx + ": " + redGo + ", " + blueGo + ", " + val;
		}
	}

	static int[] diceNum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		diceNum = new int[10];
		for (int i = 0; i < 10; i++) {
			diceNum[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < 33; i++) {
			boxes[i] = new Box(i);
		}
		Horse[] horses = new Horse[4];
		for (int i = 0; i < 4; i++) {
			horses[i] = new Horse();
		}

		rollDice(0, 0, horses);
		System.out.println(maxScore);
	}

	public static void rollDice(int idx, int score, Horse[] horses) {
		// 끝났으면 점수 정산하기
		if (idx == 10) {
			maxScore = Math.max(maxScore, score);
			return;
		}

		// 어느 말을 고를껀지
		for (int h = 0; h < 4; h++) {
			if (horses[h].idx != 32) {
				int before = horses[h].idx;
				int moveCnt = diceNum[idx];
				int next = before;
				// 내가 이 말을 굴릴껍니다 만약 파란색인지 체크
				if (boxes[horses[h].idx].blueGo.size() > 0) {
					// 몇칸을 가야해?
					next = boxes[next].blueGo.get(0);
					moveCnt--;
				}

				while (moveCnt > 0) {
					if (boxes[next].redGo.isEmpty())
						break;
					next = boxes[next].redGo.get(0);
					moveCnt--;
				}

				boolean occupied = false;
				if (next < 32) {
					for (int oh = 0; oh < 4; oh++) {
						if (h != oh && horses[oh].idx == next) {
							occupied = true;
							break;
						}
					}
				}

				if (!occupied) {
					horses[h].idx = next;
					rollDice(idx + 1, score + boxes[next].val, horses);
					horses[h].idx = before;
				}
			}
		}
	}
}