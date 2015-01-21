package engine;


public class EngineMain {

 static Engine e = new Engine();

    public static void main(String[] args) {

    	int[][] e  = Engine.Grille();
    	for(int i = 0;i<9;i++)
    	{
    		for(int j = 0;j<9;j++)
        	{
        		System.out.print(" "+e[i][j]);
        	}
    		System.out.println();
    		
    	}

    	

    }
}