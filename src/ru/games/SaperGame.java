package ru.games;
import ru.View;
import ru.fields.SaperField;
import java.util.Scanner;
public class SaperGame{
	View v = new View();
	public void gameMenu(){
		v.clean();
		Scanner inp = new Scanner(System.in);
		boolean f = true;
		while(f){
			v.println("Выберите режим игры:");
			v.println("1.Легкий(поле 10*10, 10 мин)");
			v.println("2.Средний(поле 15*15, 40 мин)");
			v.println("3.Сложный(поле 20*20, 100 мин)");
			v.println("4.Выйти из игры");
			String comand = inp.nextLine();
			switch(comand){
				case "1":
					play(10, 10);
					break;
				case "2":
					play(15, 40);
					break;
				case "3":
					play(20, 100);
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
	private boolean has(int v, int s, int e){
    	return v>=s && v<=e;
    }
    private boolean has2(String s, String[] acts){
    	for (String a:acts){
    		if (s.equals(a)){
    			return true;
    		}
    	}
    	return false;
    }
	private void play(int size, int amount){
		SaperField field = new SaperField(size, amount);
		while (!field.isEnd()){
			v.clean();
			v.saperField(field.vis_field);
			v.println("Введите поле и действие. В формате [строка столбец действие]");
			v.println("Действия:");
			v.println("'open' - открыть ячейку");
			v.println("'flag' - пометить ячейку как минную");
			v.println("'unflag' - пометить ячейку как минную");
			v.println("Чтобы закончить игру введите ['x' 'x' 'x']");
			Scanner in = new Scanner(System.in);
			while (true){
				try{
					String[] s = in.nextLine().split(" ");
					if (s[0].equals("x") && s[1].equals("x") && s[2].equals("x")){
						field.end();
						break;
					}
					int i = Integer.parseInt(s[0])-1;
					int j = Integer.parseInt(s[1])-1;
					if (field.vis_field[i][j]!="█" && field.vis_field[i][j]!="P"){
						v.println("Это поле уже открыто");
    					continue;
    				}
    				String[] acts = {"open", "flag", "unflag"};
					if (!has(i, 0, size-1) || !has(j, 0, size-1) || !has2(s[2], acts)){
    					v.println("Введены некорректные данные, попробуйте снова");
    					continue;
    				}
    				switch (s[2]) {
    					case "open":
    						field.open(i, j);
    						break;
    					case "flag":
    						field.flag(i, j);
    						break;
    					case "unflag":
    						field.unFlag(i, j);
    						break;
    				}
    				break;
				}catch (Exception e) {
					v.println("Команда не ясна, попробуйте снова");
				}
			}
		}
		v.clean();
		v.println("Игра закончена");
		v.saperField(field.vis_field);
	}
}