/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmsc132_mp;

import java.util.Scanner;

/**
 *
 * @author MiriamMarie
 */
public class CMSC132_MP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.print("Input: ");
        Scanner in = new Scanner(System.in);
        String temp = in.nextLine();
        char[] input = temp.toCharArray();
        char[] finalInput = new char[100];
        char[][] bigMemory = new char[input.length][4];
        char[][] smallMemory = new char[input.length][4];
        int[][] numbMemory = new int[temp.length()][4];
        int inputSize = input.length;
        
        //Copy
        System.arraycopy(input, 0, finalInput, 0, input.length);
        
        if((int) finalInput[0] >= 48 && (int) finalInput[0] <= 57){
            //For integer inputs
            String[] ar = temp.split(" ");
            int [] numbers = new int[ar.length];
            String [] binaryString = new String[32];
            String[] eightBit = new String[ar.length];
            
            //Convert every integer input to binary
            for(int i=0; i < ar.length; i++){
                binaryString[i] = Integer.toBinaryString(Integer.valueOf(ar[i]));
                System.out.println(binaryString[i]);
                if(binaryString[i].length() < 32){
                    int zeros = 32 - binaryString[i].length();
                    System.out.println(zeros+"zero");
                    String[] tempBinary = new String[32];
                    for(int ctr=0; ctr<zeros; ctr++){
                        tempBinary[i] = tempBinary[i] + "0";
                    }
                    binaryString[i] = tempBinary[i] + binaryString[i];
                    System.out.println(binaryString[i]);
                }
                
                //Store 4 bits 
                for(int index=0; index<binaryString.length; index++){
                    for(int ctr=0; ctr<4; ctr++){
                        eightBit[index] = eightBit[index] + binaryString[ctr];
                    }
                }
            }
            
            
            
            System.out.println("BIG ENDIAN & LITTLE ENDIAN");
            System.out.println("REPRESENTATION FOR INTEGERS");
            
            int numberLength = numbers.length;
            int row = 0, i=0;
            while(numberLength > 0){
                for(int column = 0; column < 4; column++){
                    if(column!=3){ //Check place if rightest or not
                        numbMemory[row][column] = 0;
                    } else {
                        numbMemory[row][column] = numbers[i];
                        i++;
                        numberLength--;
                    }
                    System.out.print(numbMemory[row][column] + "|");
                }
                row++;
                System.out.println("");
            }
        } else { //For string inputs
            
            //Check if multiple of four
            while(!(inputSize % 4 == 0)){
                finalInput[inputSize] = '0';
                inputSize++;
            }   
            
            
            System.out.println("BIG ENDIAN REPRESENTATION");
            //Initialization
            int i = 0, k = 0;
            int tempSize = inputSize;
            
            while(tempSize > 0){
                for(int j=0; j < 4; j++){
                    bigMemory[k][j] = finalInput[i];
                    System.out.print(bigMemory[k][j] + "|");
                    i++;
                    tempSize--;
                }
                k++;
                System.out.println("");
            } 
            System.out.println("---------------");
            
            System.out.println("LITTLE ENDIAN REPRESENTATION");
            //Re initialize
            int index = 0, row =0;
            tempSize = inputSize;
            
            while(tempSize > 0){
                int j = 0;
                //Store reversely
                for(int column=3; j < 4; j++){
                    smallMemory[row][column] = finalInput[index];
                    column--;
                    index++;
                    tempSize--;
                }
                // Separate printing 
                for(int m=0; m < 4; m++){
                    System.out.print(smallMemory[row][m]+"|");
                }
                System.out.println("");
                row++;
            }
        }
    }
}
