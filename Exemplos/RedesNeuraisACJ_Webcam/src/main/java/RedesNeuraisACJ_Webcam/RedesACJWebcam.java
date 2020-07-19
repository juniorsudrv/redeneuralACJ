/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RedesNeuraisACJ_Webcam;

import Biblis.MemoryUtils;
import OpIO.Dados;
import OpIO.IO;
import com.github.sarxos.webcam.Webcam;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import meupneuronio.MeuAJC;
import meupneuronio.NeuronioAJC;

/**
 *
 * @author junior
 */
public class RedesACJWebcam extends javax.swing.JFrame {

    int xI=0,yI=0;
    BufferedImage imagem=null;
    Webcam webcam =null ;
 
    String result="";
    int nresult=1000;
    boolean coletaid0=false, coletaid1=false;
    boolean reconhece=false;
    File temp=null;
    RecorteAtual rec=null;
    ArrayList<Dados> redes=new ArrayList<Dados>();
    
    public RedesACJWebcam() {
        rec=new RecorteAtual();
        rec.setVisible(true);
        initComponents();
        painel.setVisible(false);
    temp=new File("temp.png");   
        try {
            temp.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(RedesACJWebcam.class.getName()).log(Level.SEVERE, null, ex);
        }
    new Thread(new Runnable() {
            @Override
            public void run() {
        bt_treinar.setEnabled(false);
  ;
   webcam = Webcam.getDefault();
       treino_progress.setValue(10);
    webcam.open();
       treino_progress.setValue(20);
atualizaColetas();
   treino_progress.setValue(60);
bt_reconhecer.setBackground(Color.YELLOW);
      try{
      redes=(ArrayList<Dados>) IO.ler("minharede");
      }catch(Exception e){
          
          redes=new ArrayList<Dados>();
      
      
      }
      preencheRedes();
   treino_progress.setValue(100);

  bt_treinar.setEnabled(true);
  
  
          painel.setVisible(true);

            }
    }
    ).start();
 

 new Thread(new Runnable() {
       @Override
       public void run() {
while(true){
memoria.setText((int)MemoryUtils.usedMemory() + "  " + MemoryUtils.maxMemory());

    try {
        Thread.sleep(800);
    } catch (InterruptedException ex) {
        
    }
}       }
   }).start();

 
 
    }
    
    
    class prancha_camera_impl extends JPanel{ 
    Color[] colors = {Color.YELLOW, Color.GREEN, Color.BLUE, Color.BLACK, Color.WHITE};
    BufferedImage local=null;
    boolean setFont=true;
    
    @Override
    public void paintComponent(Graphics g){
    
        
        if(webcam!=null&&(local =webcam.getImage())!=null)
    {    
    
         imagem=  MeuAJC.image2BlackWhiteTest(local) ;
                
      g.drawImage(imagem, 0, 0, pracha_camera);
      rec.setImagem_recorte(imagem.getSubimage(xI, yI,
                    Integer.parseInt(largura.getText()),Integer.parseInt(altura.getText())));
    }else{
    
        try {
            Thread.sleep(800);
        } catch (InterruptedException ex) {
            Logger.getLogger(RedesACJWebcam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    try{
        if(reconhece){
      g.setFont(new Font(Font.SERIF, Font.BOLD, 18));  
    g.setColor(result.contains("1")?
            ((colors.length<=nresult||nresult==-1)?colors[new Random().nextInt(colors.length)]
            :colors[nresult]):Color.RED);
        
    g.drawString(resultado.getText(), xI, yI-20);
   
        }
        g.drawRect(xI, yI, Integer.parseInt(largura.getText()),Integer.parseInt(altura.getText()));
    }catch(Exception e){}
   
    
           pracha_camera. repaint();
    
    }
 
    
    }

     
    
    
    
    public void preencheRedes(){
    
    
    comb_redes.removeAllItems();
    if(redes==null)return;
    for(Dados rede:redes){
    
    comb_redes.addItem(rede.n1);
    }
    
    }
    
    
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pracha_camera = new prancha_camera_impl();
        painel = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        largura = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        altura = new javax.swing.JTextField();
        memoria = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        comb_redes = new javax.swing.JComboBox<>();
        bt_reconhecer = new javax.swing.JButton();
        resultado = new javax.swing.JTextField();
        numero_coleta_rede = new javax.swing.JLabel();
        bt_coletarid0 = new javax.swing.JButton();
        numero_coletaid0 = new javax.swing.JLabel();
        numero_coletaid1 = new javax.swing.JLabel();
        bt_coletarid1 = new javax.swing.JButton();
        treino_progress = new javax.swing.JProgressBar();
        bt_treinar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        neuronios = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pracha_camera.setBorder(javax.swing.BorderFactory.createLineBorder(null));
        pracha_camera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pracha_cameraMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout pracha_cameraLayout = new javax.swing.GroupLayout(pracha_camera);
        pracha_camera.setLayout(pracha_cameraLayout);
        pracha_cameraLayout.setHorizontalGroup(
            pracha_cameraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pracha_cameraLayout.setVerticalGroup(
            pracha_cameraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 536, Short.MAX_VALUE)
        );

        painel.setBorder(new javax.swing.border.MatteBorder(null));

        jButton4.setText("Limpar Rede");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(null));

        largura.setText("110");
        largura.setEnabled(false);

        jLabel1.setText("Largura");

        jLabel2.setText("Altura");

        altura.setText("150");
        altura.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(largura, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(altura, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(largura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel1)
                .addComponent(jLabel2)
                .addComponent(altura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jButton1.setText("Add RedeN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Del RedeN");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        comb_redes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comb_redes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comb_redesActionPerformed(evt);
            }
        });

        bt_reconhecer.setText("Reconhecer");
        bt_reconhecer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_reconhecerActionPerformed(evt);
            }
        });

        numero_coleta_rede.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        numero_coleta_rede.setText("0");
        numero_coleta_rede.setBorder(javax.swing.BorderFactory.createLineBorder(null));

        bt_coletarid0.setText("ColetarFundo");
        bt_coletarid0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_coletarid0ActionPerformed(evt);
            }
        });

        numero_coletaid0.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        numero_coletaid0.setText("0");
        numero_coletaid0.setBorder(javax.swing.BorderFactory.createLineBorder(null));

        numero_coletaid1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        numero_coletaid1.setText("0");
        numero_coletaid1.setBorder(javax.swing.BorderFactory.createLineBorder(null));

        bt_coletarid1.setText("Coletar Treino");
        bt_coletarid1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_coletarid1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelLayout = new javax.swing.GroupLayout(painel);
        painel.setLayout(painelLayout);
        painelLayout.setHorizontalGroup(
            painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelLayout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(memoria, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(painelLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numero_coleta_rede, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painelLayout.createSequentialGroup()
                        .addComponent(bt_reconhecer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resultado, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(comb_redes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(numero_coletaid0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_coletarid0))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(numero_coletaid1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_coletarid1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelLayout.setVerticalGroup(
            painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(memoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
            .addGroup(painelLayout.createSequentialGroup()
                .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelLayout.createSequentialGroup()
                        .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(comb_redes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton1)
                                .addComponent(jButton2)
                                .addComponent(numero_coleta_rede))
                            .addGroup(painelLayout.createSequentialGroup()
                                .addComponent(bt_coletarid0)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(numero_coletaid0)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bt_reconhecer)
                            .addComponent(resultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(painelLayout.createSequentialGroup()
                        .addComponent(bt_coletarid1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(numero_coletaid1)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        bt_treinar.setText("Treinar");
        bt_treinar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_treinarActionPerformed(evt);
            }
        });

        jLabel3.setText("Neuronios");

        neuronios.setText("12");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pracha_camera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(bt_treinar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(treino_progress, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(neuronios, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pracha_camera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bt_treinar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(treino_progress, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(neuronios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_coletarid0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_coletarid0ActionPerformed
 
 coletaid0=!coletaid0;
 
 if(coletaid0){
 bt_coletarid0.setText("Parar...Coleta");
 }else{
 
  bt_coletarid0.setText("ColetarFundo");
 }
new Thread(new Runnable() {
     @Override
     public void run() {
comb_redes.setEnabled(false);
while(coletaid0){

try {
            ImageIO.write(imagem.getSubimage(xI, yI,
                    Integer.parseInt(largura.getText()),Integer.parseInt(altura.getText())),
                    "png", new File("IMG0/IMG"+System.currentTimeMillis()+".png"));
        } catch (IOException ex) {
            Logger.getLogger(RedesACJWebcam.class.getName()).log(Level.SEVERE, null, ex);
        }


atualizaColetas();

}
comb_redes.setEnabled(true);
     }
 }).start();

        
 
    }//GEN-LAST:event_bt_coletarid0ActionPerformed

    private void pracha_cameraMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pracha_cameraMouseReleased
if(evt.getX()+Integer.parseInt(largura.getText())<=imagem.getWidth()&&
        evt.getY()+Integer.parseInt(altura.getText())<=imagem.getHeight()){
xI=evt.getX();
yI=evt.getY();  

}


// TODO add your handling code here:
    }//GEN-LAST:event_pracha_cameraMouseReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

if(!bt_treinar.isEnabled() 
        )return;

        reconhece=false;
if(reconhece){

bt_reconhecer.setText("Reconhecendo!");
bt_reconhecer.setBackground(Color.GREEN);
}else{
bt_reconhecer.setText("Reconhecer!");
bt_reconhecer.setBackground(Color.YELLOW);

} 
        
          


for(File file: new File("IMG0/").listFiles()) 
{
    if (!file.isDirectory()) 
{
    file.delete();
}
}
 for(File file: new File("IMG1/").listFiles()) 
{ 
    if (!file.isDirectory()) 
{
    file.delete();
}
}
 atualizaColetas();
 
 
for(int cont=0;cont<redes.size();cont++){

redes.get(cont).rede=null;

}
 
    }//GEN-LAST:event_jButton4ActionPerformed

    private void bt_coletarid1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_coletarid1ActionPerformed
  

 coletaid1=!coletaid1;
 
 if(coletaid1){
 bt_coletarid1.setText("Parar...Coleta");
 }else{
 
  bt_coletarid1.setText("Coletar Treino");
 }
new Thread(new Runnable() {
     @Override
     public void run() {
comb_redes.setEnabled(false);
while(coletaid1){

try {
            ImageIO.write(imagem.getSubimage(xI, yI,
                    Integer.parseInt(largura.getText()),Integer.parseInt(altura.getText())),
                    "png", new File("IMG1/"+comb_redes.getSelectedItem()+"_"+System.currentTimeMillis()+".png"));
        } catch (IOException ex) {
            Logger.getLogger(RedesACJWebcam.class.getName()).log(Level.SEVERE, null, ex);
        }
atualizaColetas();
}

comb_redes.setEnabled(true);

     }
 }).start();

    }//GEN-LAST:event_bt_coletarid1ActionPerformed

    private void bt_treinarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_treinarActionPerformed
reconhece=false;
if(reconhece){

bt_reconhecer.setText("Reconhecendo!");
bt_reconhecer.setBackground(Color.GREEN);
}else{
bt_reconhecer.setText("Reconhecer!");
bt_reconhecer.setBackground(Color.YELLOW);

}
        bt_treinar.setEnabled(false);



new Thread(new Runnable() {
    @Override
    public void run() {

 bt_treinar.setText("Treinando Rede...");
     
   treino_progress.setValue(0); 
LoadingTreino loading= new LoadingTreino(comb_redes.getItemCount());
   
    for (int nredes=0;nredes<comb_redes.getItemCount();nredes++)
    {
            
        loading.getProgressBar(nredes).setValue(0);
        iniciaNr(nredes);
      loading.getProgressBar(nredes).setValue(10);
      
     File IMG0 = new File("IMG0/");
     File IMG1 = new File("IMG1/");
     File[] arquivosIMG0 = IMG0.listFiles();
     File[] arquivosIMG1 = IMG1.listFiles();
 
 
   for (int cont = 0; cont < arquivosIMG0.length  ; cont++)
      { 
            
          
          redes.get(nredes).rede.setValorTreino(arquivosIMG0[cont ],    0);     
          }
 
          loading.getProgressBar(nredes).setValue(20);
    for (int cont = 0; cont <  arquivosIMG1.length; cont++)
    { 
        
     
  redes.get(nredes).rede.setValorTreino(arquivosIMG1[cont],    
          ((arquivosIMG1[cont].getName().split("_")[0].contentEquals(comb_redes.getItemAt(nredes)+"")?1:0))      
  );
  }   
   

        loading.getProgressBar(nredes).setValue(40);
  
  
    redes.get(nredes).rede.treinarRede();
       loading.getProgressBar(nredes).setValue(80);
 
        try {
            IO.inserir("minharede", redes);
        } catch (IOException ex) {
            Logger.getLogger(RedesACJWebcam.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
           loading.getProgressBar(nredes).setValue(100);
    }
    loading.dispose();
  bt_treinar.setText("Treino Completo");
bt_treinar.setEnabled(true);

    }
}).start();
       
       
    }//GEN-LAST:event_bt_treinarActionPerformed

    private void bt_reconhecerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_reconhecerActionPerformed
reconhece=!reconhece;
new Thread(new Runnable() {
    @Override
    public void run() {
        
        while(reconhece)
        { 
            try {
          
            ImageIO.write(imagem.getSubimage(xI, yI,
                    Integer.parseInt(largura.getText()),Integer.parseInt(altura.getText())),
                    "png", temp);
            
            boolean br=false;
            for(Dados ajc:redes){
                
                if(ajc.rede!=null){
           boolean r=ajc.rede.acaoAJCSimples(temp).contains(1+"");
            
            if(r){
          result=r?"1":"0";
                resultado.setText(r?ajc.n1:"IMG0/");
          nresult=redes.indexOf(ajc);
          br=true;
                break;
            }
                }
                }
            
            if(!br){
              resultado.setText("");
          nresult=-1;
            result="0";
            }
        } catch (IOException ex) {
            Logger.getLogger(RedesACJWebcam.class.getName()).log(Level.SEVERE, null, ex);
        }
        }


    }
}).start();
       
           
if(reconhece){

bt_reconhecer.setText("Reconhecendo!");
bt_reconhecer.setBackground(Color.GREEN);
}else{
bt_reconhecer.setText("Reconhecer!");
bt_reconhecer.setBackground(Color.YELLOW);
nresult=-1;
}

        
        
        
    }//GEN-LAST:event_bt_reconhecerActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed



redes.remove(comb_redes.getSelectedIndex());
        try {
            IO.inserir("minharede", redes);
            System.gc();
            redes=(ArrayList<Dados>) IO.ler("minharede");
            System.out.println(""+redes.size());
        } catch (IOException ex) {
            Logger.getLogger(RedesACJWebcam.class.getName()).log(Level.SEVERE, null, ex);
        }
preencheRedes();

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String n1=JOptionPane.showInputDialog("Digite o nome da rede");
        
        if(n1.length()<1||n1==null)return;
        Dados d=new Dados();
      d.n1=n1;
      if(redes==null)
      {
      redes=new ArrayList<Dados>();
      
      }
        redes.add(d);
       try {
            IO.inserir("minharede", redes);
        } catch (IOException ex) {
            Logger.getLogger(RedesACJWebcam.class.getName()).log(Level.SEVERE, null, ex);
        }
preencheRedes();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void comb_redesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comb_redesActionPerformed
        atualizaColetas_Item_Rede();

        // TODO add your handling code here:
    }//GEN-LAST:event_comb_redesActionPerformed

    
    
    public void atualizaColetas(){
    
    numero_coletaid0.setText(new File("IMG0/").listFiles().length+"");
       numero_coletaid1.setText(new File("IMG1/").listFiles().length+"");
       
       
       
       
       
    }
    
    
    public void atualizaColetas_Item_Rede(){
    
    
    int cont=0;
    
    for(File f:new File("IMG1/").listFiles()){
    
    if(f.getName().split("_")[0].contentEquals(comb_redes.getSelectedItem()+""))
    {
        cont++;
    }
    
    }
        numero_coleta_rede.setText(cont+"");

    
    }
    public void iniciaNr(int nrede){
    if(redes.get(nrede).rede==null){
    
        try {
            ImageIO.write(imagem.getSubimage(xI, yI,
                    Integer.parseInt(largura.getText()),Integer.parseInt(altura.getText())),
                    "png", temp);
                redes.get(nrede).rede=new   NeuronioAJC(25, 
                NeuronioAJC.lenghtImagem(temp));
        } catch (IOException ex) {
            Logger.getLogger(RedesACJWebcam.class.getName()).log(Level.SEVERE, null, ex);
        }
         

    }
    
    
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RedesACJWebcam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RedesACJWebcam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RedesACJWebcam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RedesACJWebcam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RedesACJWebcam().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField altura;
    private javax.swing.JButton bt_coletarid0;
    private javax.swing.JButton bt_coletarid1;
    private javax.swing.JButton bt_reconhecer;
    private javax.swing.JButton bt_treinar;
    private javax.swing.JComboBox<String> comb_redes;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField largura;
    private javax.swing.JTextField memoria;
    private javax.swing.JTextField neuronios;
    private javax.swing.JLabel numero_coleta_rede;
    private javax.swing.JLabel numero_coletaid0;
    private javax.swing.JLabel numero_coletaid1;
    private javax.swing.JPanel painel;
    private javax.swing.JPanel pracha_camera;
    private javax.swing.JTextField resultado;
    private javax.swing.JProgressBar treino_progress;
    // End of variables declaration//GEN-END:variables
}
