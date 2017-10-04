import java.util.Scanner;

public class MainMenu {
	
	/*** Attributes ***/
	private Scanner in = new Scanner (System.in);
	Matrix M1 = new Matrix();    // SPL
	Matrix M2 = new Matrix();	 // Augmented result 
	boolean X;
	

	/*** Method ***/
	public void run() {
		X = true;
		int r;
		int c;
		int deg;
		Scanner in = new Scanner (System.in);	
		
		 display1: while(true) {
			    System.out.println(
			        "Pilih jenis input yang anda inginkan: \n" +
			        "  1) Input manual\n" +
			        "  2) Input dari file eksternal\n" +
			        "  3) Membuat matriks solvable random\n" +
			        "  4) Membuat matriks multisolution random\n" + 
			        "  5) Membuat matriks unsolvable random\n" +
			        "  0) Exit program\n" +
			        "Input : "
			    );
			    int selection = in.nextInt();
			    in.nextLine();
			    Scanner scan = new Scanner(System.in);
			     
			    switch (selection) {
			    case 1:
			      System.out.println("Masukkan input matriks SPL");
			      M1.fillMatrix();
			      System.out.println("Masukkan input matriks hasil tiap SPL");
			      M2.fillMatrix(M1.row,1);
			      
			      X = false;
			      break display1;
			    case 2:
			      M1.fillMatrixExt(M2);
			      X = false;
			      break display1;	  
				  
				case 3:
				  
				  System.out.print("Masukkan baris matriks : "); r = scan.nextInt(); //input baris
				  System.out.print("Masukkan kolom matriks : "); c = scan.nextInt(); //input kolom
				  M1 = Matrix.random(r,c);
				  M2 = Matrix.random(r,1);
				  X = false;
				  break display1;
				  
				case 4:
					  
				  System.out.print("Masukkan baris matriks : "); r = scan.nextInt(); //input baris
				  System.out.print("Masukkan kolom matriks : "); c = scan.nextInt(); //input kolom
				  M1 = Matrix.unsolvable(r, c, 1);
				  M2 = Matrix.unsolvable(r, 1, 1);
				  X = false;
				  break display1;
				
				case 5:
			
				  System.out.print("Masukkan baris matriks : "); r = scan.nextInt(); //input baris
				  System.out.print("Masukkan kolom matriks : "); c = scan.nextInt(); //input kolom
				  M1 = Matrix.unsolvable(r, c, 1);
				  M2 = Matrix.random(r, 1);
				  X = false;
				  break display1;
					  
				case 0:
				  System.out.println("Mengeluarkan dari program");
			      System.exit(1);
			    default:
			      System.out.println("Input yang anda masukan salah. Silahkan ulangi input anda");
			      
			    }
			  }
		
		display2: while(true) {
		    System.out.println("-- Tentukan operasi yang anda inginkan --");
		    System.out.println(
		        "  1) Menyelesaikan matriks (metode Gauss-Jordan)\n"  +
		        "  2) Interpolasi polinom\n"  +
		        "  9) Kembali ke menu sebelumnya\n"  +
		        "  0) Exit program\n" +
		        "Input : "
		    );
		    int selection = in.nextInt();
		    in.nextLine();
		     
		    switch (selection) {
		    
		    case 1:
		      M1.solve(M2);
		      System.out.println();
		      break display2;
		      
		    case 2:
		      Scanner scan = new Scanner(System.in);
			  System.out.print("Masukkan derajat interpolasi : "); 
			  deg = scan.nextInt(); //input derajat polinom
			  Matrix MX = Matrix.interpolate(M1, deg);
			  System.out.println();
			  
			  M2.row = MX.col;
			  MX.solve(M2);
			  break display2;
		    case 9:
		      System.out.println("Mengembalikan ke menu sebelumnya...");
		      X = true;
		      break display2;
		    case 0:
			  System.out.println("Mengeluarkan dari program");
		      System.exit(1);
		    default:
		      System.out.println("Input yang dimasukkan salah. Silahkan ulangi input anda");
		      break;
		    }
		 }
		 
	}
	
	public static void main(String[] args) {
		MainMenu menu = new MainMenu();
		menu.run();

	}


}