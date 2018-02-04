一：Swing
    1.抗锯齿
    2.双缓存:AlgoCanvas extends JPanel 构造函数,设置true
        (拓展：底层实现双缓存)
    3.动画基础
    4. jar 打包(https://www.cnblogs.com/blog5277/p/5920560.html)
    5.初始化小球数组   Circle     AlgoVisualizer
    6.初始化Frame      AlgoFrame
    7.辅助类           AlgoVisHelper
    8.创建MVC结构
    9.键盘事件
    10.鼠标事件
    11. AlgoFrame.AlgoCanvas 类执行绘制代码
    12. AlgoVisualizer.EventQueue.invokeLater 
        初始化frame,并添加监听事件
    13.AlgoVisualizer.run 执行小球运动逻辑,
        并调用 AlgoFrame.render() 重绘画布
二：note
    1.KeyAdapter implements KeyListener
    2.在frame初始化后添加监听器