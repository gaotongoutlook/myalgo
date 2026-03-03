package com.test.section;

/**
 * 排序
 *
 * 数据量小（< 50）：插入排序或选择排序
 * 数据基本有序：插入排序（接近O(n)）
 * 数据量大：快速排序（平均最快）、归并排序（稳定）
 * 内存受限：堆排序（原地排序）
 * 需要稳定性：归并排序、插入排序、冒泡排序
 */
public class MySort {

    /**
     * 冒泡排序
     * 时间复杂度 平均N方 最好N 最坏N方
     * 空间复杂度 1 原地排序
     * 稳定
     * 思想是是相邻元素两两比较，每一轮遍历都会把当前未排序部分的最大元素 “冒泡” 到末尾，重复这个过程直到所有元素有序
     */
    public void bubbleSort(int[] arr) {
        if(arr==null || arr.length<=1) {
            return;
        }

        int n = arr.length;
        for(int i=0; i<n-1; i++) {
            boolean swapped = false;
            for(int j=0; j<n-1-i; j++) {
                if(arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    swapped = true;
                }
            }
            if(!swapped) {
                break;
            }
        }
    }

    /**
     * 选择排序
     * 时间复杂度 平均N方 最好N方 最坏N方
     * 空间复杂度 1 原地排序
     * 不稳定
     * 思想是分已排序区和未排序区，每一轮从未排序区中找到最小（或最大）元素，将其与未排序区的第一个元素交换位置，逐步扩大已排序区，直到整个数组有序
     */
    public void selectionSort(int[] arr) {
        if(arr==null || arr.length<=1) {
            return;
        }

        int n = arr.length;
        for(int i=0; i<n-1; i++) {
            int minIndex = i;
            for(int j=i+1; j<n; j++) {
                if(arr[j] > arr[minIndex]) {
                    minIndex = j;
                }
            }

            if(minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }

    /**
     * 快速排序
     * 时间复杂度 平均nlogn 最好nlogn 最坏N方
     * 空间复杂度 logn 递归调用栈
     * 不稳定
     * 思想是基于分治思想，核心是「选基准、分区、递归」
     *  选基准（pivot）：从数组中选一个元素作为基准（常见选法：数组中间元素、第一个 / 最后一个元素）
     *  分区（partition）：遍历数组，将小于基准的元素放到基准左侧，大于基准的放到右侧，基准最终处于 “正确位置”
     *  递归处理：对基准左侧和右侧的子数组重复上述步骤，直到子数组长度≤1（天然有序）
     */
    public void quickSort(int[] arr) {
        if(arr==null || arr.length<=1) {
            return;
        }
        quickSort(arr, 0, arr.length-1);
    }

    private void quickSort(int[] arr, int low, int high) {
        if(low >= high) {
            return;
        }
        int pivotIndex = partition(arr, low, high);
        quickSort(arr, low, pivotIndex-1);
        quickSort(arr, pivotIndex+1, high);
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low;
        int j = high-1;
        while(i < j) {
            while(i < j && arr[i] <= pivot) {
                i++;
            }
            while(i < j && arr[j] > pivot) {
                j--;
            }
            if(i < j) {
                swap(arr, i, j);
            }
        }
        // 将 pivot 放到正确位置
        swap(arr, low, i);
        return i;
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    /**
     * 归并排序
     * 时间复杂度 平均nlogn 最好nlogn 最坏nlogn
     * 空间复杂度 N 需要借助一个额外的数组
     * 稳定
     * 思想是
     */
    public void mergeSort(int[] arr, int left, int right) {
        if(arr==null || arr.length<=1 || left>=right) {
            return;
        }
        int mid = left + (right - left) >> 1;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid+1, right);
        mergeSort(arr, left, mid, right);
    }

    private void mergeSort(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right-left+1];
        int i = left;
        int j = mid+1;
        int k = left;

        while(i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            }else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        while (j <= right) {
            temp[k++] = arr[j++];
        }

        for (k = left; k <= right; k++) {
            arr[k] = temp[k];
        }
    }

    /**
     * 插入排序
     * 时间复杂度 平均n方 最好n 最坏n方
     * 空间复杂度 1 原地排序
     * 稳定
     * 思想是将数组分为「已排序区间」和「未排序区间」，每次从未排序区间取出一个元素，插入到已排序区间的合适位置，直到整个数组有序
     */
    public void insertionSort(int[] arr) {
        if(arr==null || arr.length<=1) {
            return;
        }

        for(int i=1; i<arr.length; i++) {
            int current = arr[i];
            for(int j=i-1; j>=0; j--) {
                if(arr[j] > current) {
                    arr[j+1] = arr[j];
                    j--;
                }else {
                    arr[j+1] = current;
                    break;
                }
            }
        }
    }

    /**
     * 堆排序
     * 时间复杂度 平均nlogn 最好nlogn 最坏nlogn
     * 空间复杂度 1 原地排序
     * 不稳定
     * 思想是利用完全二叉树结构的「堆」这种数据结构，先将待排序数组构建成大顶堆（父节点值 ≥ 子节点值），然后反复将堆顶的最大值交换到数组末尾，再对剩余元素重新调整为大顶堆，直到整个数组有序
     */
    public void heapSort(int[] arr) {

    }

}
