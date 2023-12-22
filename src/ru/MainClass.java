package ru;
import ru.games.*;
import java.util.Scanner;
public class MainClass{
	public static void main(String[] args) {
		View v = new View();
		Scanner inp = new Scanner(System.in);
		boolean f = true;
		v.clean();
		while(f){
			v.println("Выберите игру:");
			v.println("1.Крестики-нолики");
			v.println("2.Судоку");
			v.println("3.Сапёр");
			v.println("4.Супер стратегия");
			v.println("5.2048");
			v.println("6.Выйти");
			String comand = inp.nextLine();
			switch(comand){
				case "1":
					new CresNolGame().gameMenu();
					break;
				case "2":
					new SudokuGame().gameMenu();
					break;
				case "3":
					new SaperGame().gameMenu();
					break;
				case "4":
					new BatleField().gameMenu();
					break;
				case "5":
					//new NumsGame().gameMenu();
					break;
				case "6":
					f = false;
					break;
				default:
					v.clean();
					v.println("Извините, команда не ясна, попробуйте снова :(");
					break;
			}
		}
	}
}
