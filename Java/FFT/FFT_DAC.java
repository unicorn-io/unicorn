/**
 * The following class is used to calculate FFT 
 * Prerequisites: Complex class (predefined type for complex numbers)
 * 
 * Reference: https://www.cs.princeton.edu/~wayne/kleinberg-tardos/pdf/05DivideAndConquerII.pdf,
 *            Digital Signal Processing 3E, Prokis
 */
public class FFT_DAC {
    
    /**
     * This is the implementation of radix-2 FFT
     * Radix-2 is onw of the most used algorithms in FFTs
     * Algortihm:
     * 1. Divide the Signal in Even and Odd parts
     * 2. Keep Dividing untill single point DFT's are obtained
     * 3. At this point start multiplying with the particualr phase factor
     * 4. Combine them accordingly with the equations
     *          X(k) = Even(k) + W_N^k*Odd(k)
     *          X(k + N/2) = Even(k) + W_N^k*Odd(k)
     * 5. At the last iteration the result is returned in a y array. 
     * 
     * 
     * @param The Signal to be Transformed
     * @return The Fourier Transform of the given array
     */
    public static Complex[] fft(Complex[] x) {
        int n = x.length;

          // base case
          if (n == 1) return new Complex[] { x[0] };


          Complex[] padded;
          // radix 2 Cooley-Tukey FFT
          if (n % 2 != 0) {
            int padding = (int)Math.ceil(Math.log(n/2)/Math.log(2));
            padded = new Complex[n + padding];
            for (int i = 0; i < n + padding; i++) {
                if (i < n) padded[i] = x[i];
                else padded[i] = new Complex();  
            }
            x = padded;
          }
  

        Complex[] even = new Complex[n/2];
        for (int k = 0; k < n/2; k++) {
            even[k] = x[2*k];
        }
        Complex[] evenFft = fft(even);

        Complex[] odd = even;
        for (int k = 0 ; k < n/2; k++) {
            odd[k] = x[2*k+1];
        }
        Complex[] oddFft = fft(odd);

        Complex[] y = new Complex[n];
        for (int k = 0; k < n/2; k++) {
            double kth = -2*k*Math.PI/n;
            Complex wk = new Complex(Math.cos(kth), Math.sin(kth));
            y[k] = evenFft[k].plus(wk.times(oddFft[k]));
            y[k+(n/2)] = evenFft[k].minus(wk.times(oddFft[k])); 
        }
        return y;
    }

    public static Complex[] ifft(Complex[] x){
        int n = x.length;
        Complex[] y = new Complex[n];

        
    }
    
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Complex[] x = new Complex[n];

        // original data
        for (int i = 0; i < n; i++) {
            x[i] = new Complex(i, 0);
        }
        Complex[] y = fft(x);
        for (Complex k : y) System.out.println(k);
    }
}