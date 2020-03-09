class test{
    public static main(String[] args){
        
    }

    public static void count(int n){
        if(n > 2)
            test(n-1);
        System.out.println("n="+n);
    }

    public static int factorial(int n){
        if(n == 1)
            return 1;
        else
            return factorial(n - 1) * n;
    }
}