package com.mjx.LinkedList;

public class SingleLinkedListDemo {

    public static void main(String[] args) {

        HeroNode n1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode n2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode n3 = new HeroNode(3, "吴用", "智多星");
        HeroNode n4 = new HeroNode(4, "林冲", "豹子头");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(n1);
        singleLinkedList.add(n3);
        singleLinkedList.add(n4);
        singleLinkedList.add(n2);

        singleLinkedList.list();
    }
}

class SingleLinkedList{
    private HeroNode head = new HeroNode(0,"","");

    //添加节点到链接
    public void add(HeroNode heroNode){
        //1.遍历找到next等于null的节点
        HeroNode temp = head;
        //找到temp.next==null的节点就break 这样temp即为最后一个节点
        while (true){
            if(temp.next==null){
                break;
            }
            temp = temp.next;
        }
        //2.将该节点的next = 新的
        temp.next = heroNode;
    }

    //第二种方式在添加英雄时,根据排名将英雄插入到指定位置(如果有这个排名,则添加失败,并给出提示
    public void addByOrder(HeroNode heroNode){
        //1.遍历找到next等于null的节点
        HeroNode temp = head;
        HeroNode tempNext = null;
        //找到temp.next==null的节点就break 这样temp即为最后一个节点
        while (true){
            if(temp.next==null){
                break;
            }
            if (temp.next.no>heroNode.no){
                tempNext = temp.next;
                break;
            }
            temp = temp.next;
        }
        //2.将该节点的next = 新的
        temp.next = heroNode;
        heroNode.next = tempNext;
    }

    //显示链表
    public void list(){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        while (true){
            if(temp == null){
                return;
            }
            System.out.println(temp);
            temp = temp.next;
        }

    }
}

class HeroNode{
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}