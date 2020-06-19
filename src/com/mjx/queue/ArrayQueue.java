package com.mjx.queue;

import java.util.Scanner;

public class ArrayQueue {

    int maxsize;
    int front;
    int rear;
    int[] arr;

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);

        boolean loop=true;
        while (loop){
            System.out.println("请选择功能：");
//            System.out.println("c(create) 创建队列");
            System.out.println("s(show) 显示队列");
            System.out.println("a(add) 入队列");
            System.out.println("g(get) 从队列取出数据");
            System.out.println("h(head) 显示队列头部");
            System.out.println("e(exit) 退出程序");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            switch (s){
//                case "c" :
//                    System.out.println("创建队列，请输入队列存放值");
//                    int n = sc.nextInt();
//                    queue = new ArrayQueue(n);
//                    System.out.println("队列已创建成功");
//                    break;
                case "s":
                    System.out.println("显示队列");
                    queue.showQueue();
                    break;
                case "a":
                    System.out.println("入队列，请输入要入力的值");
                    int val = sc.nextInt();
                    System.out.println("val:"+val);
                    queue.addQueue(val);
                    break;
                case "g":
                    System.out.println("出队列");
                    queue.getQueue();
                    break;
                case "h":
                    System.out.println("显示队列头部");
                    queue.showHead();
                    break;
                case "e":
                    System.out.println("退出程序");
                    loop = false;
                    sc.close();
                    break;
                default:
                    System.out.println("输入错误");
            }
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

        }
    }

    public ArrayQueue(int maxsize){
        this.maxsize = maxsize;
        arr = new int[maxsize];
        this.front = -1;
        this.rear = -1;
    }

    public boolean isFull(){
//        if (rear == maxsize -1 ){
//            return true;
//        }
//        return false;
        return rear == maxsize -1;
    }
    public boolean isEmpty(){
//        if (front ==rear ){
//            return true;
//        }
//        return false;
        return rear == front ;
    }
    public void addQueue(int val){
        if(isFull()){
            System.out.println("队列已满，不能添加数据。");
            return;
        }
        rear++;
        arr[rear] = val;
    }

    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列已空，不能出队列。");
        }

        front++;
        int temp = arr[front];
        arr[front] = 0;
        return temp;
    }

    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列为空");
        }
        for (int i=0;i<arr.length;i++) {
            System.out.printf("arr[%d] = %d\n",i,arr[i]);
        }
    }

    public void showHead(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，没有头元素");
        }
        System.out.printf("头数据： %d\n",arr[front+1]);
    }


}