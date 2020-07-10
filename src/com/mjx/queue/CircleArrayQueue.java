package com.mjx.queue;

import java.util.Scanner;

public class CircleArrayQueue {


    int maxsize;
    int front;  //前指针
    int rear;   //尾指针
    int[] arr;
    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(5);

        boolean loop=true;
        while (loop){
            System.out.println("请选择功能：");
            System.out.println("s(show) 显示队列");
            System.out.println("a(add) 入队列");
            System.out.println("g(get) 从队列取出数据");
            System.out.println("h(head) 显示队列头部");
            System.out.println("l(size) 显示队列长度");
            System.out.println("e(exit) 退出程序");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            switch (s){
                case "s":
                    System.out.println("显示队列");
                    queue.showQueue();
                    break;
                case "a":
                    System.out.println("入队列，请输入要入力的值");
                    int val = sc.nextInt();
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
                case "l":
                    System.out.println("显示队列长度");
                    System.out.println("队列长度:"+queue.getSize());

                    break;
                case "e":
                    System.out.println("退出程序");
                    loop = false;
                    sc.close();
                    break;
                default:
                    System.out.println("输入错误");
            }


        }
    }

    public CircleArrayQueue(int maxsize){
        this.maxsize = maxsize;
        arr = new int[maxsize];
        this.front = 0;
        this.rear = 0;
    }

    public boolean isFull(){

        return (rear+1) % maxsize == front;
    }
    public boolean isEmpty(){
        return rear == front ;
    }
    public int getSize(){
        return (rear-front+maxsize) % maxsize ;
    }

    public void addQueue(int val){
        if(isFull()){
            System.out.println("队列已满，不能添加数据。");
            return;
        }
        arr[rear] = val;
        rear = (rear+1) % maxsize ;
    }

    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列已空，不能出队列。");
        }

        int temp = arr[front];
        arr[front] = 0;         //删除该数
        front = (front+1) % maxsize;    //头节点后移1
        return temp;            //返回删除的数
    }

    public void showHead(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，没有头元素");
        }
        System.out.printf("头数据： %d\n",arr[front]);
    }
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列为空");
        }
        for (int i=front; i%maxsize!=rear ;i++) {
            System.out.printf("arr[%d] = %d\n",i%maxsize,arr[i%maxsize]);
        }
    }


}
