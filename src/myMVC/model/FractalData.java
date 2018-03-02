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

    private double splitAngle;
    public FractalData(int depth,double splitAngle){
        this.depth = depth;
        this.splitAngle = splitAngle;
    }
    public void setDepth(int depth) {
        this.depth = depth;
    }

    public double getSplitAngle() {
        return splitAngle;
    }

    public void setSplitAngle(double splitAngle) {
        this.splitAngle = splitAngle;
    }
}
