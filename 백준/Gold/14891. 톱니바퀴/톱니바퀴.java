import java.io.*;
import java.util.*;

public class Main {
	public static class Gear {
		int[] saw = new int[8];
		int dir = 0;

		Gear(int[] saw) {
			for (int i = 0; i < 8; i++) {
				this.saw[i] = saw[i];
			}
		}

		void rotate(int dir) {
			if (dir == 1) {
				int last = this.saw[7];
				for (int i = 7; i >= 1; i--) {
					this.saw[i] = this.saw[i - 1];
				}
				this.saw[0] = last;
			} else if (dir == -1) {
				int first = this.saw[0];
				for (int i = 0; i < 7; i++) {
					this.saw[i] = this.saw[i + 1];
				}
				this.saw[7] = first;
			}
		}

		int getEast() { // 동쪽
			return this.saw[2];
		}

		int getWest() { // 서쪽
			return this.saw[6];
		}

		int getSouth() { // 남쪽
			return this.saw[4];
		}

		int getNorth() { // 북쪽
			return this.saw[0];
		}

		@Override
		public String toString() {
			String str = "[";
			for (int i = 0; i < 8; i++) {
				str += this.saw[i] + " ";
			}
			str += "]";
			return str;
		}
	}

	public static class Gears {
		Gear[] gearSet = new Gear[4];

		Gears(int[][] saws) {
			for (int i = 0; i < 4; i++) {
				this.gearSet[i] = new Gear(saws[i]);
			}
		}

		void rotate(int gearNum, int dir, int wave) { // wave 0: 양쪽, 1: 오른쪽, 2: 왼쪽
			int[] rotateDir = new int[4];
			rotateDir[gearNum] = dir;
			// 오른쪽 애들 확인
			int check = gearNum + 1;
			while (check < 4) {
				if (this.gearSet[check - 1].getEast() != this.gearSet[check].getWest()) {
					rotateDir[check] = rotateDir[check-1]*(-1);
				}
				check++;
			}
			
			// 왼쪽 애들 확인
			check = gearNum - 1;
			while (check >= 0) {
				if (this.gearSet[check+1].getWest()!=this.gearSet[check].getEast()) {
					rotateDir[check] = rotateDir[check+1]*(-1);
				}
				check--;
			}
			
			for (int i=0; i<4; i++) {
				if (rotateDir[i]!=0) {
					this.gearSet[i].rotate(rotateDir[i]);
				}
			}
		}

		int calculateScore() {
			int score = 0;
			for (int i = 0; i < 4; i++) {
				if (this.gearSet[i].getNorth() == 1) {
					score += Math.pow(2, i);
				}
			}

			return score;
		}

		@Override
		public String toString() {
			String str = "";
			str += "0: " + this.gearSet[0].toString() + "\n";
			str += "1: " + this.gearSet[1].toString() + "\n";
			str += "2: " + this.gearSet[2].toString() + "\n";
			str += "3: " + this.gearSet[3].toString();
			return str;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[][] saws = new int[4][8];
		for (int r = 0; r < 4; r++) {
			String[] strArr = br.readLine().split("");
			for (int c = 0; c < 8; c++) {
				saws[r][c] = Integer.parseInt(strArr[c]);
			}
		}

		Gears gears = new Gears(saws);
		int K = Integer.parseInt(br.readLine());

		for (int r = 0; r < K; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int gearNum = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken());
			gears.rotate(gearNum, dir, 0);
		}

		System.out.println(gears.calculateScore());

	}
}