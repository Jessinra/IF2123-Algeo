import java.io.*;
import java.util.*;

public class drivermenu {

	public static void main(String[] args){

		Matrix M1 = new Matrix();    // SPL
		Matrix M2 = new Matrix();	 // Augmented result

		File file = new File("Solusi_SPL.txt"); /* nama file yang akan di save */
    	BufferedWriter writer = null;
    	
    	try{
    		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
    	}
    	catch (FileNotFoundException e) {
    		System.err.println("File not found");
    	}
		
    	System.out.println("Masukkan input matriks SPL");
		M1.fillMatrix();
		System.out.println("Masukkan input matriks hasil tiap SPL");
	    M2.fillMatrix(M1.row,1);
		
		M1.saveMatrix(M2);
	}
}