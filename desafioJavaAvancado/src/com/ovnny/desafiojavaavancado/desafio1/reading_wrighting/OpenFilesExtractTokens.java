package com.ovnny.desafiojavaavancado.desafio1.reading_wrighting;

import java.io.*;

public class OpenFilesExtractTokens {
    public static void main(String[] args) {
       try {
           File inputFile = new File("/home/ovnny/Desktop/github/InterBootcamp-DIO/desafioJavaAvancado/src/com/ovnny/desafiojavaavancado/desafio1/shakespeare.txt");
           File outputFile = new File("/home/ovnny/Desktop/github/InterBootcamp-DIO/desafioJavaAvancado/src/com/ovnny/desafiojavaavancado/desafio1/sample.txt");

           FileInputStream fis = new FileInputStream(inputFile);
           FileOutputStream fos = new FileOutputStream(outputFile);
           int c;

           while((c = fis.read()) != -1) {
               fos.write(c);
           }
           fis.close();
           fos.close();
       } catch (IOException e) {
           System.out.println("FileStreamsTest "+ e);
       }
    }


}
