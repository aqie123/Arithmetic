package myMVC.view;

import myMVC.model.Circle;
import myMVC.tools.AlgoVisHelper;
import org.springframework.stereotype.Repository;

import java.awt.*;

@Repository
public class MyCanvas extends AlgoCanvas {
    private Object object;
    @Override
    public void graphData(Graphics2D g2D) {
        AlgoVisHelper.setStrokeWidth(g2D,5);
        AlgoVisHelper.setColor(g2D,Color.red);
        for (Circle circle : (Circle[]) object){
            if(circle.isFilled){
                AlgoVisHelper.fillCircle(g2D,circle.x,circle.y,circle.getR());
            }else{
                AlgoVisHelper.strokeCircle(g2D,circle.x,circle.y,circle.getR());
            }
        }
    }
}
