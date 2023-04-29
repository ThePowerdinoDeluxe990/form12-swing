import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class main { 
  public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
    
    File file = new File("errorsound.wav");
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
		Clip clip = AudioSystem.getClip();
		clip.open(audioStream);
    Image icon = Toolkit.getDefaultToolkit().getImage("icono.png");  
    String [] generos= {"hombre","mujer","otros"};

    JComboBox comboBox = new JComboBox(generos);
    comboBox.setBounds(170, 270, 100, 30);
    comboBox.setBackground(Color.white);
    comboBox.setFont(new Font("Trebuchet MS",Font.BOLD,12));
    comboBox.setBorder(new LineBorder(Color.CYAN,2));
   
    Label label = new Label("Pon tu nombre: ");
    label.setBounds(170,70,120,50);
    label.setForeground(Color.WHITE);
    label.setFont(new Font("Trebuchet MS", Font.BOLD, 16));

    Label edad = new Label("Pon tu edad:  ");
    edad.setBounds(170,180,100,30);
    edad.setForeground(Color.WHITE);
    edad.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
   
    JTextField text=new JTextField();
    text.setBounds(170,120,150,30);
    text.setBorder(new LineBorder(Color.CYAN,1));

    JTextField edadtext=new JTextField();
    edadtext.setBounds(170,225,150,30);
    edadtext.setBorder(new LineBorder(Color.CYAN,1));

    JButton send = new JButton();
    send.setText("send");
    send.setBounds(170,340,100,40);
    send.setBackground(Color.WHITE);
    send.setBorder(new LineBorder(Color.CYAN,2));
      
    send.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent a){
        String nombr1e = text.getText();
        String edad1 = edadtext.getText();
        LocalDateTime fecha = LocalDateTime.now();

        String genero= comboBox.getSelectedItem().toString();

        System.out.println(nombr1e);
        System.out.println(edad1);
        System.out.println(genero);

        if(nombr1e.equals("")){
          clip.start();
          JLabel label = new JLabel("Error Fatal,");
        label.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        JButton button = new JButton("custom button");
        Object[] options ={button};
          JOptionPane.showMessageDialog(null, label, "Informacion", JOptionPane.ERROR_MESSAGE);
        }else{
          try{
            FileWriter writer= new FileWriter(".\\base\\"+nombr1e+".txt");
            writer.append("Nombre: "+nombr1e);
            writer.append("\nEdad: "+edad1);
            writer.append("\nGenero: "+genero);
            writer.append("\nFecha: "+fecha);
            writer.close();
           }catch(IOException e){
            e.printStackTrace();
           }
        }
      
      }});

   
    JFrame jFrame = new JFrame("Formulario");
    jFrame.setSize(500, 500);
    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jFrame.setLayout(null);
    jFrame.add(label);
    jFrame.setIconImage(icon);
    jFrame.add(edad);
    jFrame.add(send);
    jFrame.setResizable(false);
    jFrame.add(comboBox);
    jFrame.getContentPane().setBackground(Color.black);
    jFrame.add(edadtext);
    jFrame.add(text);
    jFrame.setVisible(true);
  
  }
}
