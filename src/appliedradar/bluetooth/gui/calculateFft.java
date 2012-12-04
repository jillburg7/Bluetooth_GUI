package appliedradar.bluetooth.gui;

public class calculateFft {
	int n, m;

	// Lookup tables.  Only need to recompute when size of FFT changes.
	double[] cos;
	double[] sin;

	public calculateFft(int n) {
		this.n = n;
		this.m = (int)(Math.log(n) / Math.log(2));

		// Make sure n is a power of 2
		if(n != (1<<m))
			throw new RuntimeException("FFT length must be power of 2");

		// precompute tables
		cos = new double[n];
		sin = new double[n];
		for(int i=0; i<n; i++) {
			cos[i] = Math.cos(-2*Math.PI*i);
			sin[i] = Math.sin(-2*Math.PI*i);

		}
		
	}

	public void fft(double[] x, double[] y)
	{
		int i,j,k,n1,n2,a;
		double c,s,t1,t2;


		// Bit-reverse
		j = 0;
		n2 = n/2;
		for (i=1; i < n - 1; i++) {
			n1 = n2;
			while ( j >= n1 ) {
				j = j - n1;
				n1 = n1/2;
			}
			j = j + n1;

			if (i < j) {
				t1 = x[i];
				x[i] = x[j];
				x[j] = t1;
				t1 = y[i];
				y[i] = y[j];
				y[j] = t1;
			}
		}

		// FFT
		n1 = 0;
		n2 = 1;

		for (i=0; i < m; i++) {
			n1 = n2;
			n2 = n2 + n2;
			a = 0;

			for (j=0; j < n1; j++) {
				c = cos[a];
				s = sin[a];
				a +=  1 << (m-i-1);

				for (k=j; k < n; k=k+n2) {
					t1 = c*x[k+n1] - s*y[k+n1];
					t2 = s*x[k+n1] + c*y[k+n1];
					x[k+n1] = x[k] - t1;
					y[k+n1] = y[k] - t2;
					x[k] = x[k] + t1;
					y[k] = y[k] + t2;
				}
			}
		}
	}

	public static double[] main(String[] args) {
		int N = 32;

		calculateFft fft = new calculateFft(N);

		double[] re = new double[N];
		double[] im = new double[N];

		//		System.out.println((0.5)*N);
		// Single sin		
		for(int i=0; i<N; i++) {
			re[i] = 2*Math.cos(3*2*Math.PI*i/N);
			im[i] = 0;
		}
		//		beforeAfter(fft, re, im);

		//		protected static void beforeAfter(FFT fft, double[] re, double[] im) {
		//			System.out.println("Before: ");
		//			printReIm(re, im);
		fft.fft(re, im);
		//			System.out.println("After: ");
		//			printReIm(re, im);
		//		}

			//	public printReIm(double[] re, double[] im) {
		//			System.out.print("Re: [");
					for(int i=0; i<re.length; i++)
						re[i]=((int)(re[i]*1000)/1000.0);
					
					for(int i=0; i<im.length; i++)
						im[i]=((int)(im[i]*1000)/1000.0);
		//				System.out.print(((int)(re[i]*1000)/1000.0) + " ");
		//
		//			System.out.print("]\nIm: [");
		//			for(int i=0; i<im.length; i++)
		//				System.out.print(((int)(im[i]*1000)/1000.0) + " ");
		//
		//			System.out.println("]");
			//	}
	return re;
	}
}
