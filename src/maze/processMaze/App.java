package maze.processMaze;

public class App {
    public static void main(String[] args) {
        String mazeFile = "src/maze/processMaze/maze_101_101.txt";
        MazeData data = new MazeData(mazeFile);
        data.print();
    }
}
