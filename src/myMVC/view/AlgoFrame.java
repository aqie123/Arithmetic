package myMVC.view;

import myMVC.model.Board;
import myMVC.model.GameData;
import myMVC.tools.AlgoVisHelper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class AlgoFrame extends JFrame{
    private int canvasWidth;
    private int canvasHeight;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight){
        super(title);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        AlgoCanvas canvas = new AlgoCanvas();
        canvas.setPreferredSize(new Dimension(canvasWidth,canvasHeight));
        setContentPane(canvas); // 设置窗口内容面板
        pack(); // 根据窗口内容调整窗口大小
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        // System.out.println(getContentPane().getBounds());
    }

    public AlgoFrame(String title){
        this(title,1024,768);
    }

    public int getCanvasHeight() { return canvasHeight;}

    public int getCanvasWidth() { return canvasWidth;}

    // todo 设置自己的数据
    private GameData data;
    public void render(GameData data){
        this.data = data;
        repaint();      // 将所有控件重新刷新(也会清空画布)
    }

    // 具体绘制代码
    private class AlgoCanvas extends JPanel{
        private ArrayList<Color> colorList;
        private HashMap<Character,Color> colorMap;

        public AlgoCanvas(){
            super(true);
            colorList = new ArrayList<Color>();
            colorList.add(AlgoVisHelper.Red);
            colorList.add(AlgoVisHelper.Purple);
            colorList.add(AlgoVisHelper.Blue);
            colorList.add(AlgoVisHelper.Teal);
            colorList.add(AlgoVisHelper.LightGreen);
            colorList.add(AlgoVisHelper.Lime);
            colorList.add(AlgoVisHelper.Amber);
            colorList.add(AlgoVisHelper.DeepOrange);
            colorList.add(AlgoVisHelper.Brown);
            colorList.add(AlgoVisHelper.BlueGrey);

            colorMap = new HashMap<>();
        }
        @Override   // 绘制组件
        public void paintComponent(Graphics g) {
            super.paintComponents(g);
            Graphics2D g2D = (Graphics2D)g;
            // 抗锯齿
            RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            hints.put(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            g2D.addRenderingHints(hints);
            // todo paint board
            int w = canvasWidth/data.getM();    // 宽
            int h = canvasHeight/data.getN();   // 高

            Board showBoard = data.getShowBoard();
            for (int i = 0;i < showBoard.getN();i++){
                for(int j = 0;j < showBoard.getM();j++){
                    char c = showBoard.getData(i,j);

                    if(c != Board.EMPTY){
                        // 新添加字符不对应某一个颜色，使其对应一个颜色
                        if(!colorMap.containsKey(c)){
                            int sz = colorMap.size();
                            colorMap.put(c,colorList.get(sz));
                        }
                        Color color = colorMap.get(c);
                        AlgoVisHelper.setColor(g2D,color);
                        AlgoVisHelper.fillRectangle(g2D,j*h+2,
                                i*w+2,w-4,h-4);

                        // 窗口绘制，添加文字坐标
                        AlgoVisHelper.setColor(g2D, AlgoVisHelper.White);
                        String text = String.format("( %d , %d )", i, j);
                        AlgoVisHelper.drawText(g2D, text, j*h + h/2, i*w + w/2);
                    }
                    if(i == data.clickx && j == data.clicky){
                        AlgoVisHelper.setColor(g2D,AlgoVisHelper.LightBlue);
                        AlgoVisHelper.setStrokeWidth(g2D,4);
                        AlgoVisHelper.strokeRectangle(g2D, j * h + 2, i * w + 2,
                                w - 4, h - 4);
                    }

                }
            }
        }


        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
