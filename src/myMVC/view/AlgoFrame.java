package myMVC.view;


import myMVC.model.Circle;
import myMVC.model.SelectionSortData;
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
    private SelectionSortData data;
    public void render(SelectionSortData data){
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
            g2D.addRenderingHints(hints);
            // todo 绘制所要的数据
            // selectionPaint(g2D);
            int w = canvasWidth/data.N();
            for(int i = 0; i< data.N();i++){
                if(i < data.orderIndex){
                    AlgoVisHelper.setColor(g2D,AlgoVisHelper.Red);
                }else {
                    AlgoVisHelper.setColor(g2D,AlgoVisHelper.Grey);
                }
                if(i == data.currentIndex){
                    AlgoVisHelper.setColor(g2D,AlgoVisHelper.LightBlue);
                }
                AlgoVisHelper.fillRectangle(g2D,i * w, canvasHeight - data.get(i),
                        w-1,data.get(i));
            }
        }

        // 选择排序绘制
        private void selectionPaint(Graphics2D g2D) {
            int w = canvasWidth/data.N();  // 每个数据的宽度
            for (int i = 0;i < data.N();i++){
                if(i < data.orderIndex){    // 已经排序索引(前闭后开)
                    AlgoVisHelper.setColor(g2D, AlgoVisHelper.Red);
                }else {
                    AlgoVisHelper.setColor(g2D, AlgoVisHelper.Grey);
                }
                if(i == data.currentCompareIndex) {
                    AlgoVisHelper.setColor(g2D, AlgoVisHelper.LightBlue);
                }
                if(i == data.currentMinIndex) {
                    AlgoVisHelper.setColor(g2D, AlgoVisHelper.Indigo);
                }
                AlgoVisHelper.fillRectangle(g2D,i*w,canvasHeight-data.get(i),w - 1,data.get(i));
            }
        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
