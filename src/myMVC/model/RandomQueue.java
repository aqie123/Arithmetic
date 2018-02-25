package myMVC.model;

import java.util.ArrayList;
import java.util.LinkedList;

public class RandomQueue<E> {
    private LinkedList<E> queue;
    public RandomQueue(){
        queue = new LinkedList<>();  // 链表
    }

    public void add(E e){
        if(Math.random() < 0.5)
            queue.addFirst(e);
        else
            queue.addLast(e);
    }

    public E remove(){
        if(queue.size() == 0){
            throw new IllegalArgumentException("There's no " +
                    "element to remove in Random Queue");
        }
        if(Math.random() < 0.5)
            return queue.removeFirst();
        else
            return queue.removeLast();
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
