package com.ovnny.functionaljavaprogramming.threads;

public class ThreadExemplo {

    public static void main(String[] args) {
        BarraDeCarregamento2 barraDeCarregamento2 = new BarraDeCarregamento2();
        barraDeCarregamento2.start();
    }

}

class GerarPDF implements  Runnable {
    @Override
    public void run() {
        System.out.println("Gerar PDF");
    }
}

class BarraDeCarregamento implements Runnable {
    @Override
    public void run() { System.out.println("Loading ..."); }
}

class BarraDeCarregamento2 extends  Thread {
    @Override
     public void run() {
        super.run();
        System.out.println("rodei" + this.getName());
    }

}
