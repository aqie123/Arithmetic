package myMVC.app;

import myMVC.controller.AlgoVisualizer;
import myMVC.controller.MyVisualizer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml",getClass());
    public static void main(String[] args) {
        App app = new App();
        app.test();
    }

    private void test() {
        int screenWidth = 800;
        int screenHeight = 800;
        int N = 10;
        AlgoVisualizer vis = (MyVisualizer)ac.getBean("myVisualizer");
    }
}
