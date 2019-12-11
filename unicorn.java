class unicorn {
    /**
     * Binary Exponentiation Method
     * To calculate Power
     * 
     * @param base Base
     * @param exp Exponent
     * @return The power
     */
    public long binPower(long base, long exp) {
        long res = 1;
        while (exp > 0) {
            if (exp % 2 == 1) res *= base;
            base = base * base;
            b /= 2;
        }
        return b;
    }
}