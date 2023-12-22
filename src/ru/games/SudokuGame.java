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
					generateSud();
					break;
				case "2":
					v.clean();
					solveSud();
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
	private void solveSud(){
		SudokuField field = new SudokuField(fillField());
		if (field.solve()){
			v.clean();
			v.println("Вот решённый судоку:");
			v.printField(field.field);
		} else {
			v.clean();
			v.println("Этот судоку невозможен");
		}
	}
	private boolean has(int v, int s, int e){
    	return v>=s && v<=e;
    }
	private void generateSud(){
		SudokuFieldForSolving field = new SudokuFieldForSolving(50);
		while (!field.isFull()){
			v.clean();
			v.printField(field.field);
			v.println("Введите поле и значение. В формате [строка столбец значение]");
			v.println("Чтобы получить подсказку введите [строка столбец 'hint']");
			v.println("Чтобы закончить игру введите ['x' 'x' 'x']");
			Scanner in = new Scanner(System.in);
			while (true){
				try{
					String[] s = in.nextLine().split(" ");
					if (s[0].equals("x") && s[1].equals("x") && s[2].equals("x")){
						field.solve();
						break;
					}
					int i = Integer.parseInt(s[0])-1;
					int j = Integer.parseInt(s[1])-1;
					if (field.field[i][j]!=0){
						v.println("Это поле уже занято, попробуйте снова");
    					continue;
    				}
					if (s[2].equals("hint")){
						field.hint(i, j);
						break;
					}
					int val = Integer.parseInt(s[2]);
					if (!has(i, 0, 8) || !has(j, 0, 8) || !has(val, 1, 9)){
    					v.println("Введены некорректные данные, попробуйте снова");
    					continue;
    				}
    				
    				if (!field.paste(i, j, val)){
    					v.println("ОШИБКА. Это не верно. Попробуйте снова");
    					continue;
    				}
    				break;
				}catch (Exception e) {
					v.println("Команда не ясна, попробуйте снова");
				}
			}
		}
		v.clean();
		v.println("Игра закончена");
		v.printField(field.field);
	}

}