package myMVC.model;

import java.util.ArrayList;

public class RandomQueue<E> {
    private ArrayList<E> queue;
    public RandomQueue(){
        queue = new ArrayList<>();  // ArrayList就是动态数组
    }

    public void add(E e){
        queue.add(e);
    }

    public E remove(){
        if(queue.size() == 0){
            throw new IllegalArgumentException("There's no " +
                    "element to remove in Random Queue");
        }
        // [0-queue.size())
        int randIndex = (int)(Math.random()*queue.size());
        E randomElement = queue.get(randIndex);
        // 将最后一个元素赋给随机索引的位置
        queue.set(randIndex,queue.get(queue.size() - 1));
        // 删除最后一个元素
        queue.remove(queue.size() - 1);
        return randomElement;
    }

    // 随机队列剩余元素
    public int size(){
        return queue.size();
    }

    // 随机队列是否为空
    public boolean empty(){
        return size() == 0;
    }

}
