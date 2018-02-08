package myMVC.view;


import myMVC.model.Circle;
import myMVC.model.MonteCarloPiData;
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
    private MonteCarloPiData data;
    public void render(MonteCarloPiData data){
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
            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2D.addRenderingHints(hints);
            // todo 绘制所要的数据
            // 绘制圆形和正方形
            AlgoVisHelper.setStrokeWidth(g2D,3);
            AlgoVisHelper.setColor(g2D,AlgoVisHelper.Blue);
            Circle circle = data.getCircle();
            AlgoVisHelper.strokeCircle(g2D,circle.getX(),circle.getY(),circle.getR());

            for(int i = 0; i < data.getPointsNumber(); i++){
                Point p = data.getPoint(i);
                if(circle.contain(p)){
                    AlgoVisHelper.setColor(g2D,AlgoVisHelper.Red);
                }else {
                    AlgoVisHelper.setColor(g2D, AlgoVisHelper.Green);
                }
                // 绘制点
                AlgoVisHelper.fillCircle(g2D,p.x,p.y,3);
            }
        }


        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
