package myMVC.view;

import myMVC.model.FractalData;
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
    private FractalData data;
    public void render(FractalData data){
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

            // todo  draw Koch Snowflake
            drawFractal(g2D, 0, canvasHeight-3, canvasWidth, 0, 0);
        }

        private void drawFractal(Graphics2D g, double x1, double y1, double side, double angle, int depth){

            if(side <= 0)
                return;

            if( depth == data.getDepth()){
                double x2 = x1 + side * Math.cos(angle*Math.PI/180.0);
                double y2 = y1 - side * Math.sin(angle*Math.PI/180.0);
                AlgoVisHelper.setColor(g, AlgoVisHelper.Indigo);
                AlgoVisHelper.drawLine(g, x1, y1, x2, y2);
                return;
            }

            double side_3 = side / 3;

            double x2 = x1 + side_3 * Math.cos(angle*Math.PI/180.0);
            double y2 = y1 - side_3 * Math.sin(angle*Math.PI/180.0);
            drawFractal(g, x1, y1, side_3, angle, depth+1);

            double x3 = x2 + side_3 * Math.cos((angle+60.0)*Math.PI/180.0);
            double y3 = y2 - side_3 * Math.sin((angle+60.0)*Math.PI/180.0);
            drawFractal(g, x2, y2, side_3, angle+60.0, depth+1);

            double x4 = x3 + side_3 * Math.cos((angle-60.0)*Math.PI/180.0);
            double y4 = y3 - side_3 * Math.sin((angle-60.0)*Math.PI/180.0);
            drawFractal(g, x3, y3, side_3, angle-60.0, depth+1);

            double x5 = x4 + side_3 * Math.cos(angle*Math.PI/180.0);
            double y5 = y4 - side_3 * Math.sin(angle*Math.PI/180.0);
            drawFractal(g, x4, y4, side_3, angle, depth+1);

        }


        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
