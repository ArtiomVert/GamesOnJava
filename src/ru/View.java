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
}