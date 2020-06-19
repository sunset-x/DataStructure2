package com.mjx.sparseArr;

//稀疏数组

/**
 * 当一个二维数组存了很多没意义的0值后，就可以将它转换为稀疏数组存放，以压缩内存空间
 * 方法：
 *      1.稀疏数组第一行来记录 原数组有几行、几列、有效值个数
 *      2.从第二行开始，分别记录有效值的所在行、所在列、值。
 */

import java.io.*;

/**
 * 示例：在五子棋游戏中，黑子用1代替，白棋用2代替，棋盘大小11*11。此时就可将棋盘数组转换为稀疏数组，存放文件，复原时再将稀疏数组-》棋盘数组
 * 思路：
 * 1.棋盘数组转换稀疏数组
 *      1）for循环遍历棋盘数组，记录有效值个数sum和原棋盘大小
 *      2）根据有效值个数来创建稀疏数组 即为 arr[sum+1][3]
 *      3）for循环遍历 当棋盘不是无效值时 记录所在行列及值
 *      4)将数组存入文件
 * 2.稀疏数组转换棋盘数组
 *      1）读取稀疏数组，根据稀疏数组第一行前两值创建棋盘数组
 *      2）从第二行开始遍历稀疏数组，将值写入原始数组
 */
public class sparseArr {
    /**
     * 程序入口
     * @param args
     */
    public static void main(String[] args) {
        //创建棋盘并下棋
        int[][] chessArr = createChessArr();
        //将棋盘转为稀疏数组
        int[][] sparseArr = chessToSparseArr(chessArr);
        //将稀疏数组写入文件
        File file = new File("sparseArr.txt");  //存放数组数据的文件
        Boolean suc = fileOutput(sparseArr, file);
        //如果写入文件成功，就恢复棋盘并显示
        if (suc)
            readGame(file);
        else
            System.out.printf("写入文件失败");
    }

    /**
     * 初始化 创建游戏棋盘 并下棋2次
     * @return
     */
    public static int[][] createChessArr(){
        //创建棋盘数组
        int[][] chessArr = new int[11][11];
        //下棋
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
        //打印数组
        System.out.println("原棋盘为：");
        for (int[] ca:  chessArr) {
            for (int c:ca) {
                System.out.print(c+"\t");
            }
            System.out.println();
        }
        return chessArr;
    }

    /**
     * 将游戏棋盘转换为稀疏数组
     * @param chessArr
     * @return
     */
    public static int[][] chessToSparseArr(int[][] chessArr){

        int sum=0;
        int row=chessArr.length;
        int col=chessArr[0].length;
        //1）for循环遍历棋盘数组，记录有效值个数sum和原棋盘大小
        for (int[] ca:  chessArr) {
            for (int c:ca) {
                if (c!=0)
                    sum++;
            }
        }
        //2）根据有效值个数来创建稀疏数组 即为 arr[sum+1][3]
        int[][] sparseArr = new int[sum+1][3];
        //第一行
        sparseArr[0][0] = row;
        sparseArr[0][1] = col;
        sparseArr[0][2] = sum;
        //3）for循环遍历 当棋盘不是无效值时 记录所在行列及值
        int sCol = 1;
        for(int i=0;i<row;i++){
            for (int j=0;j<col;j++){
                if(chessArr[i][j]!=0){
                    sparseArr[sCol][0] = i;
                    sparseArr[sCol][1] = j;
                    sparseArr[sCol++][2] = chessArr[i][j];
                }
            }
        }

        System.out.println("转换后的稀疏数组为：");
        for (int[] sa:  sparseArr) {
            System.out.printf("%d\t%d\t%d\t",sa[0],sa[1],sa[2]);
            System.out.println("");
        }
        return sparseArr;
    }


    /**
     * 将数组写入文件
     * @param sparseArr
     * @param file
     * @return
     */
    public static Boolean fileOutput(int[][] sparseArr,File file)  {
        FileWriter out = null;
        try {
            out = new FileWriter(file);  //文件写入流
            for (int[] sa:  sparseArr) {
                out.write(sa[0]+"\t"+sa[1]+"\t"+sa[2]+"\n");
            }
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 读取文件中的数组
     * @param file
     * @return
     */
    public static int[][] fileInput(File file)  {
        int[][] arr = new int[1024][1024];
        BufferedReader in = null;  //
        try {
            in = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;  //一行数据
        int row=0;
        //逐行读取，并将每个数组放入到数组中
        try {
            while ((line = in.readLine()) != null) {
                String[] temp = line.split("\t");
                for (int j = 0; j < temp.length; j++) {
                    arr[row][j] = Integer.parseInt(temp[j]);
                }
                row++;
            }
            in.close();
            return arr;
        }catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
    }

    /**
     * 读取文件 显示原棋盘
     */
    public static void readGame(File file){
        //1)读取稀疏数组
        int[][] sparseArr = fileInput(file);
        //2）根据稀疏数组第一行前两值创建棋盘数组
        int[][] chessArr  = new int[sparseArr[0][0]][sparseArr[0][1]];
        //3）从第二行开始遍历稀疏数组，将值写入原始数组
        for (int i = 1;i<sparseArr.length;i++){
            chessArr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        //显示一下
        System.out.println("恢复后的棋盘：");
        for (int[] ca:  chessArr) {
            for (int c:ca) {
                System.out.print(c+"\t");
            }
            System.out.println();
        }
    }

}
