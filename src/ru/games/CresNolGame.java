package ru.games;
import ru.View;
import ru.fields.CresNolField;
import java.util.Scanner;
public class CresNolGame{
	View v = new View();
	public void gameMenu(){
		v.clean();
		Scanner inp = new Scanner(System.in);
		boolean f = true;
		while(f){
			v.println("Выберите режим игры:");
			v.println("1.Против бота(Игрок ходит первый)");
			v.println("2.Против бота(Бот ходит превый)");
			v.println("3.Два игрока");
			v.println("4.Выйти из игры");
			String comand = inp.nextLine();
			switch(comand){
				case "1":
					//playWithBot(1);
					break;
				case "2":
					//playWithBot(2);
					break;
				case "3":
					//playWithHuman();
					break;
				case "4":
					v.clean();
					f = false;
					break;
				default:
					v.clean();
					v.println("Извините, команда не ясна, попробуйте скова :(");
					break;
			}
		}
	}
}