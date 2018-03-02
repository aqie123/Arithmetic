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
            // drawFractal(g2D,0,0,canvasWidth,canvasHeight,0);
            drawSierpinski(g2D,0,0,canvasWidth,canvasHeight,0);
        }

        // Vicsek Gractal 分形
        private void drawFractal(Graphics2D g,int x,int y,int w,int h,int depth){
            int w_3 = w/3;
            int h_3 = h/3;

            if(w_3 <= 0 || h_3 <= 0){
                AlgoVisHelper.setColor(g, AlgoVisHelper.Indigo);
                AlgoVisHelper.fillRectangle(g, x, y, 1, 1);
                return;
            }
            if(depth == data.getDepth()){
                AlgoVisHelper.setColor(g,AlgoVisHelper.Indigo);
                AlgoVisHelper.fillRectangle(g,x,y,w,h);
                return;
            }

            drawFractal(g,x,y,w_3,h_3,depth + 1);
            drawFractal(g,x+2*w_3,y,w_3,h_3,depth + 1);
            drawFractal(g,x+w_3,y+w_3,w_3,h_3,depth + 1);
            drawFractal(g,x,y+2*w_3,w_3,h_3,depth + 1);
            drawFractal(g,x+2*w_3,y+2*w_3,w_3,h_3,depth + 1);
        }

        // Sierpinski 分形绘制
        private void drawSierpinski(Graphics2D g,int x,int y,int w,int h,int depth){
            int w_3 = w/3;
            int h_3 = h/3;

            if(w_3 <= 0 || h_3 <= 0){
                return;
            }
            // 分形到底,绘制中间矩形
            if(depth == data.getDepth()){
                AlgoVisHelper.setColor(g,AlgoVisHelper.Indigo);
                AlgoVisHelper.fillRectangle(g,x + w_3,y + w_3,w_3,h_3);
                return;
            }

            /**
             * 中间的格子填实
             * 其他递归调用绘制过程
             */
            for(int i = 0; i < 3;i++){
                for(int j = 0; j < 3;j++){
                    if(i == 1 && j == 1){
                        AlgoVisHelper.setColor(g,AlgoVisHelper.Indigo);
                        AlgoVisHelper.fillRectangle(g,x + w_3,y + w_3,w_3,h_3);
                    }else{
                        drawSierpinski(g,x+i*w_3,y+j*h_3,w_3,h_3,depth+1);
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
