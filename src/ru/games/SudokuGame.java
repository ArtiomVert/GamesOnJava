package ru.games;
import ru.View;
import ru.fields.SudokuField;
import ru.fields.SudokuFieldForSolving;
import java.util.Scanner;
public class SudokuGame{
	View v = new View();
	public void gameMenu(){
		v.clean();
		Scanner inp = new Scanner(System.in);
		boolean f = true;
		while(f){
			v.println("Выберите режим игры:");
			v.println("1.Создать и решить судоку");
			v.println("2.Решить судоку");
			v.println("3.Выйти из игры");
			String comand = inp.nextLine();
			switch(comand){
				case "1":
					v.clean();
					//generateSud();
					break;
				case "2":
					v.clean();
					//solveSud();
					break;
				case "3":
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

	private int[][] fillField(){
		v.println("Введите поле судоку, где 0 - пустая ячейка. Например:");
		v.println("123050089");
		v.println("456000003");
		v.println("   ...   ");
		v.println("030507091");
		v.println("----------");
		int[][] desk = new int[9][9];
		for (int i = 0; i < 9; i++){
			Scanner in = new Scanner(System.in);
			String s = in.nextLine();
        	for (int j = 0; j<s.length(); j++){
        		try{
        			int a = Integer.parseInt(s.charAt(j) + "");
        			desk[i][j] = a;
        		}catch (Exception e) {
        			v.clean();
        			v.println("В введёных вами данных есть ошибка.\nПопробуйте ввести поле заново.");
        			return fillField();
       			}
        	}
        }
        return desk;
	}

}