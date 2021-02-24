package com.banxian.design.demo;

/**
 * @program: Pattern-Design
 * @description: 动物
 * @author: Wangly
 * @create: 2021-02-24 16:21
 */
public interface Animal
{
    void makeSound();
    public static void main(String[] args) {
        // 针对实现编程
        /**
         * 声明变量“d”为Dog类型 （Animal具体实现）
         * 造成必须针对具体实现编码
         * */
        Dog dog = new Dog();
        dog.bark();
        /**
         * 针对接口/超类型编程
         * 知道创建的对象是狗
         * 子类实例化的动作不需要在代码中硬编码，new Dog();
         * 运行时才指定具体实现的对象,只关心正确进行makeSound()
         * */
        Animal animal = new Dog();
        animal.makeSound();
        Animal cat = new Cat();
        cat.makeSound();
    }


}

class Dog implements Animal {
    void bark() {
        System.out.println("犬吠");
    }


    @Override
    public void makeSound() {
        bark();
    }
}

class Cat implements Animal {
    void meow() {
        System.out.println("miaomiaomiao~~~~");
    }

    @Override
    public void makeSound() {
        meow();
    }
}