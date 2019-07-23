package latter001;
import java.util.Scanner;

public class suanfa {
	static int n, m;//n是变量数，m是约束个数
	static float value[] = new float[100];//定义一个数组value，用来存储物品价值
	static float property[][] = new float[100][100];//定义一个数组property，用来存储物品的属性
	static float reproperty[] = new float[100];//定义一个数组reproperty，物品属性的当前值
	static float maxConstraints[] = new float[100];//定义一个数组maxConstraints，存储物品属性的最大约束条件
	static float bestvalue, res;//bestvalue为当前最大价值
 

	public static void main(String[] args) {
		// 数据的输入
		System.out.println("请输入变量个数n和约束个数m");
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		m = scan.nextInt();
		System.out.println("请输入n个物品的价值");
		for (int i = 1; i <= n; i++) {
			value[i] = scan.nextFloat();
		}
		for (int i = 1; i <= m; i++) {
			System.out.println(" 请输入第" + i + " 个物品属性");
			for (int j = 1; j <= n; j++) {
				property[j][i] = scan.nextInt();
			}
		}
		System.out.println(" 输入m个属性的最大约束条件");
		for (int i = 1; i <= m; i++) {
			maxConstraints[i] = scan.nextInt();
		}
		
		BackTrack(1,0);		//利用回溯法求解答案 
		System.out.println(" 得到的最大价值为:"+bestvalue);

	}

	
	static boolean check(int x, float revalue) {
		// 限界,减去不可能包含最优解的情况
		float res = 0;
		for (int i = x + 1; i <= n; i++) {
			res += value[i];
		}

		if (revalue + value[x] + res <= bestvalue)
			return false;
		// 剪枝函数，剪去不可能包含正确答案的情况
		for (int i = 1; i <= m; i++) {
			if (reproperty[i] + property[x][i] > maxConstraints[i]) { // 如果当前i属性加上x的i属性大于属性i的最大约束，则x不可选，返回false
				return false;
			}
		}
		// 满足可选条件,返回true
		return true;
	}

	
	static void BackTrack(int x, float revalue) {
		if (x > n) {
			if (revalue > bestvalue) { // 更新结果
				bestvalue = revalue;
			}
			return;
		}
		BackTrack(x + 1, revalue); // 不选择物品x
									// 限界条件+约束条件
		if (!check(x, revalue))
			return;
		for (int i = 1; i <= m; i++) { // 选择x，当前各个属性加上x的属性
			reproperty[i] += property[x][i]; // reproperty[i]代表走到物品x时属性i当前的值
		}
		BackTrack(x + 1, revalue + value[x]); 	// 选择x
		for (int i = 1; i <= m; i++) { 		// 回溯
			reproperty[i] -= property[x][i];
		}
	}
}
