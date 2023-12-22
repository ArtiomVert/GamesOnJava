package ru;
public class View{
	public void clean(){
		try{
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch(Exception e){
			System.out.println("Извините, какие-то ошибки");
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
}