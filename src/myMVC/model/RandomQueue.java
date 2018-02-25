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
        int randIndex = (int)(Math.random()*queue.size());
        E randomElement = queue.get(randIndex);
        //
        queue.set(randIndex,queue.get(queue.size() - 1));
        queue.remove(queue.size() - 1);
        return randomElement;
    }

    public int size(){
        return queue.size();
    }

    public boolean empty(){
        return size() == 0;
    }

}
