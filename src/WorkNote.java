
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;


class Frame extends JFrame implements ActionListener {
    
    private JTextArea area;
    private JButton wgraj, aktualizuj, zapisz;
    private JLabel lLiczbaZnakow, lLiczbaWyrazow, lLiczbaWierszy;
    private JMenuBar pasekMenu;
    private JMenu mPlik;
    private JMenuItem pOtworz, pZapisz, pWyjscie, pSzybki;
    
    public Frame(){
  
        setTitle("E-biznes");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        pasekMenu = new JMenuBar();
        setJMenuBar(pasekMenu);
       
        mPlik = new JMenu("Plik");
        pasekMenu.add(mPlik);
        
        pOtworz = new JMenuItem("Otworz");
        pZapisz = new JMenuItem("Zapisz");
        pWyjscie = new JMenuItem("Wyjscie");
        pSzybki = new JMenuItem("Szybki Zapis");
        
        mPlik.add(pOtworz);
        mPlik.add(pZapisz);
        mPlik.add(pSzybki);
        mPlik.addSeparator();
        mPlik.add(pWyjscie);
        
        
        pWyjscie.addActionListener(this);
        pWyjscie.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
        
        pOtworz.addActionListener(this);
        pZapisz.addActionListener(this);
        pSzybki.addActionListener(this);
        pSzybki.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        
        wgraj = new JButton("Wgraj z pliku");
        wgraj.setBounds(400, 30, 150, 30);
        add(wgraj);
        wgraj.addActionListener(this);
        
        zapisz = new JButton("Zapisz");
        zapisz.setBounds(600, 30, 150, 30);
        add(zapisz);
        zapisz.addActionListener(this);
        
        area = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(area , JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
        scrollPane.setBounds(20, 100, 1140, 500);
        area.setBounds(20, 100, 1140, 500);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setFont(new Font("Verdana", Font.ITALIC, 25));
        add(scrollPane);
        
        lLiczbaZnakow = new JLabel("Liczba Znakow: ");
        lLiczbaZnakow.setBounds(20, 600, 300, 20);
        lLiczbaZnakow.setFont(new Font("Verdana", Font.ITALIC, 20));
        add(lLiczbaZnakow);
        
        lLiczbaWyrazow = new JLabel("Liczba Wyrazow: ");
        lLiczbaWyrazow.setBounds(330, 600, 300, 20);
        lLiczbaWyrazow.setFont(new Font("Verdana", Font.ITALIC, 20));
        add(lLiczbaWyrazow);
        
        aktualizuj = new JButton("Oblicz / Aktualizuj");
        aktualizuj.setBounds(500, 650, 150, 30);
        add(aktualizuj);
        aktualizuj.addActionListener(this);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        File plik = new File("E-biznes.txt");
        if(source==wgraj || source==pOtworz){
            JFileChooser fc = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT file", "txt");
            fc.setFileFilter(filter);
            if(fc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
                plik = fc.getSelectedFile();
                try {
                    BufferedReader czytaj = new BufferedReader(new FileReader(plik));
                    area.read(czytaj, null);
                    czytaj.close();
                    area.requestFocus();
                    
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
                
                
            }
        }
        
        else if(source==zapisz || source==pZapisz){
            JFileChooser fc = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT file", "txt");
            fc.setFileFilter(filter);
            if(fc.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
                plik = fc.getSelectedFile();
                try {
                    PrintWriter zapis = new PrintWriter(plik);
                    /*
                    String[] tabelaWyrazow = null ;
                    String tekst = "";
                    tabelaWyrazow = area.getText().split(" ");
                    for(int i=0;i<tabelaWyrazow.length;i++){
                        tekst += tabelaWyrazow[i] + " ";
                        if(i%10==0){
                            tekst += "\r\n";
                        }
                    }*/
                    
                    zapis.println(area.getText());
                    zapis.close();
                    
                    
                    /*BufferedReader czytaj = new BufferedReader(new FileReader(plik));
                    area.read(czytaj, null);
                    czytaj.close();
                    area.requestFocus();*/
                    
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
                
                
            }
        }
        else if(source==pSzybki){
            try {
                    PrintWriter zapis = new PrintWriter(plik);
                    zapis.println(area.getText());
                    zapis.close();
                    
                    
                    /*BufferedReader czytaj = new BufferedReader(new FileReader(plik));
                    area.read(czytaj, null);
                    czytaj.close();
                    area.requestFocus();*/
                    
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
        }
        
        else if(source==aktualizuj){
            aktualizuj();
        }
        else if(source==pWyjscie){
            dispose();
        }
    }
    
    public void aktualizuj(){
        String tekst = area.getText();
        lLiczbaZnakow.setText("Liczba Znakow:  " + tekst.length());
        System.out.println("Liczba Znakow:  " + tekst.length());
        
        
        String[] tabelaWyrazow = null ;
        tabelaWyrazow = tekst.split(" ");
        lLiczbaWyrazow.setText("Liczba Wyrazow:  " + tabelaWyrazow.length);
        System.out.println("Liczba Wyrazow:  " + tabelaWyrazow.length);
        
        
        System.out.println("");
    }
    
    
    
}

public class WorkNote {
    
    public static void main(String[] args){
        
        Frame okno = new Frame();
        okno.setVisible(true);
    
    }
    
}
