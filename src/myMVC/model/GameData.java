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

    public boolean solve(){
        if(maxTurn <= 0){
            return false;
        }
        return solve(new Board(starterBoard),maxTurn);
    }
    // 下 右 左
    private static int d[][] = {{1,0},{0,1},{0,-1}};

    /**
     *
     * @param board  通过盘面board
     * @param turn   使用turn次move
     * @return       解决move the box
     * 成功解决,返回true,反之返回false
     */
    private boolean solve(Board board,int turn){
        if(board == null) {
            throw new IllegalArgumentException("board can not be null in solve function!");
        }

        if(turn == 0) {
            return board.isWin();
        }

        if(board.isWin()) {
            return true;
        }
        for(int x = 0;x < N;x++){
            for(int y = 0;y < M;y++){
                if(board.getData(x,y) != Board.EMPTY){
                    for (int i = 0;i < 3;i++){
                        int newX = x + d[i][0];
                        int newY = y + d[i][1];
                        if(inArea(newX,newY)){
                            String swapString = String.format("swap (%d, %d) and (%d, %d)",
                                    x, y, newX, newY);
                            Board nextBoard = new Board(board,board,swapString);
                            nextBoard.swap(x, y, newX, newY);
                            nextBoard.run();
                            // 回溯求解
                            if(solve(nextBoard, turn-1)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
