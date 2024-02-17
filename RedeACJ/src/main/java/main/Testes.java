/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import OpIO.IO;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import meupneuronio.NeuronioAJC;

/**
 *
 * @author junior
 */
public class Testes {

    static int PAR = 0, IMPAR = 1;

    public static void main(String[] args) {

        try {
            //Sa�das 0 ou 1
            //0 Par
            //1 Impar
            //Numeros Imagens, vais convertido pra bits 2decimal 10bits 0 ou 1
            //2 000000000010

            NeuronioAJC nr = new NeuronioAJC( 5);

            nr.setValorTreino(0, 2);
            nr.setValorTreino(1, -2);
            nr.setValorTreino(2, -2);
            nr.setValorTreino(3, 2);
           
           nr.treinarRedePS(1);

            System.out.println("" + nr.acaoPS(0));
            System.out.println("" + nr.acaoPS(1));
            System.out.println("" + nr.acaoPS(2));
            System.out.println("" + nr.acaoPS(3));
            if (true) {
                return;
            }
            Scanner sc = new Scanner(System.in);
            while (true) {
                boolean estado = false;
                int numero = 0;
                System.out.println("Digite um numero (inteiro) por favor,"
                        + "para encerrar o programa -1");
                numero = sc.nextInt();
                if (numero == -1) {
                    break;
                }

                System.out.println("Resultado da rede "
                        + nr.acaoAJCSimples(numero));

                if (numero % 2 == PAR && nr.acaoAJCSimples(numero).contains(IMPAR + "")) {
                    estado = false;
                } else {
                    estado = true;
                }

                System.out.println("\n\n\nA rede informa que este numero e  "
                        + (nr.acaoAJCSimples(numero).contains(IMPAR + "") ? "Impar" : "Par"));

                //ERRO treina 2 � par 3 � impar e 3 � par
                if (!estado) {
                    System.out.println("Eu errei vou treinar");
                    System.out.println("Treinando rede");
                    nr.setValorTreino(numero, (numero % 2 == 0) ? PAR : IMPAR);
                    //  nr.treinarRede();
                    System.out.println("Treino Finalizado ");
                }

            }

            IO.inserir("ParImpar", nr);//Salvar
            nr = (NeuronioAJC) IO.ler("ParImpar"); //Carregar
        } catch (IOException ex) {
            Logger.getLogger(Testes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
