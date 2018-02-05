package myMVC.view;


import myMVC.model.Circle;
import myMVC.tools.AlgoVisHelper;


import javax.annotation.Resource;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public abstract class AlgoFrame extends JFrame{
    private int canvasWidth;
    private int canvasHeight;
    @Resource
    private AlgoCanvas myCanvas;
    public AlgoFrame(){
        this("默认窗口",1024,768);
    }
    public AlgoFrame(String title, int canvasWidth, int canvasHeight){
        super(title);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        // myCanvas.setPreferredSize(new Dimension(canvasWidth,canvasHeight));
        setContentPane(myCanvas); // 设置窗口内容面板
        pack(); // 根据窗口内容调整窗口大小
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public AlgoFrame(String title){
        this(title,1024,768);
    }

    public int getCanvasHeight() { return canvasHeight;}

    public int getCanvasWidth() { return canvasWidth;}

    // 设置自己的数据
    private Object object;
    public void render(Object object) {
        this.object = object;
        repaint();      // 将所有控件重新刷新(也会清空画布)
    }

}
