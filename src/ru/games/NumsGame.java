package ru.games;
import ru.View;
import ru.fields.NumsField;
import java.util.Scanner;
import java.util.Random;
public class NumsGame{
	View v = new View();
	boolean playf = true;
	NumsField field;
	public void gameMenu(){
		v.clean();
		Scanner inp = new Scanner(System.in);
		boolean f = true;
		while(f){
			v.println("Выберите режим игры:");
			v.println("1.Маленькое поле");
			v.println("2.Среднее поле");
			v.println("3.Большое поле");
			v.println("4.Выйти из игры");
			String comand = inp.nextLine();
			switch(comand){
				case "1":
					v.clean();
					play(5);
					break;
				case "2":
					v.clean();
					play(10);
					break;
				case "3":
					v.clean();
					play(15);
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

	private void play(int n){	
		field = new NumsField(n);
		for (int i = 0; i <= n/2; i++){		
			field.fillField();
		}
		while(playf){
			if (field.isFull()){
				v.clean();
				v.field2048(field.field);
				v.println("Ходы невозможны. Игра закончилась.");
				break;
			}
			hod();
			if (field.hasZero()){
				field.fillField();
			}
		}
		playf = true;
	}
	private void hod(){
		v.clean();
		v.field2048(field.field);
		v.println("Сделайте ход:");
		v.println("w - вверх");
		v.println("d - вправо");
		v.println("a - влево");
		v.println("s - вниз");
		v.println("x - Выйти из игры");
		Scanner inp = new Scanner(System.in);
		boolean f = true;
		while (f){
			String comand = inp.nextLine();
			switch(comand){
				case "w":
					field.hodw();
					f=false;
					break;
				case "d":
					field.hodd();
					f=false;
					break;
				case "s":
					field.hods();
					f=false;
					break;
				case "a":
					field.hoda();
					f=false;
					break;
				case "x":
					v.clean();
					f = false;
					playf = false;
					break;
				default:
					v.println("Извините, команда не ясна, попробуйте скова :(");
					break;
			}
		}
	}
}