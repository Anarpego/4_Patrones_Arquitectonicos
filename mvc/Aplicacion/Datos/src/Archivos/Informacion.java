package Archivos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Formatter;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class Informacion extends javax.swing.JFrame {

    String espacio = File.separator;
    
    String ubicacion = System.getProperty("user.dir") + espacio + "Vacunacion COVID19" + espacio; //los archivos se guardaran en la ubicacion donde esta el programa guardado
    
    
    public Informacion() {
        initComponents();
    }

    private void Ingresar()//metodo 1 para ingresar registros nuevos a la carpeta de base de datos
    {
        String archivo = tx01.getText() + ".txt";
        File crear_ubicacion = new File(ubicacion);
        File crear_archivo = new File(ubicacion + archivo);
        
        if(tx01.getText().equals(""))
        {
            JOptionPane.showMessageDialog(rootPane, "DPI no ingresado");
        }
            else
            {
                
                try
                {
                    if(crear_archivo.exists())
                    {
                        JOptionPane.showMessageDialog(rootPane, "Este DPI ya tiene registro");
                    }                
                        else
                        {
                            crear_ubicacion.mkdirs(); //sirve para crear las carpetas que se necesiten en la ruta del programa
                            Formatter crear = new Formatter(ubicacion + archivo); //se crea el archivo
                            
                            crear.format("%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s", 
                                         "DPI = " + tx01.getText(),
                                         "Nombres = " + tx02.getText(),
                                         "Apellidos = " + tx03.getText(),
                                         "Edad = " + tx04.getText(),
                                         "EstadoCivil = " + tx05.getText(),
                                         "Departamento = " + tx06.getText(),
                                         "Municipio = " + tx07.getText(),
                                         "Telefono = " + tx08.getText(),
                                         "Correo = " + tx09.getText(),
                                         "Vacuna = " + tx10.getText());
                            
                            crear.close();
                            
                            JOptionPane.showMessageDialog(rootPane, "El paciente " + tx01.getText() + " ha sido registrado correctamente");
                        }
                }
                    catch (Exception e)
                    {
                        JOptionPane.showMessageDialog(rootPane, "Error en el guardado de registro del paciente");
                    }                                 
                    
                
            }
    }
    
    private void Buscar()//metodo 2 para consultar la informacion de un archivo por medio del DPI
    {
        File url = new File(ubicacion + tx01.getText() + ".txt");
        
        if(tx01.getText().equals(""))
        {
            JOptionPane.showMessageDialog(rootPane, "Ingresa DPI del paciente para mostrar su informacion");
        }
            else
            {
                if(url.exists())
                {
                    try
                    {
                        FileInputStream fis = new FileInputStream(url);
                        Properties mostrar = new Properties();
                        mostrar.load(fis);
                        
                        tx02.setText(mostrar.getProperty("Nombres"));
                        tx03.setText(mostrar.getProperty("Apellidos"));
                        tx04.setText(mostrar.getProperty("Edad"));
                        tx05.setText(mostrar.getProperty("EstadoCivil"));
                        tx06.setText(mostrar.getProperty("Departamento"));
                        tx07.setText(mostrar.getProperty("Municipio"));
                        tx08.setText(mostrar.getProperty("Telefono"));
                        tx09.setText(mostrar.getProperty("Correo"));
                        tx10.setText(mostrar.getProperty("Vacuna"));
                    }
                        catch (Exception e)
                        {
                        
                        }
                }
                    else
                    {
                        JOptionPane.showMessageDialog(rootPane, "El registro no existe");
                    }
            }
    }
    
    private void Editar()//metodo 3 para cambiar la informacion de uno de los registros
    {
    
    File url = new File(ubicacion + tx01.getText() + ".txt");
    
        if(tx01.getText().equals(""))
        {
            JOptionPane.showMessageDialog(rootPane, "Ingresa el DPI que se editara su registro");
        }
            else
            {
                if(url.exists())
                {
                    try
                    {
                        FileWriter cambios = new FileWriter(ubicacion + tx01.getText() + ".txt");//FileWriter permite sobreescribir un archivo
                        
                        String DPI = "DPI = ";
                        String Nomb = "Nombres = ";
                        String Ape = "Apellidos = ";
                        String Ed = "Edad = ";
                        String EstCiv = "EstadoCivil = ";
                        String Depa = "Departamento = ";
                        String Muni = "Municipio = ";
                        String Tel = "Telefono = ";
                        String Cor = "Correo = ";
                        String Vac = "Vacuna = ";
                        
                        PrintWriter guardar = new PrintWriter(cambios);
                        
                        guardar.println(DPI + tx01.getText());
                        guardar.println(Nomb + tx02.getText());
                        guardar.println(Ape + tx03.getText());
                        guardar.println(Ed + tx04.getText());
                        guardar.println(EstCiv + tx05.getText());
                        guardar.println(Depa + tx06.getText());
                        guardar.println(Muni + tx07.getText());
                        guardar.println(Tel + tx08.getText());
                        guardar.println(Cor + tx09.getText());
                        guardar.println(Vac + tx10.getText());
                        
                        cambios.close();
                        
                        JOptionPane.showMessageDialog(rootPane, "Registro editado correctamente");
                        
                    }
                        catch (Exception e)
                        {
                            JOptionPane.showMessageDialog(rootPane, "Error" + e);
                        }
                }
                    else
                    {
                        JOptionPane.showMessageDialog(rootPane, "No existe registro con este DPI.");
                    }
                
                    
            }
    }
    
    private void Borrar()//metodo 4 para eliminar un registro por completo
    {
        File url  = new File(ubicacion + tx01.getText() + ".txt");
        
        String opcion [] = {"Eliminar", "Cancelar"}; //creacion de dos botones para preguntar al usuario si desea borrar un registro o no
        
        if(tx01.getText().equals(""))
        {
            JOptionPane.showMessageDialog(rootPane, "Escribe el DPI cuyo registro quieres eliminar");
        }
        
            else
            {
                if(url.exists())
                {
                    try
                    {
                        FileInputStream cerrar = new FileInputStream(url); //esto permite que el archivo se pueda cerrar, ya que abierto no se puede eliminar
                        cerrar.close();
                        System.gc();//forzar cierre
                        
                        int afirmar;
                        afirmar = JOptionPane.showOptionDialog(rootPane, "Estas seguro que deseas eliminar el registro " + tx01.getText() + "?", 
                                                               "Eliminar registro", 0, 0, null, opcion, null);
                        
                        if(afirmar == JOptionPane.YES_OPTION)
                        {
                            url.delete();
                            JOptionPane.showMessageDialog(rootPane, "El registro " + tx01.getText() + " ha sido eliminado");
                        }
                        
                            if(afirmar == JOptionPane.NO_OPTION)
                            {
                            
                            }
                    }
                        catch(Exception e)
                        {
                        
                        }
                    
                    
                }
                    else
                    {
                        JOptionPane.showMessageDialog(rootPane, "El archivo no existe");
                    }
            }
    
    }            
    
    private void Limpiar()//metodo 5 para limpiar los campos de registro de la aplicacion
    {
        tx01.setText("");
        tx02.setText("");
        tx03.setText("");
        tx04.setText("");
        tx05.setText("");
        tx06.setText("");
        tx07.setText("");
        tx08.setText("");
        tx09.setText("");
        tx10.setText("");
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titulo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tx01 = new javax.swing.JTextField();
        tx02 = new javax.swing.JTextField();
        tx03 = new javax.swing.JTextField();
        tx04 = new javax.swing.JTextField();
        tx05 = new javax.swing.JTextField();
        tx06 = new javax.swing.JTextField();
        tx07 = new javax.swing.JTextField();
        tx08 = new javax.swing.JTextField();
        tx09 = new javax.swing.JTextField();
        tx10 = new javax.swing.JTextField();
        Btn01 = new javax.swing.JButton();
        Btn02 = new javax.swing.JButton();
        Btn03 = new javax.swing.JButton();
        Btn04 = new javax.swing.JButton();
        Btn05 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titulo.setFont(new java.awt.Font("Arial Black", 3, 24)); // NOI18N
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("Registro Vacunación");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setText("DPI:");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setText("Nombres:");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setText("Apellidos:");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setText("Edad:");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel5.setText("Estado civil:");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setText("Departamento:");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel7.setText("Municipio:");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel8.setText("Telefono:");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel9.setText("Correo:");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel10.setText("Vacuna:");

        tx01.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tx01.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tx02.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tx02.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tx03.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tx03.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tx04.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tx04.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tx05.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tx05.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tx06.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tx06.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tx07.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tx07.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tx08.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tx08.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tx09.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tx09.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tx10.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tx10.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        Btn01.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Btn01.setText("Ingresar");
        Btn01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn01ActionPerformed(evt);
            }
        });

        Btn02.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Btn02.setText("Buscar");
        Btn02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn02ActionPerformed(evt);
            }
        });

        Btn03.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Btn03.setText("Editar");
        Btn03.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn03ActionPerformed(evt);
            }
        });

        Btn04.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Btn04.setText("Eliminar");
        Btn04.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn04ActionPerformed(evt);
            }
        });

        Btn05.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Btn05.setText("Limpiar");
        Btn05.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn05ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(tx06))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(102, 102, 102)
                        .addComponent(tx01))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(59, 59, 59)
                        .addComponent(tx02))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(59, 59, 59)
                        .addComponent(tx03))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(89, 89, 89)
                        .addComponent(tx04))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(40, 40, 40)
                        .addComponent(tx05))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(55, 55, 55)
                        .addComponent(tx07))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(60, 60, 60)
                        .addComponent(tx08))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(76, 76, 76)
                        .addComponent(tx09))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(71, 71, 71)
                        .addComponent(tx10))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Btn01)
                        .addGap(18, 18, 18)
                        .addComponent(Btn02, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Btn03, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Btn04)
                        .addGap(18, 18, 18)
                        .addComponent(Btn05)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titulo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tx01, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tx02, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tx03, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tx04, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tx05, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tx06, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tx07, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tx08, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tx09, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(tx10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btn01)
                    .addComponent(Btn02)
                    .addComponent(Btn03)
                    .addComponent(Btn04)
                    .addComponent(Btn05))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Btn01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn01ActionPerformed
        Ingresar();
    }//GEN-LAST:event_Btn01ActionPerformed

    private void Btn05ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn05ActionPerformed
        Limpiar();
    }//GEN-LAST:event_Btn05ActionPerformed

    private void Btn02ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn02ActionPerformed
        Buscar();
    }//GEN-LAST:event_Btn02ActionPerformed

    private void Btn03ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn03ActionPerformed
        Editar();
    }//GEN-LAST:event_Btn03ActionPerformed

    private void Btn04ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn04ActionPerformed
        Borrar();
    }//GEN-LAST:event_Btn04ActionPerformed

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
            java.util.logging.Logger.getLogger(Informacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Informacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Informacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Informacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Informacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn01;
    private javax.swing.JButton Btn02;
    private javax.swing.JButton Btn03;
    private javax.swing.JButton Btn04;
    private javax.swing.JButton Btn05;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel titulo;
    private javax.swing.JTextField tx01;
    private javax.swing.JTextField tx02;
    private javax.swing.JTextField tx03;
    private javax.swing.JTextField tx04;
    private javax.swing.JTextField tx05;
    private javax.swing.JTextField tx06;
    private javax.swing.JTextField tx07;
    private javax.swing.JTextField tx08;
    private javax.swing.JTextField tx09;
    private javax.swing.JTextField tx10;
    // End of variables declaration//GEN-END:variables
}
