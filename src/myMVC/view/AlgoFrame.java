package myMVC.view;


import myMVC.model.Circle;
import mySwing.tools.AlgoVisHelper;

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
        System.out.println(getContentPane().getBounds());
    }

    public AlgoFrame(String title){
        this(title,1024,768);
    }

    public int getCanvasHeight() { return canvasHeight;}

    public int getCanvasWidth() { return canvasWidth;}

    private Circle[] circles;
    public void render(Circle[] circles){
        this.circles = circles;
        repaint();      // 将所有控件重新刷新(也会清空画布)
    }

    private class AlgoCanvas extends JPanel{// 具体绘制代码
        public AlgoCanvas(){
            super(true);
        }
        @Override   // 绘制组件
        public void paintComponent(Graphics g) {
            super.paintComponents(g);
            Graphics2D g2D = (Graphics2D)g;
            // drawCircle((Graphics2D) g);
            // 抗锯齿
            RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2D.addRenderingHints(hints);
            AlgoVisHelper.setStrokeWidth(g2D,5);
            AlgoVisHelper.setColor(g2D,Color.red);
            // drawCircleWithTool(g2D);
            for (Circle circle : circles){
                AlgoVisHelper.strokeCircle(g2D,circle.x,circle.y,circle.getR());
            }
        }

        // 通过工具类绘制圆
        private void drawCircleWithTool(Graphics2D g2D) {
            AlgoVisHelper.strokeCircle(g2D,100,100,200);
        }

        // 绘制一个圆
        private void drawCircle(Graphics2D g) {
            // g.drawOval(50,50,300,300);
            Graphics2D g2D = g;

            int strokeWidth = 10;
            g2D.setStroke(new BasicStroke(strokeWidth));
            g2D.setColor(Color.blue);
            Ellipse2D circle = new Ellipse2D.Double(50,50,300,300);  // 创建基本图形对象
            g2D.draw(circle);

            Ellipse2D solidCircle = new Ellipse2D.Double(60,60,280,280);
            g2D.fill(solidCircle);
        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
