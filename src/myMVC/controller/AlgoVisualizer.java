package myMVC.controller;

import myMVC.model.Circle;
import myMVC.model.SelectionSortData;
import myMVC.tools.AlgoVisHelper;
import myMVC.view.AlgoFrame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AlgoVisualizer {
    private static int DELAY = 30;
    // 数据
    private SelectionSortData data;
    // 视图层
    private AlgoFrame frame;
    // todo 设置自定义变量


    // 对数据和视图进行初始化
    public AlgoVisualizer(int screenWidth,int screenHeight,int N){
        this(screenWidth,screenHeight,N,SelectionSortData.Type.Default);
    }

    public AlgoVisualizer(int screenWidth,int screenHeight,int N,SelectionSortData.Type dataType){
        // todo 初始化数据
        data = new SelectionSortData(N,screenHeight,dataType);
        // 初始化视图frame
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Selection Sort Visualization", screenWidth, screenHeight);
            // TODO: 根据情况决定是否加入键盘鼠标事件监听器
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                // selectRun();
                insertRun();
            }).start();
        });
    }

    // 选择排序逻辑
    private void selectRun() {
        setData(0, -1, -1);
        for(int i = 0;i < data.N()-1;i++){
            int minIndex = i;
            setData(i, -1, minIndex);
            // 当前比较索引 j
            for(int j = i + 1;j < data.N();j++){
                setData(i,j,minIndex);
                if(data.get(j) < data.get(minIndex)){
                    minIndex = j;
                    setData(i,j,minIndex);
                }
            }
            data.swap(i,minIndex);
            setData(i+1,-1,-1);
        }
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    /**
     * 插入排序逻辑
     */
    private void insertRun(){
        setInsertData(0, -1);
        for(int i = 0; i < data.N(); i++){
            setInsertData(i, i);
            // 比前面元素小,和前面元素交换位置
            for (int j = i;j > 0 && data.get(j) < data.get(j - 1);j--){
                data.swap(j,j-1);
                setInsertData(i+1,j - 1);
            }
        }
        setInsertData(data.N(), -1);
    }

    /**
     * 选择排序
     * @param orderIndex    (顺位索引)
     * @param currentCompareIndex   (当前比较的索引)
     * @param currentMinIndex   (当前最小值索引)
     */
    private void setData(int orderIndex,int currentCompareIndex,int currentMinIndex){
        data.orderIndex = orderIndex;
        data.currentCompareIndex = currentCompareIndex;
        data.currentMinIndex = currentMinIndex;
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    private void setInsertData(int orderIndex,int currentIndex){
        data.orderIndex = orderIndex;
        data.currentIndex = currentIndex;
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    // 添加键盘监听事件
    private class AlgoKeyListener extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent e) {
            // todo 键盘释放事件
        }
    }

    // 添加鼠标响应事件
    private class AlgoMouseListener extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
           // todo 鼠标按压事件
        }
    }
}
