package ru.fields;
import java.util.Random;
public class SaperField{
	private int[][] field;
	public String[][] vis_field;
	private boolean first = true;
	private int size;
	private int amount;
	public SaperField(int size, int amount){
		this.size = size;
		this.amount = amount;
		field = new int[size][size];
		vis_field = new String[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				vis_field[i][j] = "█";
			}
		}
	}
	public boolean isEnd(){
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (vis_field[i][j].equals("*")){
					return true;
				}
			}
		}
		return (flagAndCloseAmount() == amount);
	}
	private int flagAndCloseAmount(){
		int c = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (vis_field[i][j].equals("P") || vis_field[i][j].equals("█")){
					c++;
				}
			}
		}
		return c;
	}

	public void end(){
		if (first){
			fillField(size/2, size/2);
		}
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (field[i][j] == 10){
					vis_field[i][j] = "*";
				}
			}
		}
	}

	private void fillField(int x1, int y1){
		Random r = new Random();
		for (int i = 0; i < amount; i++){
			int x;
			int y;
			do{
				x = r.nextInt(size);
				y = r.nextInt(size);
			}while(field[x][y]==10 || ((x1 - x) * (x1 - x) + (y1 - y) * (y1-y) <= 16));
			field[x][y] = 10;
		}
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (field[i][j] == 10){
					continue;
				}
				field[i][j] = amountMine(i, j);
			}
		}
	}
	private int amountMine(int i, int j){
		int c = 0;
		for (int i1 = -1; i1 <=1; i1 ++){
			for (int j1 = -1; j1 <=1; j1 ++){
				try{
					if(field[i+i1][j+j1] == 10){
						c++;
					}
				}catch (Exception e){}
			}
		}
		return c;
	}

	public void flag(int i, int j){
		vis_field[i][j] = "P";
	}
	public void unFlag(int i, int j){
		vis_field[i][j] = "█";
	}
	public boolean open(int i1, int j1){
		if (first){
			fillField(i1, j1);
			first = false;
		}
		try{
			if (!vis_field[i1][j1].equals("█")){
				return true;
			}
			switch(field[i1][j1]){
				case 10:
					end();
					return true;
				case 0:
					vis_field[i1][j1] = " ";
					for (int i = -1; i <=1; i ++){
						for (int j = -1; j <= 1; j ++){
							open(i1+i, j1+j);
						}
					}
					return true;
				default:
					vis_field[i1][j1] = field[i1][j1] + "";
					return true;
 			}
		} catch(Exception e){}
		return true;
	}
}