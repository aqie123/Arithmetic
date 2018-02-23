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
    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N){
        this(sceneWidth, sceneHeight, N, QuickSortData.Type.Default);
    }
    // 近乎有序数组排序
    public AlgoVisualizer(int screenWidth,int screenHeight,int N,QuickSortData.Type dataType){
        // todo 初始化数据
        data = new QuickSortData(N,screenHeight, dataType);
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
        // quickSort(0,data.N() - 1);
        // quickSort2Ways(0,data.N() - 1);
        quickSort3Ways(0,data.N() - 1);
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
        // int p = partition(l,r);
        int p = partition2(l,r);
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

    // 近乎有序数组排序
    private int partition2(int l ,int r){
        int p = (int)(Math.random()*(r-l+1) + l);
        setData(l,r,-1,p,-1);
        data.swap(l,p);
        int v = data.get(l);
        setData(l,r,-1,l,-1);

        int j = l;
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

    // 双路快排
    private void quickSort2Ways(int l,int r){
        if(l > r){
            return;
        }
        if(l == r){
            setData2(l,r,l,-1,-1,-1);
        }
        setData2(l, r, -1, -1, -1, -1);

        int p = partition3(l, r);
        quickSort2Ways(l, p-1 );
        quickSort2Ways(p+1, r);
    }

    // 双路快排
    private int partition3(int l,int r){
        int p = (int)(Math.random() * (r-l+1)) + l;
        setData2(l,r,-1,p,-1,-1);
        data.swap(l,p);
        int v = data.get(l);
        setData2(l,r,-1,l,-1,-1);
        // arr[l+1...i) <= v; arr(j...r] >= v
        int i = l+1, j = r;
        setData2(l, r, -1, l, i, j);
        while( true ){
            while( i <= r && data.get(i) < v ){
                i ++;
                setData2(l, r, -1, l, i, j);
            }

            while( j >= l+1 && data.get(j) > v ){
                j --;
                setData2(l, r, -1, l, i, j);
            }

            if( i > j )
                break;

            data.swap( i, j );
            i ++;
            j --;
            setData2(l, r, -1, l, i, j);
        }

        data.swap(l, j);
        setData2(l, r, j, -1, -1, -1);

        return j;
    }

    // 双路快排
    private void setData2(int l,int r,int fixedPivot,int curPivot,int curL,int curR){
        data.l = l;
        data.r = r;
        if(fixedPivot != -1)
            data.fixedPivots[fixedPivot] = true;
        data.curPivot = curPivot;
        data.curL = curL;
        data.curR = curR;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    private void quickSort3Ways(int l, int r){

        if( l > r )
            return;

        if( l == r ) {
            setData3(l, r, l, -1, -1, -1);
            return;
        }

        setData3(l, r, -1, -1, -1, -1);

        // 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        int p = (int)(Math.random()*(r-l+1)) + l;
        setData3(l, r, -1, p, -1, -1);

        data.swap(l, p);
        int v = data.get(l);
        setData3(l, r, -1, l, -1, -1);

        int lt = l;     // arr[l+1...lt] < v
        int gt = r + 1; // arr[gt...r] > v
        int i = l+1;    // arr[lt+1...i) == v
        setData3(l, r, -1, l, lt, gt);

        while( i < gt ){
            if( data.get(i) < v ){
                data.swap( i, lt+1);
                i ++;
                lt ++;
            }
            else if( data.get(i) > v ){
                data.swap( i, gt-1);
                gt --;
            }
            else // arr[i] == v
                i ++;

            setData3(l, r, -1, l, i, gt);
        }

        data.swap( l, lt );
        setData3(l, r, lt, -1, -1, -1);

        quickSort3Ways(l, lt-1 );
        quickSort3Ways(gt, r);
    }

    private void setData3(int l, int r, int fixedPivot, int curPivot, int curL, int curR){
        data.l = l;
        data.r = r;
        if(fixedPivot != -1){
            data.fixedPivots[fixedPivot] = true;
            int i = fixedPivot;
            while(i < data.N() && data.get(i) == data.get(fixedPivot)){
                data.fixedPivots[i] = true;
                i ++;
            }
        }
        data.curPivot = curPivot;
        data.curL = curL;
        data.curR = curR;

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
