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

        HeroNode newN2 = new HeroNode(2,"露露","玉麒麟2");
        HeroNode newN3 = new HeroNode(8,"11","222");
        singleLinkedList.update(newN2);
//        singleLinkedList.update(newN3);
        System.out.println("修改后的链表：");
        singleLinkedList.list();
        System.out.println("删除节点2：");
        singleLinkedList.del(2);
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

    // 修改节点：修改节点内容 no不能修改 即根据no修改内容
    public void update(HeroNode heroNode){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //先用辅助变量找到no 如果找到再修改
        HeroNode tempNode = head.next;

        while (true){
            if (tempNode==null){
                System.out.println("未找到");
                break;
            }
            if (tempNode.no == heroNode.no){
                //找到
                tempNode.name = heroNode .name;
                tempNode.nickName = heroNode.nickName;
                System.out.println("已修改");
                break;
            }
            tempNode = tempNode.next;
        }
    }
    // 删除节点
    public void del(int no){
        if(head.next == null){
            System.out.println("链表为空");
        }

        HeroNode tempNode = head;
        while (true){
            if (tempNode.next == null){
                System.out.println("没有该节点");
                break;
            }
            if (tempNode.next.no == no){
                if (tempNode.next.next == null){
                    tempNode.next = null;
                }else{
                    tempNode.next = tempNode.next.next;
                }
                System.out.println("删除成功");
                break;
            }
            tempNode = tempNode.next;
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