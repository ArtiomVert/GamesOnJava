package ru.fields;
import java.util.Random;
public class CresNolField{
	public int[][] desk = new int[3][3];
	private int n = 3;
	public boolean itisend(){
		int q;
		for (int i = 0; i < n; i++){
			q = desk[i][0]*desk[i][1]*desk[i][2];
			if (q == 1 || q == 8){
				return true;
			}
			q = desk[0][i]*desk[1][i]*desk[2][i];
			if (q == 1 || q == 8){
				return true;
			}
		}
		q = desk[0][0]*desk[1][1]*desk[2][2];
		if (q == 1 || q == 8){
			return true;
		}
		q = desk[0][2]*desk[1][1]*desk[2][0];
		if (q == 1 || q == 8){
			return true;
		}
		return false;
	}
	public void botHod(int who){
		int x;
		int y;
		Random r = new Random();
		do{
			x = r.nextInt(3);
			y = r.nextInt(3);
		}while(desk[x][y]!=0);
		desk[x][y] = (1+who%2);
	}
}