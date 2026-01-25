package com.nowcoder.mytwopointer;

/**
 * 位运算
 */
public class MyBitwiseOperation {

    /**
     * 汉明距离
     */
    public int hammingDistance(int x, int y) {
        int r = x ^ y;
        int mask = 1;
        int count = 0;
        for(int i=0; i<31; i++) {
            if((r & mask) != 0) {
                count++;
            }
            mask *= 2;
        }
        return count;
    }

    /**
     * 位1的个数
     */
    public int hammingWeight(int n) {
        int oneCount = 0;
        int mask = 1;
        for(int i=0; i<32; i++) {
            if((n & mask) != 0) {
                oneCount++;
            }
            mask <<= 1;
        }
        return oneCount;
    }

}
