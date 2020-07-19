/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOR;

import meupneuronio.NeuronioAJC;

/**
 *
 * @author junio
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         NeuronioAJC nr = new NeuronioAJC(3,2);
         
         nr.setValorTreino(0, 0);
         nr.setValorTreino(1, 1);
         nr.setValorTreino(2, 1);
         nr.setValorTreino(3, 0);
         nr.treinarRede();
         
         System.out.println(
         nr.acaoAJCSimples(2)
         );
         
    }
    
}
