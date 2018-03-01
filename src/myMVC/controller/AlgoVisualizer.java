package myMVC.controller;

import myMVC.model.GameData;
import myMVC.model.Position;
import myMVC.tools.AlgoVisHelper;
import myMVC.view.AlgoFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AlgoVisualizer {
    private static int DELAY = 5;

    private GameData data;

    // 视图层
    private AlgoFrame frame;

    // todo 设置自定义变量
    private static int blockSide = 80;  // 每个单元格边长

    public AlgoVisualizer(String filename){
        // todo 初始化数据
        data = new GameData(filename);
        int screenWidth = data.getM() * blockSide;
        int screenHeight = data.getN() * blockSide;
        // 初始化视图frame
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Move the Box Solver",
                    screenWidth, screenHeight);
            // TODO: 根据情况决定是否加入键盘鼠标事件监听器
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 编写自动求解过程
    private void run(){
       setData(-1,-1);
       if(data.solve()){
           System.out.println("The game has a solution!");
       }else{
           System.out.println("The game does NOT have a solution.");
       }
       setData(-1,-1);
    }

    // 设置要绘制的数据,添加点击坐标
    private void setData(int clickx, int clicky){
        data.clickx = clickx;
        data.clicky = clicky;
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    // 添加键盘监听事件
    private class AlgoKeyListener extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent event){
        }
    }

    private Position clickPos1 = null;
    private Position clickPos2 = null;
    // 添加鼠标响应事件
    private class AlgoMouseListener extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent event){
            event.translatePoint(
                    -(int)(frame.getBounds().width - frame.getCanvasWidth()),
                    -(int)(frame.getBounds().height - frame.getCanvasHeight())
            );
            Point point = event.getPoint();

            int w = frame.getCanvasWidth() / data.getM();
            int h = frame.getCanvasHeight() / data.getN();

            int x = point.y / h;
            int y = point.x / w;

            if(SwingUtilities.isLeftMouseButton(event)){
                if(data.inArea(x,y)){
                    setData(x,y);
                    if(clickPos1 == null){
                        clickPos1 = new Position(x,y);
                    }else{
                        clickPos2 = new Position(x,y);
                        if(clickPos2.nextTo(clickPos1)){
                            data.getShowBoard().swap(clickPos1.getX(), clickPos1.getY(),
                                    clickPos2.getX(), clickPos2.getY());
                            data.getShowBoard().run();
                        }
                        clickPos1 = null;
                        clickPos2 = null;
                        setData(-1,-1);
                    }
                }else{
                    setData(-1,-1);
                    clickPos1 = null;
                    clickPos2 = null;
                }
            }
        }
    }
}
