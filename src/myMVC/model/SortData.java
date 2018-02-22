package myMVC.model;

public interface SortData {
    // 有序的索引, [0,orderIndex) 有序的,前闭后开 (插入)
    public int orderIndex = -1;
    public int currentIndex = -1;

    // 当前找到的最小值索引 (选择)
    public int currentMinIndex = -1;

    // 当前正在比较的元素索引
    public int currentCompareIndex = -1;
    public int N();
    public int get(int index);
    public void swap(int i, int j);
}
