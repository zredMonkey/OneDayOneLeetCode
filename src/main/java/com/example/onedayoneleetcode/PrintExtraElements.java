package com.example.onedayoneleetcode;

public class PrintExtraElements {

    public void printExtraElements(int[] A, int[] B) {
        int i = 0; // A数组的指针
        int j = 0; // B数组的指针

        while (i < A.length && j < B.length) {
            if (A[i] < B[j]) {
                System.out.println(A[i]); // 打印A中比B多的元素
                i++;
            } else if (A[i] > B[j]) {
                j++;
            } else {
                i++;
                j++;
            }
        }

        // 打印A中剩余的元素（如果有）
        while (i < A.length) {
            System.out.println(A[i]);
            i++;
        }
    }

    public static void main(String[] args) {
        int[] A = {1, 3, 4, 5, 7};
        int[] B = {2, 3, 5, 6};


        PrintExtraElements obj = new PrintExtraElements();
        obj.printExtraElements(A, B);
    }

}
