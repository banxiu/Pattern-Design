package com.banxian.design;

import java.util.concurrent.*;

/**
 * @program: Pattern-Design
 * @description: 武器行为
 * @author: Wangly
 * @create: 2021-02-26 08:53
 */
public interface WeaponBehavior {
    /**
     * 使用的武器
     * */
    void useWeapon();
}

/**
 * 章节
 * */
abstract class AbstractCharacter {
    WeaponBehavior weapon ;

    public AbstractCharacter() {
    }

    /**
     * Attention；
     * 抽象类中不声明abstract的方法需要有方法体
     * 而声明为abstract的方法则不需要方法体
     * 战斗动作
     */

    void fight() {
        weapon.useWeapon();
    }
    void setWeapon(WeaponBehavior w) {
        this.weapon = w;
    }
}

class Queen extends AbstractCharacter {

    @Override
    void fight() {
        weapon.useWeapon();
    }
}

class King extends AbstractCharacter {

    @Override
    void fight() {
        weapon.useWeapon();
    }
}

class Troll extends AbstractCharacter {

    @Override
    void fight() {
        weapon.useWeapon();
    }
}

class Knight extends AbstractCharacter {

    @Override
    void fight() {
        weapon.useWeapon();
    }
}

class KnifeBehavior implements WeaponBehavior {

    @Override
    public void useWeapon() {
        // 匕首
        System.out.print("用匕首刺杀了");
    }
}
class BowAndArrowBehavior implements WeaponBehavior {

    @Override
    public void useWeapon() {
        // 弓箭
        System.out.print("用弓箭射杀了");
    }
}
class AxeBehavior implements WeaponBehavior {

    @Override
    public void useWeapon() {
        // 斧头
        System.out.print("用斧头砍杀了");
    }
}

class SwordBehavior implements WeaponBehavior {

    @Override
    public void useWeapon() {
        // 宝剑
        System.out.print("用宝剑砍杀了");
    }
}

class Fight {
    public static void main(String[] args) {
        Executor executor = new ThreadPoolExecutor(10, 20, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(10), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());
        // 创建十个线程
        for (int i = 0; i < 10; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        AbstractCharacter king = new King();
                        king.setWeapon(new SwordBehavior());
                        System.out.println("chapter I :murder");
                        // System.out.println(Thread.currentThread().getName());
                        Thread.sleep(10000);
                        System.out.print("king");
                        king.fight();
                        System.out.println("Troll");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
    }
}

