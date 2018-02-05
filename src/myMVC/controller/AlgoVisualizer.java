package myMVC.controller;

import myMVC.model.Circle;
import myMVC.tools.AlgoVisHelper;
import myMVC.view.AlgoFrame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public abstract class AlgoVisualizer {
    // 数据 存储所有circle信息
    private Circle[] circles;
    // 视图层
    private AlgoFrame frame;
    // todo 设置自定义变量

    public AlgoVisualizer(){
        this(500,500,10);
    }

    // 对数据和视图进行初始化
    public AlgoVisualizer(int screenWidth,int screenHeight,int N){
        EventQueue.invokeLater(() -> {
            new Thread(()->{
                run();
            }).start();
        });
    }

    // 小球运动逻辑
    public void run(){}

    // todo 添加键盘监听事件
    public class AlgoKeyListener extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent e) {
            // todo 键盘释放事件
        }
    }

    // todo 添加鼠标响应事件
    public class AlgoMouseListener extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
           // todo 鼠标按压事件
        }
    }
}
