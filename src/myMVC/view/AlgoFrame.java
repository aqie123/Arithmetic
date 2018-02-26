package myMVC.view;

import myMVC.model.MineSweeperData;
import myMVC.tools.AlgoVisHelper;

import javax.swing.*;
import java.awt.*;

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
    private MineSweeperData data;
    public void render(MineSweeperData data){
        this.data = data;
        repaint();      // 将所有控件重新刷新(也会清空画布)
    }

    // 具体绘制代码
    private class AlgoCanvas extends JPanel{
        public AlgoCanvas(){
            super(true);
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
            // todo 绘制雷区面板
            int w = canvasWidth/data.getM();
            int h = canvasHeight/data.getN();
            for (int i = 0;i < data.getN();i++){
                for(int j = 0;j < data.getM();j++){
                    // 用户点开,是雷，显示雷区图标,不是雷，显示周围雷数(雷数是0)
                    // 则显示凹进去方块
                    if(data.open[i][j]){
                        if(data.isMine(i, j)){
                            AlgoVisHelper.putImage(g2D, j*w, i*h,
                                    MineSweeperData.mineImageURL);
                        }else {
                            AlgoVisHelper.putImage(g2D, j * w, i * h,
                                    MineSweeperData.numberImageURL(data.getNumber(i, j)));
                        }
                    }else{
                        // 未点开,如果标记，显示旗子，未标记正常显示
                        if(data.flags[i][j]){
                            AlgoVisHelper.putImage(g2D, j*w, i*h,
                                    MineSweeperData.flagImageURL);
                        }
                        else{
                            AlgoVisHelper.putImage(g2D, j*w, i*h,
                                    MineSweeperData.blockImageURL);
                        }
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
