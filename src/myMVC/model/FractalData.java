package myMVC.model;

// Vicsek Gractal 分形图绘制
public class FractalData {
    public int getDepth() {
        return depth;
    }

    public FractalData(int depth) {
        this.depth = depth;
    }

    private  int depth;

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
