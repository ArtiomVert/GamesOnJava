package ru.games;
import ru.View;
import java.util.Scanner;
import java.util.Random;
public class BatleField{
	View v = new View();
	int n = 9;
	int money = 200;
	int myCountArmy = 10;
	int botCountArmy = 10;
	int[][] myField = new int[n][n];
	int[][] botField = new int[n][n];

	public void gameMenu(){
		v.clean();
		Scanner inp = new Scanner(System.in);
		boolean f = true;
		while(f){
			v.println("Выберите режим игры:");
			v.println("1.Лёгкий бот");
			v.println("2.Невозможный бот");
			v.println("3.Пройти обучение");
			v.println("4.Выйти из игры");
			String comand = inp.nextLine();
			switch(comand){
				case "1":
					v.clean();
					play(false);
					break;
				case "2":
					v.clean();
					play(true);
					break;
				case "3":
					v.clean();
					obychenie();
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
	/*
	0-ничего
	1-военная единица
	2-мирное сооружение
	3-мёртвый юнит
	*/
	private void fillField(){
		botField = new int[n][n];
		myField = new int[n][n];
		botCountArmy = 10;
		myCountArmy = 10;
		Random r = new Random();
		for (int i = 0; i < myCountArmy; i++){
			int x;
			int y;
			do{
				x = r.nextInt(n);
				y = r.nextInt(n);
			}while(myField[x][y] != 0);
			myField[x][y] = 1;
			do{
				x = r.nextInt(n);
				y = r.nextInt(n);
			}while(myField[x][y] != 0);
			myField[x][y] = 2;
		}
		for (int i = 0; i < botCountArmy; i ++){
			int x;
			int y;
			do{
				x = r.nextInt(n);
				y = r.nextInt(n);
			}while(botField[x][y] != 0);
			botField[x][y] = 1;
			do{
				x = r.nextInt(n);
				y = r.nextInt(n);
			}while(botField[x][y] != 0);
			botField[x][y] = 2;
		}
	}

	private int[] getInt(){
		while (true) {
        	try {
        		Scanner in = new Scanner(System.in);
        		return new int[] {in.nextInt(), in.nextInt()};
    		} catch (Exception e) {
        		v.println("Пожалуйста, не балуйтесь и попробуйте снова.");
    		}
		}
	}
	private void strelba(){
		int x = 0;
		int y = 0;
		boolean f = true;
		do{
			int[] cos = getInt();
			x = cos[0];
			y = cos[1];
			if (x>=1 && x<=n && y>=1 && y<=n){
				v.shootingField(n, myField, botField, x-1, y-1, true);
				switch(botField[x-1][y-1]){
					case 0:
						v.println("Мимо");
						break;
					case 1:
						v.println("Цель поражена");
						botField[x-1][y-1] = 3;
						botCountArmy -= 1;
						break;
					case 2:
						v.println("О нет! Мы попали в жилой район... -50 монет");
						money -= 50;
						botField[x-1][y-1] = 3;
						break;
					case 3:
						v.println("Мимо. Что за вандализм!?");
						break;
				}
				f = false;
			}else {
				v.println("Пожалуйста, не балуйтесь и попробуйте снова.");
			}
		}while(f);
		v.sleep(2000);
	}

	//ход "авиаудар"
	private void aviaydar(){
		v.println("Введите координаты удара");
		int x = 0;
		int y = 0;
		boolean f = true;
		do{
			int[] cos = getInt();
			x = cos[0]-1;
			y = cos[1]-1;
			if (x >= 0 && x<=8 && y>=0 && y<=8){
				f = false;
			} else {
				v.println("Введите корректные координаты. Наприме: 1 9");
			}
		}while(f);
		int[] stat = new int[3];
		for (int j = -1; j<=1; j++){
			for (int i = -1; i<=1; i++){
				try{
					stat[botField[x+i][y+j]%3] += 1;
					switch(botField[x+i][y+j]){
						case 0:
							break;
						case 1:
							botField[x+i][y+j] = 3;
							botCountArmy -= 1;
							break;
						case 2:
							money -= 50;
							botField[x+i][y+j] = 3;
							break;
						case 3:
							break;
					}
				}catch(Exception e){}
			}
			v.airShootingField(n, myField, botField, x, y+j);
		}
		v.batleField(n, myField, botField);
		v.println("Отчёт:");
		v.println(stat[0] + " промахов");
		v.println(stat[1] + " целей поражено");
		v.println(stat[2] + " жилых районов задето (-" + (50*stat[2]) + " монет)");
		v.sleep(5000);
	}
	//TODO сделать передвижение лагерей
	private void voiska(){
		v.println("Выберите координаты лагеря, который хотите передвинуть");
		int x1 = 0;
		int y1 = 0;
		boolean f = true;
		do{
			int[] cos = getInt();
			x1 = cos[0]-1;
			y1 = cos[1]-1;
			if (myField[x1][y1] == 1){
				f = false;
			} else {
				v.println("Введите корректные координаты. Наприме: 1 9");
			}
		}while(f);
		v.println("Теперь введите координаты места переброски");
		int x2 = 0;
		int y2 = 0;
		f = true;
		do{
			int[] cos = getInt();
			x2 = cos[0]-1;
			y2 = cos[1]-1;
			if (myField[x2][y2]==0){
				f = false;
			} else {
				v.println("Введите корректные координаты. Наприме: 1 9");
			}
		}while(f);
		myField[x1][y1] = 0;
		myField[x2][y2] = 1;
		v.batleField(n, myField, botField);
	}
	private void razvedka(){
		int[][] q = new int[botCountArmy][2];
		int c = 0;
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++) {
				if (botField[i][j]==1){
					q[c][0] = i + 1;
					q[c][1] = j + 1;
					c+=1;
				}
			}
		}
		Random r = new Random();
		int w = r.nextInt(c);
		v.println("Разведка сообщила о лагере врага в квадрате " + q[w][0] + " " + q[w][1]);
	}

	private void botHod(){
		Random r = new Random();
		int x = r.nextInt(9);
		int y = r.nextInt(9);
		switch (myField[x][y]){
			case 1:
				myField[x][y] = 3;
				myCountArmy -= 1;
				break;
			case 2:
				myField[x][y] = 3;
				break;
		}
		v.shootingField(n, myField, botField, x, y, false);
		if (r.nextInt(3)==2){
			int x1 = r.nextInt(9);
			int y1 = r.nextInt(9);
			do{
				x1 = r.nextInt(9);
				y1 = r.nextInt(9);
			}while(botField[x1][y1]!=1);
			int x2 = r.nextInt(9);
			int y2 = r.nextInt(9);
			do{
				x2 = r.nextInt(9);
				y2 = r.nextInt(9);
			}while(botField[x2][y2]!=0);
			botField[x1][y1] = 0;
			botField[x2][y2] = 1;
		}
	}
	private void play(boolean hardBot){
		money = 200;
		fillField();
		boolean game = true;
		while(game){
			v.clean();
			v.batleField(n, myField, botField);
			v.println("Денег: " + money);
			v.println("Ваш ход. Что вы хотите сделать?");
			v.println("1.Обстрелять врага");
			v.println("2.Сосредоточиться на экономике (+100 монет)");
			v.println("3.Вызвать поддержку(авиаудар 3*3) (-150 монет)");
			v.println("4.Передислокация войск");
			v.println("5.Разведка (-50 монет)");
			v.println("6.Завершить эту игру");
			Scanner inp = new Scanner(System.in);
			boolean f = true;
			while (f){
				String comand = inp.nextLine();
				switch(comand){
					case "1":
						f = false;
						v.println("Введите координаты цели");
						strelba();
						break;
					case "2":
						f = false;
						money += 100;
						break;
					case "3":
						f = false;
						money -= 150;
						aviaydar();
						break;
					case "4":
						f = false;
						voiska();
						break;
					case "5":
						f = false;
						money -= 50;
						razvedka();
						v.sleep(4000);
						break;
					case "6":
						f = false;
						game = false;
						v.clean();
						break;
					default:
						v.println("Извините, команда не ясна, попробуйте скова :(");
				}
			}
			if (botCountArmy == 0 || money < 0){
				break;
			}
			v.println("Ходит Бот, подождите.");
			v.sleep(1000);
			if (hardBot){	
				for (int qw = 0; qw < botCountArmy * 2; qw++) {
					botHod();
				}
			} else {
				botHod();
			}
			if (myCountArmy == 0){
				break;
			}
		}
		v.clean();
		v.batleField(n, myField, botField);
		v.println("Количество живых лагерей - " + myCountArmy + ". Количество оставшихся денег - " + money);
		if (botCountArmy == 0){
			v.println("-------------------------------------");
			v.println("ТОВАРИЩ! ПОБЕДА ЗА НАМИ! УРА УРА УРА!");
		}
		if (myCountArmy == 0 || money < 0){
			v.println("-------------------------------------------------");
			v.println("Наши войска потерпели поражение, теперь мы должны" +
				"\nпостоять за себя сами. На этом ваша работа закончена.");
		}
	}

	private void obychenie(){
		Scanner inp = new Scanner(System.in);
		v.text("Приветствую тебя, товарищ!");
		v.text("Наш мир стоит на пороге войны!");
		v.text("Тебе придётся противостоять иноземным захватчикам");
		v.text("чтобы люди могли спать спокойно.");
		v.text("Но для этого надо знать основы войны, поэтому начнём твоё обучение.");
		v.println("Введите что-нибудь для продолжения");
		String s = inp.nextLine();
		fillField();
		v.batleField(n, myField, botField);
		v.println("Денег: " + money);
		v.text("Так будет выглядеть ваше поле боя.");
		v.text("Слева твоя земля, справа - врага.");
		v.text("Ниже видны твои деньги.");
		v.text("Каждый из вас имеет:");
		v.text("10 лагерей войск (Л)");
		v.text("10 жилых районов (▓)");
		v.text("И да, ты не видишь поле врага.");
		v.println("Введите что-нибудь для продолжения");
		s = inp.nextLine();
		v.clean();
		v.println("-------------------------");
		v.println("Ваш ход. Что вы хотите сделать?");
		v.println("1.Обстрелять врага");
		v.println("2.Сосредоточиться на экономике (+100 монет)");
		v.println("3.Вызвать поддержку(авиаудар 3*3) (-150 монет)");
		v.println("4.Передислокация войск");
		v.println("5.Разведка (-50 монет)");
		v.println("-------------------------");
		v.text("Это твоё меню действия. Ты можешь:");
		v.text("1) Сделать точный обстрел позиций врага в заданном тобою квадрате;");
		v.text("2) Сосредоточиться на экономике и получить целых 100 монет;");
		v.text("3) Вызвать авиаудар, который сотрёт с лица Земли всё в радиусе");
		v.text("1 квадрата от введённых тобой координат;");
		v.text("4) Передвинуть свои войска в любую свободную позицию (враг вряд ли");
		v.text("будет бить в одно и то же место подряд, хотя и может);");
		v.text("5) Узнать расположение какого-либо лагеря врага.");
		v.println("Введите что-нибудь для продолжения");
		s = inp.nextLine();
		v.clean();
		v.text("Мирные районы:");
		v.text("Если ты попадёшь в мирные здания, тебе придётся заплатить 50 монет");
		v.text("Враг не имеет жалости и совести, он будет бить по твоим жилым районам,");
		v.text("а сам прятаться радом с ними, будь аккуратнее, используя авиаудар.");
		v.text("Иногда точный выстрел спасёт множество жизней, в отличие от бездумных");
		v.text("бомбардировок.");
		v.text("На месте погибших войск или районов будут образовываться захоронения(+)");
		v.println("Введите что-нибудь для продолжения");
		s = inp.nextLine();
		v.clean();
		v.text("Условия победы и поражения:");
		v.text("Закончились деньги, не на что содержать армию - смерть");
		v.text("Нет войск, не кому оборонять твою территорию от врага - смерть");
		v.text("Закончились войска врага - победа");
		v.text("Ещё кое-что забыл:");
		v.text("Лёгкий бот стреляет за свой ход только 1 раз.");
		v.text("Лагеря невозможного бота же выпускают по две ракеты каждый.");
		v.text("Также они могут передвигать свои войска => разведданные актуальны не всегда.");
		v.println("Введите что-нибудь для окончания обучения");
		s = inp.nextLine();
		v.clean();
	}
}