package ru.fields;
import java.util.Random;
public class NumsField{
	public int[][] field;
	private int n;
	public NumsField(int n){
		this.n = n;
		field = new int[n][n];
	}
	public void fillField(){
		Random r = new Random();
		int x;
		int y;
		do{
			x = r.nextInt(n);
			y = r.nextInt(n);
		}while(field[x][y]!=0);
		field[x][y] = 2;
	}
	public void hodw(){
		for (int c = 0; c < n-1; c++){
			for (int i = 0; i < n-1; i++){
				for (int j = 0; j < n; j++){
					if (field[i][j] == field[i+1][j] || field[i][j] == 0){
						field[i][j] = field[i][j] + field[i+1][j];
						field[i+1][j] = 0;
					}
				}
			}
		}
	}
	public void hoda(){
		for (int c = 0; c < n-1; c++){
			for (int i = 0; i < n-1; i++){
				for (int j = 0; j < n; j++){
					if (field[j][i] == field[j][i+1] || field[j][i] == 0){
						field[j][i] = field[j][i] + field[j][i+1];
						field[j][i+1] = 0;
					}
				}
			}
		}
	}
	public void hods(){
		for (int c = 0; c < n-1; c++){
			for (int i = n-1; i > 0; i--){
				for (int j = 0; j < n; j++){
					if (field[i][j] == field[i-1][j] || field[i][j] == 0){
						field[i][j] = field[i][j] + field[i-1][j];
						field[i-1][j] = 0;
					}
				}
			}
		}
	}
	public void hodd(){
		for (int c = 0; c < n-1; c++){
			for (int i = n-1; i > 0; i--){
				for (int j = 0; j < n; j++){
					if (field[j][i] == field[j][i-1] || field[j][i] == 0){
						field[j][i] = field[j][i] + field[j][i-1];
						field[j][i-1] = 0;
					}
				}
			}
		}
	}

	public boolean hasZero(){
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				if (field[i][j]==0){
					return true;
				}
			}
		}
		return false;
	}
	public boolean isFull(){
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				if (field[i][j] == 0){
					return false;
				}
				try{
					if (field[i][j] == field[i-1][j]){
						return false;
					}
				}catch(Exception e){}
				try{
					if (field[i][j] == field[i+1][j]){
						return false;
					}
				}catch(Exception e){}
				try{
					if (field[i][j] == field[i][j-1]){
						return false;
					}
				}catch(Exception e){}
				try{
					if (field[i][j] == field[i][j+1]){
						return false;
					}
				}catch(Exception e){}
			}
		}
		return true;
	}
}