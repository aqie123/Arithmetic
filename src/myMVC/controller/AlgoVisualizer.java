package myMVC.controller;

import myMVC.model.*;
import myMVC.tools.AlgoVisHelper;
import myMVC.view.AlgoFrame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;


public class AlgoVisualizer {
    private static int DELAY = 30;

    private QuickSortData data;

    // 视图层
    private AlgoFrame frame;
    // todo 设置自定义变量

    // 归并排序
    public AlgoVisualizer(int screenWidth,int screenHeight,int N){
        // todo 初始化数据
        data = new QuickSortData(N,screenHeight);
        // 初始化视图frame
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Quick Sort Visualization", screenWidth, screenHeight);
            // TODO: 根据情况决定是否加入键盘鼠标事件监听器
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 归并排序
    private void run(){
        setData(-1,-1,-1,-1,-1);
        quickSort(0,data.N() - 1);
        setData(-1,-1,-1,-1,-1);
    }

    private void quickSort(int l,int r){
        if(l > r){
            return;
        }
        if(l == r){
            setData(l,r,l,-1,-1);
            return;
        }
        setData(l,r,-1,-1,-1);
        int p = partition(l,r);
        quickSort(l,p-1);
        quickSort(p+1,r);
    }

    private int partition(int l,int r){
        int v = data.get(l);
        setData(l,r,-1,l,-1);
        int j = l;  // arr[l+1...j] < v ; arr[j+1...i) > v
        for(int i = l + 1;i <= r;i++){
            setData(l,r,-1,l,i);
            if(data.get(i) < v){
                j++;
                data.swap(j,i);
                setData(l,r,-1,l,i);
            }
        }
        data.swap(l,j);
        setData(l,r,j,-1,-1);
        return j;
    }

    private void setData(int l, int r, int fixedPivot,int curPivot,int curElement){
        data.l = l;
        data.r = r;
        if(fixedPivot != -1){
            data.fixedPivots[fixedPivot] = true;
        }
        data.curPivot = curPivot;
        data.curElement = curElement;
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
