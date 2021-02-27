package com.banxian.design;

/**
 * @program: Pattern-Design
 * @description: 鸭子类
 * @author: Wangly
 * @create: 2021-02-24 15:32
 *
 * 设计原则
 * 一.找出应用中可能需要变化的地方，独立出来，不要和其他不变的代码混合到一起
 * 也就是将会变化的代码取出并封装起来，让其他部分不受影响
 * 实际：
 *       新需求
 *          -->代码变化
 *            --->需要抽取
 *              ---->区分变化的代码和不变的代码
 * 二.针对接口编程，而不是针对实现编程
 * 为什么不用抽象类实现飞行行为？
 * 针对接口编程真正的意思是 ‘’针对超类型编程‘’
 * 接口有多个含义：
 * 1.Interface构造
 * 可以在不涉及Java Interface的情况下 针对接口编程
 * 关键就在多态，利用多态
 * 根据实际情况执行真正的行为，不会固定在超类型的行为上
 * ‘’针对超类型编程‘’明确来说,变量的声明类型应该是超类型的，通常是一个抽象类是接口
 * 只要具体实现超类型的类产生的所有对象，都可以制定给这个变量
 * 声明类不用理会以后执行的真正对象类型
 * 三. 多用组合，少用继承
 *
 * 策略模式 定义了算法族，分别封装起来。他们之间可以互相替换，
 * 算法的变化独立于使用算法的客户
 *
 * 继承   extends   ───────────▷
 * 实现   implement ···········▷
 * 有一个   ──────────>
 * * 例子
 @see com.banxian.design.demo.Animal
 * 实例如下
 */

public  interface FlyBehavior {
    /**
     * 飞行类必须实现的接口
     * */
    void fly();
}

class FlyWithWings implements FlyBehavior {
    /**
     * 会飞的行为实现 会飞的鸭子用
     * */
    @Override
    public void fly() {
        //鸭子飞   有翅膀的鸭子的飞行动作
        System.out.println("鸭子飞");
    }
}


class FlyNoWay implements FlyBehavior
{
    /**
     * 不会飞的行为实现 不会飞的鸭子用
     * */
    @Override
    public void fly() {
        // 不会飞 nothing to do 不会飞的鸭子的操作
        System.out.println("不会飞");
    }

}

interface QuackBehavior {
    /**
     * 呱呱叫行为
     * */
    void quack();
}

class Quack implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("鸭子呱呱叫");
    }
}

class Squeak implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("橡皮鸭子吱吱叫");
    }
}

class MuteQuack implements QuackBehavior {

    @Override
    public void quack() {
        // 啥都不干
        System.out.println("<< Silence >>");
    }
}

class RocketPowered implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("火箭推进动力系统");
    }
}

class Frog implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("青蛙呱呱叫");
    }
}


abstract class  Duck {
    
    /**
     * 为行为接口类型声明引用  所有鸭子子类都继承他们
     * 加入两个实例变量，声明为接口类型，
     * 每个鸭子都会动态设置这变量
     * 以在运行时引用正确的行为类型 （例如：FlyWithWings）
     * */
    FlyBehavior flyBehavior = new FlyWithWings();
    QuackBehavior quackBehavior;

    public Duck() {
    }
    /**
     * abstract function
     * */
    abstract void display();

    /**
     * 委托给行为类
     */

    void performFly() {
        flyBehavior.fly();
    }
    /**
     * 委托给行为类
     */
    void performQuack() {
        quackBehavior.quack();
    }

    void swim() {
        System.out.println("all ducks float , evendecoys");
    }

    void setFlyBehavior(FlyBehavior fb) {
        flyBehavior = fb;
    }

    void setQuackBehavior(QuackBehavior qb) {
        quackBehavior = qb;
    }
}
/**
 * 因为MallarDuck 继承了Duck
 * */
class MallarDuck extends Duck {

    public MallarDuck() {
        // 叫行为引用指向
        // 叫行为 ：多态  quackBehavior 指向 子类  Quack
        quackBehavior = new Quack();
        // 飞行为引用指向
        // 飞行为 ：多态  flyBehavior 指向子类 飞行为FlyWithWings
        flyBehavior = new FlyWithWings();
    }

    @Override
    void display() {
        System.out.println("real Mallard duck");
    }
}

class MinniDuckSimulator {
    public static void main(String[] args) {
        Duck mallard = new MallarDuck();
        /**
         @see MallarDuck 继承自Duck
         * mallard 继承自“Duck”
         * MallarDuck 调用继承而来的 performQuack()
          * 委托quackBehavior 处理 调用继承而来的quackBehavior
          * 引用对象的quack()
          * performFly() 也是一样道理
          *
          * 抽象类其实是可以实例化的，但是他的实例化方式不是通过new方式来创建对象，而是通过父类的引用来指向子类的实例来间接地实现父类的实例化（因为子类要实例化前，一定会先实例化他的父类。这样创建了继承抽象类的子类的对象，也就把其父类（抽象类）给实例化了）.但是：接口是不能被实例化的（接口压根就没有构造函数）。
         * */
        mallard.performQuack();
        mallard.performFly();

        Duck model = new ModelDuck();
        model.performFly();
        model.setFlyBehavior(new FlyRocketPowered());
        model.performFly();
    }
}

/**
 * 通过方法设定鸭子行为，不在构造器中实例化
 * <p>
 * void setFlyBehavior(FlyBehavior fb) {
 * flyBehavior = fb;
 * }
 * 随时调用这两个方法改变鸭子行为
 * void setQuackBehavior(QuackBehavior qb) {
 * quackBehavior = qb;
 * }
 */

class ModelDuck extends Duck {
    public ModelDuck() {
        // 还不会飞
        flyBehavior = new FlyNoWay();
        quackBehavior = new Quack();
    }

    @Override
    void display() {
        System.out.println("modelDuck");
    }
}

class FlyRocketPowered implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("火箭带我飞");
    }
}



