package ru.fields;
import java.util.Random;
public class SudokuField{
	public int[][] field = new int[9][9];
	public SudokuField(int[][] field1){
		for (int i = 0; i < 9; i ++){
			for (int j = 0; j < 9; j ++){
				field[i][j] = field1[i][j]*1;
			}
		}
	}

	public int[] possible(int i, int j){
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
        return isGoodFIeld1();
	}

	private boolean isGoodFIeld1(){
		SudokuField f = new SudokuField(field);
		return f.isGoodFIeld();
	}
	private boolean isGoodFIeld(){
		try{
			for (int i = 0; i < 9; i++){
        		for (int j = 0; j < 9; j++){
        			int buf = field[i][j]*1;
        			field[i][j] = 0;
        			int[] pos = possible(i, j);
        			if (buf != pos[0] || pos.length != 1){
        				return false;
        			}
        			field[i][j] = buf;
        		}
        	}
    	} catch(Exception e){
    		return false;
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
}