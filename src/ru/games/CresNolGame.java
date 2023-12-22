package ru.games;
import ru.View;
import ru.fields.CresNolField;
import java.util.Scanner;
import java.util.Random;
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
					playWithBot(1);
					break;
				case "2":
					playWithBot(2);
					break;
				case "3":
					playWithHuman();
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
	private int[] inputTwoInt(){
		try {
        	Scanner in = new Scanner(System.in);
        	String[] s = in.nextLine().split(" ");
        	return new int[] {Integer.parseInt(s[0]), Integer.parseInt(s[1])};
    	} catch (Exception e) {
        	v.println("Пожалуйста, попробуйте снова.");
        	return inputTwoInt();
    	}
	}

	private void playWithBot(int who){
		CresNolField field = new CresNolField();
		boolean end = false;
		for (int i = 0; i < 9; i++){
			v.clean();
			v.printDesk(field.desk);
			if ((i+who-1)%2==1){
				field.botHod(who);
			}else{
				v.println("Ходит игрок("+v.ch(who)+"). Введите поле, куда хотите сходить(строка, столбец). Например '3 3'");
				int x = 0;
				int y = 0;
				boolean f = true;
				do{
					int[] place = inputTwoInt();
					x = place[0]-1;
					y = place[1]-1;
					try{
						if (field.desk[x][y]==0){
							f = false;
						}else{
							v.println("Это место занято, попробуйте другое.");
						}
			    	}catch(Exception e){
			    		v.println("Пожалуйста, попробуйте снова.");
			    	}
				}while(f);
				field.desk[x][y] = who;
			}
			v.clean();
			if (field.itisend()){
				end = true;
				if ((i+who-1)%2==1){
					v.println("Бот выиграл");
				}else{
					v.println("Игрок выиграл");
				}
				
				break;
			}
		}
		if (end == false){
			v.println("Победила дружба! :)");
		}
		v.printDesk(field.desk);
	}

	private void playWithHuman(){
		CresNolField field = new CresNolField();
		boolean end = false;
		for (int i = 0; i < 9; i++){
			v.clean();
			v.printDesk(field.desk);
			v.println("Ходит игрок " + (1 + i%2) + " (" + v.ch(1 + i%2) + "). Введите поле, куда хотите сходить(строка, столбец). Например '3 3'");
			int x = 0;
			int y = 0;
			boolean f = true;
			do{
				int[] place = inputTwoInt();
				x = place[0]-1;
				y = place[1]-1;
				try{
					if (field.desk[x][y]==0){
						f = false;
					}else{
						v.println("Это место занято, попробуйте другое.");
					}
			    }catch(Exception e){
			    	v.println("Пожалуйста, попробуйте снова.");
			    }
			}while(f);
			v.clean();
			field.desk[x][y] = 1+i%2;
			if (field.itisend()){
				end = true;
				v.println("Игрок " + (1+i%2) +" выиграл");
				break;
			}
		}
		if (end==false){
			v.println("Победила дружба! :)");
		}
		v.printDesk(field.desk);
	}
}