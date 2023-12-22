package ru.fields;
import java.util.Random;
public class SudokuFieldForSolving{
	public int[][] field = new int[9][9];
	public SudokuFieldForSolving(int n){
		solve();
		Random r = new Random();
		for (int i = 0; i < n; i++){
			int x;
			int y;
			do{
				x = r.nextInt(9);
				y = r.nextInt(9);
			}while(field[x][y]==0);
			field[x][y] = 0;
		}
	}
	public boolean isFull(){
		for (int i = 0; i < 9; i++){
        	for (int j = 0; j < 9; j++){
        		if (field[i][j] == 0){
        			return false;
        		}
        	}
    	}
    	return true;
	}
	private int[] possible(int i, int j){
		int[] q = new int[9];
		for (int w = 1; w <= 9; w++){
			q[w-1] = w;
		}
		for (int j1 = 0; j1 < 9; j1++){
			if (field[i][j1]!=0){
				q[field[i][j1]-1] = 0;
			}
		}
		for (int i1 = 0; i1 < 9; i1++){
			if (field[i1][j]!=0){
				q[field[i1][j]-1] = 0;
			}
		}
		for (int i1 = 0; i1 < 3; i1 ++){
			for (int j1 = 0; j1 < 3; j1 ++){
				int zn = field[3*(i/3)+i1][3*(j/3)+j1];
				if (zn!=0){
					q[zn-1] = 0;
				}
			}
		}
		int c = 0;
		for (int i1 = 0; i1 < 9; i1++){
			if (q[i1]!=0){
				c++;
			}
		}
		int[] qout = new int[c];
		c = 0;
		for (int i1 = 0; i1 < 9; i1++){
			if (q[i1]!=0){
				qout[c] = q[i1];
				c++;
			}
		}
		return qout;
	}

	public boolean solve(){
		for (int i = 0; i < 9; i++){
        	for (int j = 0; j < 9; j++){
        		if (field[i][j] == 0){
        			int[] mpos = possible(i, j);
        			shuffle(mpos);
        			for (int s:mpos){
        				field[i][j] = s;
        				if (solve()){
        					return true;
        				}
        				field[i][j] = 0;
        			}
        			return false;
        		}
        	}
        }
        return true;
	}
	private void shuffle(int[] m) {
        Random r = new Random();
        for (int i = m.length - 1; i > 0; i--) {
            int index = r.nextInt(i + 1);
            int buf = m[index];
            m[index] = m[i];
            m[i] = buf;
        }
    }
    
    public boolean paste(int i, int j, int v){
    	field[i][j] = v;
    	SudokuField f = new SudokuField(field);
    	if (f.solve()){
    		return true;
    	}
    	field[i][j] = 0;
    	return false;
    }
    public void hint(int i, int j){
    	int[] pos = possible(i, j);
    	shuffle(pos);
    	for (int p:pos){
    		field[i][j] = p;
    		SudokuField f = new SudokuField(field);
    		if (f.solve()){
    			break;
    		}
    	}
    }
}