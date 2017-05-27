/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escalonador;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;

/**
 *
 * @author Rafael
 */
public class Inicio extends javax.swing.JFrame {
    DefaultListModel model;
    int numero = 0;
    
    public Inicio() {
        initComponents();
        Inserir_Lista();
        model = new DefaultListModel();
        ListaSelecionada.setModel(model);
    }
    
    private void Inserir_Lista(){
        DefaultListModel modelo = new DefaultListModel();
        modelo.add(0, "FCFS");//insere o nome dos escalonadores na lista
        modelo.add(1, "SJF");
        modelo.add(2, "SRTF");
        modelo.add(3, "ROUND ROBIN");
        modelo.add(4, "PRIORIDADES S/ PREEMPÇÃO");
        modelo.add(5, "PRIORIDADES C/ PREEMPÇÃO");
        Lista.setModel(modelo);
    }
    private void Inserir_ListaSelecionada(String fila){//funçao para add um escalonador na lista de selecionados
        if(ListaSelecionada.getModel().getSize() < numero){
            model.addElement(fila);
        }
    }
    private void Remover_ListaSelecionada(String fila){//funçao para remover um escalonador na lista de selecionados
        if(ListaSelecionada.getModel().getSize() > 0){
            model.removeElement(fila);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        NumeroFila = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Lista = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        ListaSelecionada = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        botao = new javax.swing.JButton();
        Inserir_Processos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Numero de Filas");

        Lista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ListaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Lista);

        ListaSelecionada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ListaSelecionadaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(ListaSelecionada);

        jLabel2.setText("Selecionar os escalonadores");

        jLabel3.setText("Escalonadores selecionados");

        botao.setText("OK");
        botao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoActionPerformed(evt);
            }
        });

        Inserir_Processos.setText("Inserir processos");
        Inserir_Processos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Inserir_ProcessosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(NumeroFila, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botao))
                            .addComponent(Inserir_Processos))
                        .addGap(0, 320, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NumeroFila, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Inserir_Processos)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ListaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaMouseClicked
        Inserir_ListaSelecionada(Lista.getSelectedValue());//chama funçao para add escalonador
        ListaSelecionada.setModel(model);
    }//GEN-LAST:event_ListaMouseClicked

    private void ListaSelecionadaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaSelecionadaMouseClicked
        Remover_ListaSelecionada(ListaSelecionada.getSelectedValue());//chama funçao para remover escalonador
        ListaSelecionada.setModel(model);
    }//GEN-LAST:event_ListaSelecionadaMouseClicked

    private void botaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoActionPerformed
        numero = Integer.parseInt(NumeroFila.getText());//numero de escalonadores
    }//GEN-LAST:event_botaoActionPerformed

    private void Inserir_ProcessosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Inserir_ProcessosActionPerformed
        Insercao insere = new Insercao(model);
        insere.setVisible(true);//chama novo frame e apaga este frame
        this.dispose();
    }//GEN-LAST:event_Inserir_ProcessosActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Inserir_Processos;
    private javax.swing.JList<String> Lista;
    private javax.swing.JList<String> ListaSelecionada;
    private javax.swing.JTextField NumeroFila;
    private javax.swing.JButton botao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
