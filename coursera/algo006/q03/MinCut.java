import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.IOException;

class MinCut {
	private int[][] board;
	private int maxDim;
	private Random random;

	MinCut(int[][] input) {
		maxDim = input.length;
		board = new int[maxDim][maxDim];
		for (int i = 0; i < maxDim; i++) {
			for (int j = 0; j < maxDim; j++) {
				board[i][j] = input[i][j];
			}
		}
		random = new Random();
	}

	private int getRandomInteger (int min, int max) {
		return random.nextInt(max - min) + min;
	}

	private void contract(int p, int q) {
		int end = maxDim - 1;
		for (int j = 0; j < maxDim; j++) {
			board[p][j] += board[q][j];
		}
		for (int i = 0; i < maxDim; i++) {
			board[i][p] += board[i][q];
			if (q < end) {
				board[i][q] = board[i][end];
			}
		}
		if (q < end) {
			for (int j = 0; j < maxDim; j++) {
				board[q][j] = board[end][j];
			}
		}
		board[p][p] = 0;
		// System.out.println("p = " + p + "; q = " + q);
		// for (int i = 0; i < end; i++) {
		// 	for (int j = 0; j < end; j++) {
		// 		System.out.print(board[i][j] + " ");
		// 	}
		// 	System.out.println();
		// }
	}

	public int getMinCut() {
		while (maxDim > 2) {
			int p = getRandomInteger(0, maxDim);
			int q;
			do {
				q = getRandomInteger(0, maxDim);
			} while (p==q);
			if (q < p) {
				int tmp = p;
				p = q;
				q = tmp;
			}
			contract(p, q);
			maxDim--;
		}
		return board[0][1];
	}

	public static void main(String[] args) {
		if (args.length == 0) throw new IllegalArgumentException();
		String fileName = args[0];
		int[][] input;
		int N, mincut = 0;
		try {
			Scanner scanner = new Scanner(new File(fileName));
			if (scanner.hasNextLine()) {
				N = Integer.parseInt(scanner.nextLine().trim());
				input = new int[N][N];
				while (scanner.hasNextLine()) {
					String str = scanner.nextLine().trim();
					Scanner intScan = new Scanner(str);
					if (intScan.hasNextInt()) {
						int rowId = intScan.nextInt() - 1;
						while (intScan.hasNextInt()) {
							int val = intScan.nextInt() - 1;
							input[rowId][val]++;
							mincut++;
						}
					}
				}
				long totalCnt = N*N*N*N;
				long loopCnt = totalCnt;
				while (loopCnt-- > 0) {
					MinCut mt = new MinCut(input);
					int curMt = mt.getMinCut();
					if (curMt < mincut) mincut = curMt;
					System.out.print((100-(100*loopCnt)/totalCnt) + "%\r");
					if (mincut < 20) {
						break;
					}
				}
				System.out.println("MinCut is: " + mincut);
			}
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
}