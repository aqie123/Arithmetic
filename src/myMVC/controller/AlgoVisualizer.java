package myMVC.controller;

import myMVC.model.Circle;
import myMVC.model.MergeSortData;
import myMVC.model.SelectionSortData;
import myMVC.model.SortData;
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

    private MergeSortData data;

    // 视图层
    private AlgoFrame frame;
    // todo 设置自定义变量

    // 归并排序
    public AlgoVisualizer(int screenWidth,int screenHeight,int N){
        // todo 初始化数据
        data = new MergeSortData(N,screenHeight);
        // 初始化视图frame
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Selection Sort Visualization", screenWidth, screenHeight);
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
        setData(-1,-1,-1);
        // 方式一：自顶向下
        // mergeSort(0,data.N()-1);
        // 方式二：自下向上
        for(int sz = 1;sz < data.N(); sz *= 2){
            for(int i = 0;i < data.N() - sz;i += sz+sz){
                // 对 arr[i...i+sz-1] 和 arr[i+sz...i+2*sz-1] 进行归并
                merge(i,i+sz-1,Math.min(i+sz+sz-1,data.N()-1));
            }
        }
        setData(0,data.N()-1,data.N()-1);
    }

    private void mergeSort(int l,int r){
        if(l >= r){
            return;
        }
        setData(l,r,-1);
        int mid = (l + r)/2;
        mergeSort(l,mid);
        mergeSort(mid+1,r);
        merge(l,mid,r);
    }

    private void merge(int l,int mid,int r){
        int[] aux = Arrays.copyOfRange(data.numbers,l,r+1);
        // 初始化，i指向左半部分的起始索引位置l；j指向右半部分起始索引位置mid+1
        int i = l, j = mid + 1;
        for(int k = l;k <= r; k++){
            if(i > mid){
                data.numbers[k] = aux[j-l];
                j++;
            }else if(j > r){   // 如果右半部分元素已经全部处理完毕
                data.numbers[k] = aux[i - l];
                i++;
            }else if(aux[i-l] < aux[j-l]){  // 左半部分所指元素 < 右半部分所指元素
                data.numbers[k] = aux[i-l];
                i++;
            }else{
                data.numbers[k] = aux[j-l];
                j++;
            }
            setData(l, r, k);
        }
    }

    private void setData(int l, int r, int mergeIndex){
        data.l = l;
        data.r = r;
        data.mergeIndex = mergeIndex;
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
