package ru;
public class View{
	public void clean(){
		try{
			new ProcessBuilder("clear").inheritIO().start().waitFor();//Linux
			//new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();//Windows
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	public void println(Object s){
		System.out.println(s);
	}
	public void print(Object s){
		System.out.print(s);
	}
	public String ch(int n){
		switch(n){
			case 0:
				return " ";
			case 1:
				return "X";
			case 2:
				return "O";
		}
		return " ";
	}
	public void printDesk(int[][] desk){
		println(ch(desk[0][0]) + "|" + ch(desk[0][1]) + "|" + ch(desk[0][2]));
		println("-+-+-");
		println(ch(desk[1][0]) + "|" + ch(desk[1][1]) + "|" + ch(desk[1][2]));
		println("-+-+-");
		println(ch(desk[2][0]) + "|" + ch(desk[2][1]) + "|" + ch(desk[2][2]));
	}
	public void printField(int[][] field){
		for (int i = 0; i < 9; i++){
        	for (int j = 0; j < 9; j++){
        		print(field[i][j]==0?" ":field[i][j]);
        		print((j == 2 || j == 5)?"|":" ");
        	}
        	println("");
        	if (i == 2 || i == 5){
        		println("-----+-----+-----");
        	}
        }
	}
	public void saperField(String[][] field){
		int n = field.length;
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				print(field[i][j]);
			}
			println("");
		}
	}
	public void sleep(int a){
		try{
			Thread.sleep(a);
		}catch(Exception e){}
	}
	public void text(String s){
		for (int i = 0; i<s.length(); i++){
			print(s.charAt(i)+"");
			sleep(50);
		}
		println("");
	}
	public void batleField(int n, int[][] myField, int[][] botField){
		clean();
		for (int i = 1; i <= n; i++){
			print(i+"");
		}
		print("   ");
		for (int i = 1; i <= n; i++){
			print(i+"");
		}
		println("");
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				String[] charF = {" ", "Л", "▓", "+"};
					print(charF[myField[i][j]]);
			}
			print("|"+(i+1)+"|");
			for (int j = 0; j < n; j++){
				String[] charF = {"░", "░", "░", "+"};
					print(charF[botField[i][j]]);
			}
			println("");
		}
	}
	public void airShootingField(int n, int[][] myField, int[][] botField, int x, int y){
		for (int qwer = 0; qwer < 4; qwer++){
			clean();
			for (int i = 1; i <= n; i++){
				print(i+"");
			}
			print("   ");
			for (int i = 1; i <= n; i++){
				print(i+"");
			}
			println("");
			for (int i = 0; i < n; i++){
				for (int j = 0; j < n; j++){
					String[] charF = {" ", "Л", "▓", "+"};
					print(charF[myField[i][j]]);
				}
				print("|"+(i+1)+"|");
				for (int j = 0; j < n; j++){
					if (Math.abs(x-i)<=1 && j == y){
						String[] charF = {"█", "▓", "▒", "░"};
						print(charF[qwer]);
						continue;
					}
					String[] charF = {"░", "░", "░", "+"};
					print(charF[botField[i][j]]);
				}
				println("");
			}
			sleep(300);
		}
	}
	public void shootingField(int n, int[][] myField, int[][] botField, int x, int y, boolean player){
		
		for (int qwer = 0; qwer < 4; qwer++){
			clean();
			for (int i = 1; i <= n; i++){
				print(i+"");
			}
			print("   ");
			for (int i = 1; i <= n; i++){
				print(i+"");
			}
			println("");
			for (int i = 0; i < n; i++){
				for (int j = 0; j < n; j++){
					if (x == i && j == y && !player){
						String[] charF = {"█", "▓", "▒", "░"};
						print(charF[qwer]);
						continue;
					}
					String[] charF = {" ", "Л", "▓", "+"};
					print(charF[myField[i][j]]);
				}
				print("|"+(i+1)+"|");
				for (int j = 0; j < n; j++){
					if (x == i && j == y && player){
						String[] charF = {"█", "▓", "▒", "░"};
						print(charF[qwer]);
						continue;
					}
					String[] charF = {"░", "░", "░", "+"};
					print(charF[botField[i][j]]);
				}
				println("");
			}
			sleep(300);
		}
	}
	private String q(String s, int n){
		String sout = "";
		for (int i = 0; i < n; i++){
			sout += s;
		}
		return sout;
	}
	public void field2048(int[][] f){
		int lenf = f.length;
		int maxnum = 0;
		for (int i = 0; i < lenf; i++){
			for (int j = 0; j < lenf; j++){
				if (maxnum < f[i][j]){
					maxnum = f[i][j];
				}
			}
		}
		int maxlen = String.valueOf(maxnum).length();
		for (int i = 0; i < lenf; i++){
			for (int j = 0; j < lenf; j++){
				print("|");
				if (f[i][j]!=0){
					int loclen = String.valueOf(f[i][j]).length();
					print(q(" ", (maxlen - loclen+1)/2) + f[i][j] + q(" ", (maxlen - loclen)/2));
				} else {
					print(q(" ", maxlen));
				}
			}
			println("");
			for (int j = 0; j < lenf; j++){
				print("+" + q("-", maxlen));
			}
			println("");
		}
	}
}