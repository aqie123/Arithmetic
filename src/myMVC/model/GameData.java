package myMVC.model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameData {
    private int maxTurn;
    private int N, M;
    private Board starterBoard;
    private Board showBoard;

    public GameData(String filename){
        if(filename == null) {
            throw new IllegalArgumentException("Filename cannot be null!");
        }
        Scanner scanner = null;
        try {
            FileInputStream fis = new FileInputStream(filename);
            scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");
            String turnline = scanner.nextLine();
            this.maxTurn = Integer.parseInt(turnline);
            ArrayList<String> lines = new ArrayList<>();
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                lines.add(line);
            }
            starterBoard = new Board(lines.toArray(new String[lines.size()]));
            this.N = starterBoard.getN();
            this.M = starterBoard.getM();
            starterBoard.print();
            showBoard = new Board(starterBoard);        // 根据现有面板生成新面板
        }catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            if(scanner != null){
                scanner.close();
            }
        }
    }

    public int getN(){ return N; }
    public int getM(){ return M; }
    public Board getShowBoard(){ return showBoard;}

    public boolean inArea(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
