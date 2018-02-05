package myMVC.view;

import javax.swing.*;
import java.awt.*;

// 具体绘制代码
public abstract class AlgoCanvas extends JPanel {
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
        graphData(g2D);
    }
    public abstract void graphData(Graphics2D g2D);

    /*@Override
    public Dimension getPreferredSize(){
        return new Dimension(canvasWidth, canvasHeight);
    }*/
}
