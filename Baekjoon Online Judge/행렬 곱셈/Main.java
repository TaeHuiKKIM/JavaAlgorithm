import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] a = new int[n][m];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                a[i][j] = sc.nextInt();
            }
        }
        int m2 = sc.nextInt();
        int k = sc.nextInt();
        int[][] b = new int[m2][k];
        for(int i=0; i<m2; i++){
            for(int j=0; j<k; j++){
                b[i][j]=sc.nextInt();
            }
        }

        int[][] result = multiply(a,b,n,m,k);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                sb.append(result[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
    static int[][] multiply(int [][] A, int[][] B, int size){
        int[][] C = new int[size][size];
        // size가 1로 가장 작게 쪼개질 경우 (0,0) 원소밖에 없으므로 해당 원소의 곱을 반환
        if(size == 1) {
            C[0][0] = A[0][0] * B[0][0];
            return C;
        }
        int newSize = size / 2;	// 부분행렬에 대한 사이즈
        
    }
}