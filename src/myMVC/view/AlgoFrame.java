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
            // todo 绘制分形图

            drawSierpinskiTriangle(g2D,0,canvasHeight,canvasWidth,0);
        }


        /**
         * 绘制三角形 逆时针 A,B,C
         * @param g
         * @param Ax        A横坐标
         * @param Ay        A纵坐标
         * @param side      A边长
         * @param depth     递归深度
         */
        private void drawSierpinskiTriangle(Graphics2D g,int Ax,int Ay,int side,int depth){
            // 递归到底
            if(side <= 1){
                AlgoVisHelper.setColor(g, AlgoVisHelper.Indigo);
                AlgoVisHelper.fillRectangle(g, Ax, Ay, 1, 1);
                return;
            }

            int Bx = Ax + side;
            int By = Ay;
            int h = (int)(Math.sin(60.0*Math.PI/180.0) * side);
            int Cx = Ax + side/2;
            int Cy = Ay - h;

            // 递归到底,当前递归深度达到用户指定递归深度
            if(depth == data.getDepth()){
                AlgoVisHelper.setColor(g, AlgoVisHelper.Indigo);
                AlgoVisHelper.fillTriangle(g, Ax, Ay, Bx, By, Cx, Cy);
                return;
            }

            int AB_centerx = (Ax + Bx)/2;
            int AB_centery = (Ay + By)/2;

            int AC_centerx = (Ax+Cx)/2;
            int AC_centery = (Ay+Cy)/2;

            int BC_centerx = (Bx+Cx)/2;
            int BC_centery = (By+Cy)/2;

            drawSierpinskiTriangle(g, Ax, Ay, side/2, depth+1);
            drawSierpinskiTriangle(g, AC_centerx, AC_centery, side/2, depth+1);
            drawSierpinskiTriangle(g, AB_centerx, AB_centery, side/2, depth+1);

        }


        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
