/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmsc132_mp;

import java.util.Scanner;
import java.util.Arrays;

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
        char[] finalInput = new char[input.length];
        char[][] bigMemory = new char[input.length][4];
        char[][] smallMemory = new char[input.length][4];
        int inputSize = input.length;
        
        //Copy
        System.arraycopy(input, 0, finalInput, 0, input.length);
        
        if(check(finalInput)){
            //For integer inputs
            String[] ar = temp.split(" ");
            int [] numbers = new int[ar.length];
            String [] binaryString = new String[ar.length];
            int [] eightBit = new int[4];
            
            System.out.println("BIG ENDIAN & LITTLE ENDIAN");
            System.out.println("REPRESENTATION FOR INTEGERS");
            //Printing
            //Convert every integer input to binary
            for(int i=0; i < ar.length; i++){
                binaryString[i] = Integer.toBinaryString(Integer.valueOf(ar[i]));
                if(binaryString[i].length() < 32){
                    int zeros = 32 - binaryString[i].length();
                    String tempBinary = new String();
                    for(int ctr=0; ctr<zeros; ctr++){
                        tempBinary = tempBinary + "0";
                    }
                    binaryString[i] = tempBinary + binaryString[i];
                }
                
                //Store 8 bits 
                for(int index=0; index<binaryString[i].length();){
                    int indexEightBit = 0;
                    String tempEight="";
                    for(int ctr=0; ctr < 8; ctr++){
                        tempEight += binaryString[i].charAt(index);
                        index++;
                    }
                    eightBit[indexEightBit] = Integer.parseInt(tempEight, 2);
                    System.out.print(eightBit[indexEightBit] + "|");
                    indexEightBit++;
                }
                System.out.println("");
            }
        } else { //For string inputs
            
            //Check if multiple of four
            int x = 0;
            while(x < inputSize) {
                x += 4;
            }
            finalInput = new char[x];
            Arrays.fill(finalInput,'0');

            //Copy
            System.arraycopy(input, 0, finalInput, 0, input.length);
            
            
            System.out.println("BIG ENDIAN REPRESENTATION FOR STRINGS");
            //Initialization
            int i = 0, k = 0;
            int tempSize = finalInput.length;
            
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
            
            System.out.println("LITTLE ENDIAN REPRESENTATION FOR STRINGS");
            //Re initialize
            int index = 0, row =0;
            tempSize = finalInput.length;
            
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

    private static boolean check(char[] finalInput){
        for(int i=0; i<finalInput.length; i++){
            if(!((int) finalInput[i] >= 48 && (int) finalInput[i] <= 57))
                return false;
        }
        return true;
    }
}
