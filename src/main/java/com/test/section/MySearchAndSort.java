package com.test.section;

public class MySearchAndSort {

    /**
     * 二分查找
     */
    public int search (int[] nums, int target) {
        if(nums==null || nums.length==0) {
            return -1;
        }

        int left = 0;
        int right = nums.length-1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                return mid;
            }else if(nums[mid] > target) {
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }

        return -1;
    }

    /**
     * 二维数组中的查找
     */
    public boolean Find (int target, int[][] array) {
        if(array==null || array.length==0 || array[0].length==0) {
            return false;
        }

        int row = 0;
        int col = array[0].length-1;
        while(row<array.length && col>=0) {
            if(array[row][col] == target) {
                return true;
            }else if(array[row][col] < target) {
                row++;
            }else {
                col--;
            }
        }

        return false;
    }

    /**
     * 寻找峰值
     */
    public int findPeakElement (int[] nums) {
        if(nums==null || nums.length==0) {
            return -1;
        }

        int left = 0;
        int right = nums.length-1;
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] < nums[mid+1]) {
                left = mid + 1;
            }else {
                right = mid;
            }
        }

        return left;
    }

    /**
     * 数组中的逆序对
     */
    public int InversePairs(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] temp = new int[nums.length];
        return mergeSort(nums, 0, nums.length - 1, temp);
    }

    private int mergeSort(int[] nums, int left, int right, int[] temp) {
        if (left >= right) {
            return 0;
        }

        int mid = left + (right - left) / 2;

        // 分别计算左右两部分的逆序对
        int leftCount = mergeSort(nums, left, mid, temp) % 1000000007;
        int rightCount = mergeSort(nums, mid + 1, right, temp) % 1000000007;

        // 合并时计算跨左右两部分的逆序对
        int mergeCount = merge(nums, left, mid, right, temp) % 1000000007;

        return (leftCount + rightCount + mergeCount) % 1000000007;
    }

    private int merge(int[] nums, int left, int mid, int right, int[] temp) {
        // 复制待合并的数组到临时数组
        for (int i = left; i <= right; i++) {
            temp[i] = nums[i];
        }

        int i = left;      // 左半部分起始位置
        int j = mid + 1;   // 右半部分起始位置
        int k = left;      // 合并后的数组当前位置
        int count = 0;     // 逆序对计数

        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                nums[k++] = temp[i++];
            } else {
                // 如果左半部分的当前元素大于右半部分的当前元素
                // 那么左半部分从i到mid的所有元素都大于temp[j]
                nums[k++] = temp[j++];
                count += mid - i + 1;  // 统计逆序对
                count %= 1000000007;
            }
        }

        // 复制剩余元素
        while (i <= mid) {
            nums[k++] = temp[i++];
        }

        while (j <= right) {
            nums[k++] = temp[j++];
        }

        return count % 1000000007;
    }

    /**
     * 旋转数组的最小值
     */
    public int minNumberInRotateArray (int[] nums) {
        if(nums.length==1) {
            return nums[0];
        }

        int left = 1;
        int right = nums.length-1;
        int lowIndex = 0;
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] > nums[lowIndex]) {

            }
        }

        return nums[lowIndex];
    }


}
