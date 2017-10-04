import java.io.*;
import java.util.*;
public class Matrix {
	
	/*** Attributes ***/
	int row;
	int col;
	int Solution_type;
	double [][] matrix;
	String format = "%7.3f ";
	
	
	/*** CONSTRUCTOR ***/
	public Matrix()
	{
		this.row = 10;
		this.row = 10;
		this.Solution_type = 0;
		matrix = new double[10][10];
	}
	
	public Matrix(int row, int col) {
		this.row = row;
		this.col = col;
		this.Solution_type = 0;
		matrix = new double[row+1][col+1];
	}
	
	
	private Matrix(Matrix A){
		/* Method Overide constructor for copy matrix */
		
		this.row = A.row;
		this.col = A.col;
		this.Solution_type = A.Solution_type;
		this.matrix = A.matrix; 
	}

	/*** SELECTOR ***/
	public double Elmt(int row, int col){
		return this.matrix[row][col];
	}
	
	/*** DISPLAY ***/
	public void show() {
		/* Display original standard matrix */
		
		String input;
		
        for (int i = 1; i <= this.row; i++) {
            for (int j = 1; j <= this.col; j++) {
            	input = String.format(format, this.Elmt(i,j));
            	System.out.print("Matriks ["+i+"] ["+j+"] : ");
            	System.out.println(input);
            }
            
        }
	}
	
	public void show(BufferedWriter writer) {
		/* Display original standard matrix */
		
		String input;
		
        for (int i = 1; i <= this.row; i++) {
            for (int j = 1; j <= this.col; j++) {
            	input = String.format(format, this.Elmt(i,j));
            	save(input, writer);
            }
            
            save("\n", writer);
        }
	}
	
	public void showEx(Matrix B, BufferedWriter writer) {
		/* Display matrix as augmented matrix */
		
		String input;
		
        for (int i = 1; i <= this.row; i++) {
            for (int j = 1; j <= this.col; j++) {
            	input = String.format(format, this.Elmt(i,j));
                save(input,writer);
            }
            
            input = (String.format(format, B.Elmt(i,1)));
            save(input, writer);
            save("\n", writer);
        }
	}
	
	
	public void showFinal(BufferedWriter writer){
		/* Display final result */
		
		for (int i = 1; i <= this.row; i++) {
			String input = String.format("X"+Integer.toString(i)+" : ");
			save(input ,writer);
            for (int j = 1; j <= this.col; j++) {
            	input = String.format(format, this.Elmt(i,j));
                save(input, writer);
            }
            save("\n", writer);
		}
	}
	
	public void showPolynom(BufferedWriter writer){
		/* Display as polynom */

		String input;
		
		for (int j = 1; j <= this.col; j++) {
			input = String.format(format, this.Elmt(1,j));
			save(input, writer);
		}
		
		for (int i = 2; i <= this.row; i++) {
            for (int j = 1; j <= this.col; j++){ 
            	input = String.format("+");
            	save(input, writer);
            	input = String.format(format, this.Elmt(i,j));    
                save(input, writer);
                input = String.format("X^"+Integer.toString(i-1));
                save(input, writer);
			}
		}
	}

	
	/*** CHECKING MATRIX PROPERTIES ***/
	private boolean eqDim(Matrix B) {
		/* has same dimension */
		
		Matrix A = this;
		boolean IsEqDim = (A.row == B.row && A.col == B.col);
        
		if (!IsEqDim) {
        	System.out.println("Different matrix dimension");
        }
   
        return IsEqDim;
	}
	
	public boolean eq(Matrix B) {
		/* is equal */
		
        Matrix A = this;
        
        if (!A.eqDim(B)){ 
        	throw new RuntimeException("Different dimension");
        }
        
        for (int i = 1; i <= A.row; i++)
            for (int j = 1; j <= A.col; j++)
                if (A.Elmt(i,j) != B.Elmt(i,j)){
                	return false;
                }
        return true;
    }
	
	
	/*** MATRIX MATH OPERATION ***/
	
    public Matrix plus(Matrix B) {
    	/*return C = A + B */
    	
        Matrix A = this;
        Matrix C = new Matrix(A.row, A.col);
        
        if (!A.eqDim(B)){
        	throw new RuntimeException("Different dimension");
        }
               
        for (int i = 1; i <= A.row; i++)
            for (int j = 1; j <= A.col; j++)
                C.matrix[i][j] = A.Elmt(i,j) + B.Elmt(i,j);
        return C;
    }


    public Matrix minus(Matrix B) {
        /* return C = A - B */
    	
        Matrix A = this;
        Matrix C = new Matrix(A.row, A.col);
        
        if (!A.eqDim(B)) {
        	throw new RuntimeException("Different dimension");
        }
        
        for (int i = 1; i <= A.row; i++)
            for (int j = 1; j <= A.col; j++)
                C.matrix[i][j] = A.Elmt(i,j) - B.Elmt(i,j);
        return C;
    }
     
    
    public Matrix times(Matrix B) {
    	/* return C = A * B  */
    	
        Matrix A = this;
        Matrix C = new Matrix(A.row, B.col);
        
        if (!A.eqDim(B)) {
        	throw new RuntimeException("Different dimension");
        }
        
        for (int i = 1; i <= C.row; i++)
            for (int j = 1; j <= C.col; j++)
                for (int k = 1; k <= A.col; k++)
                    C.matrix[i][j] += (A.Elmt(i,k) * B.Elmt(k,j));
        return C;
    }

    /*** OTHER METHOD ***/
	private void swap(int i, int j) {
		/* Swap 2 rows from matrix */
		
        double[] temp;
        
        temp = matrix[i];
        matrix[i] = matrix[j];
        matrix[j] = temp;
    }
	
	
	public Matrix transpose() {
		/* Create a transpose matrix */
        
		Matrix A = new Matrix(row, col);
        
        for (int i = 1; i <= this.row; i++)
            for (int j = 1; j <= this.col; j++)
                A.matrix[j][i] = this.Elmt(i,j);
        return A;

	}
	
	public static Matrix random(int M, int N) {
		/* Create random matrix */
		
		Matrix A = new Matrix(M, N);
        
		for (int i = 1; i <= M; i++)
            for (int j = 1; j <= N; j++)
                A.matrix[i][j] = 1+Math.random()*10;
        return A;
	}
	
	public static Matrix unsolvable(int M, int N,int X) {
		/* Create unsolvable matrix */
		
        Matrix A = new Matrix(M, N);
        for (int i = 1; i <= M-X; i++)
            for (int j = 1; j <= N; j++)
                A.matrix[i][j] = Math.round(Math.random()*5);
        
        for (int i = M-X+1; i <= M; i++){
        	for (int j = 1; j <= N; j++)
            A.matrix[i][j] = 0;
        }
        return A;
	}
	
	public static Matrix interpolate(Matrix func, int deg) {
		/* create interpolate matrix 
		 * func = matrix of function (n rows, 1 col of x)
		 * deg = degree of polynomials
		 */
		
		Matrix A = new Matrix(deg+1, deg+1);
		A.Solution_type = 4;
		
		for (int i = 1; i <= deg+1; i++){
			A.matrix[i][1] = 1;					// asign 1 to all first column
			A.matrix[i][2] = func.Elmt(i,1); // copy the value of func into second column
            for (int j = 3; j <= deg+1; j++){
                A.matrix[i][j] = java.lang.Math.pow(A.Elmt(i,2), j-1);
            }
		}
		
		return A;
	}
	        
	public void solve(Matrix result) {
		
		int SolutionType = this.Solution_type; //  1. single solution    2.multi solution    3.no solution   4.interpolate
		File file = new File("Solusi_SPL.txt"); /* nama file yang akan di save */
    	BufferedWriter writer = null;
    	
    	// Open file
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		}
		catch (FileNotFoundException e){
			System.err.println("Exception : " + e.toString());
		}
        
        // Create copies of the data
        Matrix a = new Matrix(this); 	// typically the 'equation' part
        Matrix b = new Matrix(result);  // typically the 'augmented' part

        // Check the matrix dimension
        if (this.col != this.row || result.row != this.col || result.col != 1)
            throw new RuntimeException("Illegal matrix dimensions.");
        
        // Show matrix before solving
        save("Augmented matrix : ", writer);
        save("\n", writer);
        a.showEx(b, writer);
        save("\n", writer);
          
        
        // Gaussian elimination with partial pivoting
        for (int i = 1; i <= col; i++) {

            // find pivot row and swap
            int max = i;
            for (int j = i + 1; j <= a.col; j++)
                if (Math.abs(a.Elmt(j,i)) > Math.abs(a.Elmt(max,i))){
                    max = j;
                }
            a.swap(i, max); 
            b.swap(i, max);          
                       
            	
            // Making echelon form
            double m = 1;
            for (int j = i + 1; j <= a.row; j++) {
            	int indent = 0;
                
            	// Finding multiplier
                if(a.Elmt(i,i) != 0){ 	// If main diagonal is not zero
                	m = a.Elmt(j,i) / a.Elmt(i,i);
                }
                else{  // If the main diagonal is zero, do indenting
                	for(int l = 0; i+l <= a.col; l++){
                  		if (a.Elmt(i,i+l) != 0){
                  			m = a.Elmt(j,i+l) / a.Elmt(i,i+l);
                  			indent = l;
                  			break;
                  		}
                	}
                }               
                
                // Reducing using multipier
                for (int k = i+1; k <= a.col; k++) {
                	a.matrix[j][k] -= a.Elmt(i,k) * m;
                }
                
                // Make the rest of column zero
                for(int l = 0; l<= i+indent; l++){
                	a.matrix[j][i] = 0.0;
                }
                b.matrix[j][1] -= b.Elmt(i,1) * m;  
            }	
        }

        // If matrix is not interpolated, find the solution type
    	if (SolutionType != 4){
    		for(int i = 1; i <= a.row; i++){
    			/* Check for each row */
    			
    			SolutionType = 0;
	    		for(int k = 1; k <= a.col; k++){
					if (a.Elmt(i,k) != 0.0){  // If there's non-zero element
						SolutionType = 1;
					}
	    		}
				
	    		if (SolutionType != 1){	// If there's a complete zero row
	    			if (b.Elmt(i,1) == 0.0){ // If the result row also zero
	    				SolutionType = 2;		// Multisolution
	    			}
	    			else{						// If the result row is not zero
	    				SolutionType = 3;		// Unsolvable
	    			}
	    		}
	    		
	    		if (SolutionType == 2 || SolutionType == 3){
	    			break;
	    		}
    		}
    	}
        	
    	
        Matrix x = new Matrix(a.col, 1);
    		
        // Display solution according to what type of solution matrix has (type 1 /2 /3 /4 ) 
	    if (SolutionType == 1 || SolutionType == 4){ // Single solution or interpolate
	    	
	    	for (int j = a.col; j > 0; j--) {
	    		
	            double t = 0.0;
	            
	            for (int k = j; k <= a.col; k++)
	                t += a.Elmt(j,k) * x.Elmt(k,1);
	            x.matrix[j][1] = (b.Elmt(j,1) - t) / a.Elmt(j,j);
	        }
	    	
	    	if (SolutionType == 1){	// standard linear equation
	    		x.showFinal(writer);
	    	}
	    	
	    	else if (SolutionType == 4){ // interpolated
	    		x.showPolynom(writer);
	    	}
        }
        
        else if (SolutionType == 2){ // Multi solution
        	
        	char []var = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t'};
        	String input;
        	int []problem = new int [25];
        	
        	  	
        	// Reduce the matrix to echelon for non-problematical row 
        	for (int j = a.col; j > 0; j--) {
        		
        		double divider = 1;
	        	
	        	// Finding divider
	            if(a.Elmt(j,j) != 0){	// If the main diagonal is not zero, use it
	            	divider = a.Elmt(j,j);
	            }
	            else{	// do indentation
	            	for(int l = 0; j+l <= a.col; l++){
	              		if (a.Elmt(j,j+l) != 0){
	              			divider = a.Elmt(j,j+l);
	              			break;
	              		}
	            	}
	            }  
	            
	            // for every column, divide the element by the divider 
	            for (int k = j; k <= a.row; k++){
	            	a.matrix[j][k] =  a.Elmt(j,k) / divider;
	            }
	            b.matrix[j][1] = (b.Elmt(j,1)) / divider;
	        }
        	
        	// At this point, the matrix should be in reduced echelon form
        	
        	// Listing equations which should be changed into parameters    	
        	int indent = 0;
        	for (int k = 1; k + indent <= a.col; k++) {
	            if (a.Elmt(k, k+indent) == 0 ){
	            	problem[k+indent] = 0;
	            	while((a.Elmt(k, k+indent) == 0) && (k+indent < a.col)){
	            		indent++;
	            	}
	            	if (k+indent < a.col){
	            		problem[k+indent] = k+indent;
	            	}
	            }
	            else{
	            	problem[k+indent] = k+indent;
	            }
	        }

        	// Expand (Not a valid algorithm, but it's usable #teehee)
        	// The idea is to make all '1' align in the main diagonal
        	// ignore the bad naming of variable
        	Matrix exp1 = new Matrix(a);
        	Matrix exp2 = new Matrix(b);
        	
        	// Check if there's problematical row, 
        	for(int xx = 1; xx <= a.row; xx++){
        		if (problem[xx] == 0){ 	// if the row is 'problematic', shift it down
        			for(int yy = a.row; yy > xx; yy--){ 	// loop from bottom
        				for(int zz = 1; zz <= a.col; zz++){	// shift every element downward
        					exp1.matrix[yy][zz] = exp1.Elmt(yy-1, zz);
        					exp1.matrix[yy-1][zz] = 0; 
        				}
        				exp2.matrix[yy][1] = exp2.Elmt(yy-1, 1);  // don't forget to shift the result part as well
        			}
        		}
        	}
        	a = exp1;
        	b = exp2;
        	// At this point, a and b is new 'expanded' matrix
        	
        	// Show matrix as parameter 
        	for (int i = 1; i <= a.row; i++){
        		
        		input = String.format("X%d = ",i);
        		save(input, writer);
        		
        		if(problem[i] != 0){
                	/* If it's not problematic, write equations  */
        			
        			input = String.format(format+"+",b.Elmt(i,1));
        			save(input, writer);
        			
        			for(int j = i+1; j < a.col; j++){ 
            			if(problem[j] != 0){ // if the column is not parametric column
            				input = String.format(format+"X%d + ",-a.Elmt(i, j),j);
            				save(input, writer);
            			}
            			else{	// write as parameter instead
            				input = String.format(format+"%c +",-a.Elmt(i, j),var[j]);
                			save(input, writer);
            			}
            		}
            		
        			// Write last element (without '+' at the end)
            		if(problem[a.col] != 0){
        				input = String.format(format+"X%d ",-a.Elmt(i, a.col),a.col);
        				save(input, writer);
        			}
        			else{
        				input = String.format(format+"%c",-a.Elmt(i, a.col),var[a.col]);
            			save(input, writer);
        			}
        		}
        		
        		else{
                	/* If it's problematic, write as parameter */
        			input = String.format("%5c",var[i]);
            		save(input, writer);
        		}
        		save("\n", writer);;
        	}    
        }
	    
        else if (SolutionType == 3){ // No solution
        	
        	String input = String.format("Matrix unsolvable");
        	save(input, writer);
        }
	    
	    // Last, close the file 
    	try {
			if( writer != null) writer.close();
		}
		catch (IOException e){
			System.err.println("Exception : " + e.toString());	
		}
    }
	
	
	/*** MATRIX MANUAL INPUT ***/
	void fillMatrix(){
	/* Isi matriks dengan input user */
	
		int i,j;
		double isi;
		Scanner scan = new Scanner(System.in);

		System.out.print("Masukkan baris matriks : "); 
		int row = scan.nextInt(); //input baris
		System.out.print("Masukkan kolom matriks : ");
		int col = scan.nextInt(); //input kolom

		this.row = row;
		this.col = col;
		this.matrix = new double[this.row+1][this.col+1];

		/* isi matriks */
		for(i=1;i<=this.row;i++){
			for(j=1;j<=this.col;j++){
				System.out.print("Input matrix[" + (i) + "][" + (j) + "] : ");
				isi = scan.nextDouble();
				this.matrix[i][j] = isi;
			}
		}
	}
	
	void fillMatrix(int row, int col){
	/* Isi matriks dengan input user */
	
		int i,j;
		double isi;
		Scanner scan = new Scanner(System.in);
		
		this.row = row;
		this.col = col; //matrix dibuat ukuran sesuai input
		this.matrix = new double[this.row+1][this.col+1];

		/* isi matriks */
		for(i = 1; i <= this.row; i++){
			for(j = 1; j <= this.col; j++){
				System.out.print("Input matrix[" + (i) + "][" + (j) + "] : "); 
				isi = scan.nextDouble();
				this.matrix[i][j] = isi;
			}
		}
	}
	
	
	/*** MATRIX USING EXTERNAL FILE ***/
	void fillMatrixExt(Matrix M2){
		/*baca baris, kolom, dan isi matrix dari file ext */
		
		File file = new File("Matrix.txt"); //file external di load ke program
		BufferedReader reader = null; //deklarasi object reader per byte
		
		try
		{
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			int i=1,j;
			String [] numbers = null; //nampung angka yang akan diassign ke program
			String line = null;

			/* Baca file*/
			while((line = reader.readLine()) != null){ //baca file sampai habis
				/* baca baris */
				/*------------------------------------*/
				if(line.equals("NUMBER_OF_ROW")){ //ketemu mark
				
					line = reader.readLine(); //baca satu baris
					numbers = line.split(" "); //buang spasi
					this.row = Integer.valueOf(numbers[0]); //assign angka ka baris objek
				}
				/*------------------------------------*/

				/* baca kolom */
				/*------------------------------------*/
				else if(line.equals("NUMBER_OF_COLUMN")){ //ketemu mark
				
					line = reader.readLine(); //baca satu baris
					numbers = line.split(" "); //buang spasi
					this.col = Integer.valueOf(numbers[0]); //assign angka ke kolom objek
				}
				/*-----------------------------------*/

				/* baca isi matriks */
				/*-----------------------------------*/
				else if(line.equals("MATRIX")){ //ketemu mark
				
					this.matrix = new double[this.row+1][this.col+1]; //buat matriks sesuai dengan ukuran yang sudah dibaca dari file ext
					M2.matrix = new double[this.row+1][2];
					while((line = reader.readLine()) != null) //baca sampai file habis
					{
						numbers = line.split(" "); //buang spasi
						for(j=1;j<=this.col;j++){
							this.matrix[i][j] = Double.parseDouble(numbers[j-1].trim()); //assign isi matriks ke dalam program	
						}		
						
						M2.matrix[i][1] = Double.parseDouble(numbers[j-1].trim()); //assign isi matriks ke dalam program		
						i++;
					}
					M2.row = this.row;
					M2.col = 1;

				}
				/*-----------------------------------*/
			}
			/*-------------------------------------------------------------------------*/
		}
		catch (FileNotFoundException e) //kalau file external tidak ada
		{
			System.err.println("Exception : " + e.toString());
		}
		catch (IOException e)
		{
			System.err.println("Exception : " + e.toString());	
		}
		finally
		{
			if(reader != null)
			{
				try
				{
					reader.close();
				}
				catch(IOException e)
				{
					System.err.println("Exception : " + e.toString());
				}
			}
		}
	}
	

	static void save(String input, BufferedWriter writer){
	/* save string ke file external */
		
		try {		
			if (input == "\n"){
				System.out.println();
				writer.newLine();
			}
			else{
				System.out.print(input);
				writer.write(input);	
			}
		}
		catch (FileNotFoundException e) {
			System.err.println("Exception : " + e.toString());
		}
		catch (IOException e) {
			System.err.println("Exception : " + e.toString());	
		}
	}

}