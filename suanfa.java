package latter001;
import java.util.Scanner;

public class suanfa {
	static int n, m;//n�Ǳ�������m��Լ������
	static float value[] = new float[100];//����һ������value�������洢��Ʒ��ֵ
	static float property[][] = new float[100][100];//����һ������property�������洢��Ʒ������
	static float reproperty[] = new float[100];//����һ������reproperty����Ʒ���Եĵ�ǰֵ
	static float maxConstraints[] = new float[100];//����һ������maxConstraints���洢��Ʒ���Ե����Լ������
	static float bestvalue, res;//bestvalueΪ��ǰ����ֵ
 

	public static void main(String[] args) {
		// ���ݵ�����
		System.out.println("�������������n��Լ������m");
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		m = scan.nextInt();
		System.out.println("������n����Ʒ�ļ�ֵ");
		for (int i = 1; i <= n; i++) {
			value[i] = scan.nextFloat();
		}
		for (int i = 1; i <= m; i++) {
			System.out.println(" �������" + i + " ����Ʒ����");
			for (int j = 1; j <= n; j++) {
				property[j][i] = scan.nextInt();
			}
		}
		System.out.println(" ����m�����Ե����Լ������");
		for (int i = 1; i <= m; i++) {
			maxConstraints[i] = scan.nextInt();
		}
		
		BackTrack(1,0);		//���û��ݷ����� 
		System.out.println(" �õ�������ֵΪ:"+bestvalue);

	}

	
	static boolean check(int x, float revalue) {
		// �޽�,��ȥ�����ܰ������Ž�����
		float res = 0;
		for (int i = x + 1; i <= n; i++) {
			res += value[i];
		}

		if (revalue + value[x] + res <= bestvalue)
			return false;
		// ��֦��������ȥ�����ܰ�����ȷ�𰸵����
		for (int i = 1; i <= m; i++) {
			if (reproperty[i] + property[x][i] > maxConstraints[i]) { // �����ǰi���Լ���x��i���Դ�������i�����Լ������x����ѡ������false
				return false;
			}
		}
		// �����ѡ����,����true
		return true;
	}

	
	static void BackTrack(int x, float revalue) {
		if (x > n) {
			if (revalue > bestvalue) { // ���½��
				bestvalue = revalue;
			}
			return;
		}
		BackTrack(x + 1, revalue); // ��ѡ����Ʒx
									// �޽�����+Լ������
		if (!check(x, revalue))
			return;
		for (int i = 1; i <= m; i++) { // ѡ��x����ǰ�������Լ���x������
			reproperty[i] += property[x][i]; // reproperty[i]�����ߵ���Ʒxʱ����i��ǰ��ֵ
		}
		BackTrack(x + 1, revalue + value[x]); 	// ѡ��x
		for (int i = 1; i <= m; i++) { 		// ����
			reproperty[i] -= property[x][i];
		}
	}
}
