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
            drawCircle(g2D);
        }

        private void drawCircle(Graphics2D g2D) {
            AlgoVisHelper.setStrokeWidth(g2D,5);
            AlgoVisHelper.setColor(g2D,Color.red);
            for (Circle circle : (Circle[]) object){
                if(circle.isFilled){
                    AlgoVisHelper.fillCircle(g2D,circle.x,circle.y,circle.getR());
                }else{
                    AlgoVisHelper.strokeCircle(g2D,circle.x,circle.y,circle.getR());
                }
            }
        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
