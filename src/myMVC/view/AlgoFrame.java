package myMVC.view;


import myMVC.model.Circle;
import myMVC.tools.AlgoVisHelper;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

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
    private Object object;
    public void render(Object object){
        this.object = object;
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
            g2D.addRenderingHints(hints);
            // todo 绘制所要的数据

            int[] money = (int[])object;
            // 绘制实心矩形
            int w = canvasWidth / money.length;     // 每个数据宽度,固定
            for(int i = 0;i<money.length;i++){
                // +1 是每个格子中间距离  (w-1)矩形实际宽度,留出空位
                if(money[i] > 0){
                    AlgoVisHelper.setColor(g2D,AlgoVisHelper.Blue);
                    AlgoVisHelper.fillRectangle(g2D,
                            i*w+1, canvasHeight/2 - money[i], w-1, money[i]);
                }else{      // 负债
                    AlgoVisHelper.setColor(g2D,AlgoVisHelper.Red);
                    AlgoVisHelper.fillRectangle(g2D,
                            i*w+1, canvasHeight/2, w-1, -money[i]);
                }

            }
        }



        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
