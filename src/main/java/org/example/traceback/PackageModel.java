package org.example.traceback;

/**
 * 0-1背包问题
 */
public class PackageModel {

    /**
     * 0-1背包问题
     */
    public int bage(int[] items, int w) {
        int maxW = Integer.MIN_VALUE;
        backtrace(items, 0, 0, w, maxW);
        return maxW;
    }

    // k:阶段
    // cw：路径，记录已经选择的物品的总重量
    // items[k]：选择列表，选或不选
    // w：剪枝的条件
    private void backtrace(int[] items, int k, int cw, int w, int maxW) {
        // cw==w 表示装满了； k==items.length 表示已经考察完所有的物品
        if(cw == w || k == items.length) {
            if(cw > maxW) {
                maxW = cw;
            }
            return;
        }
        // 做选择
        backtrace(items, k+1, cw, w, maxW); // 不装
        if(cw + items[k] <= w) { // 剪枝
            backtrace(items, k+1, cw+items[k], w, maxW); // 装
        }
        // 都是局部变量，自动撤销选择
    }

    /**
     * 物品的总价值最大
     * @return
     */
    private int maxV = Integer.MIN_VALUE;
    private int[] weight = new int[]{};
    private int[] value = new int[]{};
    int n = 5;
    private int w = 9;

    public void bigValue(int i, int cw, int cv) {
        if(cw == w || i == n) {
            if(cv > maxV) {
                maxV = cv;
            }
            return;
        }
        // 不选择第i个物品
        bigValue(i+1, cw, cv);
        if(weight[i] + cw <= w) {
            // 选择第i个物品
            bigValue(i+1, cw+weight[i], cv+value[i]);
        }
    }

}
