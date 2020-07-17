package com.runjing.utils.compressPic;

/**
 * @Created by xiaoyu on 2017/1/11.
 * @Describe：用于测试闲人勿扰滚粗
 * @Review by：
 * @Modify by：
 * @Version : $ v_1.0 on 2017/1/11.
 * @Remark:
 */
public class Test {

    public void getSort(){
        int[] array = {49,38,65,97,76,13,27,49};

    }

    /**
     * 方法一 普通排序
     * @param arr
     * @return
     * @throws Exception
     */
    public int[] function1(int[] arr) throws Exception{
        int[] sort = new int[arr.length];
        int count = 0;
        for (int i =0; i < arr.length; i++){
            for (int j = 0; j < arr.length; j++){
                if (arr[i] <= arr[j]){
                   sort[count] = arr[i];
                    count++;
                }
            }
        }
        return sort;
    }

    /**
     * 方法二 二分法
     * @param arr
     * @return
     * @throws Exception
     */
    public int[] function2(int[] arr) throws Exception{
        int[] sort = new int[arr.length];

        return sort;
    }
}
