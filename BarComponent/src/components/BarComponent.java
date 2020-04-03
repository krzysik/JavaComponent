//Bar Diagram Component
//Programowanie Komponentowe
//Tomasz Patrzałek U-14819
//Mateusz Orelik   U-14814
//Informatyka, sem. VI
//Studia stacjonarne

package components;
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class BarComponent extends JPanel implements ActionListener {
   
   //Properties
   private int barsNumber;
   private Color barsColor;
   
   //Main container
   private final JPanel mainPanel = new JPanel();
 
   //Arrays for components
   private JTextField[] valueTexts;
   private JPanel[] barPanels;
   private JProgressBar[] progressBars;
   private JTextField[] barName;
   private JLabel[] barLabel;

   //Component constructor
   public BarComponent(){
       setLayout(new BorderLayout());
       
       mainPanel.setLayout(new FlowLayout());
       add(mainPanel, BorderLayout.CENTER);
   }

    //Getters
    public int getBarsNumber() {
        return barsNumber;
    }
   
    public Color getBarsColor() {
        return barsColor;
    }
 
    //Setters
    public void setBarsNumber(int barsNumber) {
        this.barsNumber = barsNumber;
        //Removes old bars, when typed new bar number
        mainPanel.removeAll();
       
        //Arrays initialize according to value set in barsNumber property
        barPanels = new JPanel[barsNumber];
        valueTexts = new JTextField[barsNumber];
        progressBars = new JProgressBar[barsNumber];
        barName = new JTextField[barsNumber];
        barLabel = new JLabel[barsNumber];
       
         for(int i=0; i<barPanels.length; i++)
         {
                        //Sub-panels for labels,textfields and bars
                        barPanels[i] = new JPanel();
                        barPanels[i].setLayout(new BorderLayout());
                        
                        //Textfields for bars' values
                        valueTexts[i] = new JTextField(3);
                        valueTexts[i].addActionListener(this);
                        valueTexts[i].setHorizontalAlignment(JTextField.CENTER);
                        
                        //Bars
                        progressBars[i] = new JProgressBar(0,100);
                        progressBars[i].setOrientation(SwingConstants.VERTICAL);
                        
                        //Adding all components to sub-panels
                        barPanels[i].add(progressBars[i], BorderLayout.CENTER);
                        barName[i] = new JTextField(3);
                        barName[i].addActionListener(this);
                        barPanels[i].add(barName[i], BorderLayout.SOUTH);
                        barPanels[i].add(valueTexts[i], BorderLayout.NORTH);
                       
                        //Adding sub-panels to main container
                        mainPanel.add(barPanels[i]);
         }
    }
    
    public void setBarsColor(Color barsColor) {
        this.barsColor = barsColor;
        UIManager.put("ProgressBar.foreground", barsColor);
    }

    
    //Events method
    @Override
    public void actionPerformed(ActionEvent e) {
        
        Object o = e.getSource();
        for(int i=0; i<barPanels.length; i++){
            if(o==valueTexts[i]){
                
                int min = progressBars[i].getMinimum();
                int max = progressBars[i].getMaximum();
                
                try{
                    int value = Integer.parseInt(valueTexts[i].getText());
                    if(value>max || value<min){
                    JOptionPane.showMessageDialog(null, "Poza zakresem! "
                            + "Dozwolone wartości: " + min + " do " + max);    
                    }
                    else{
                    progressBars[i].setValue(value);
                    }
                }
                catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Dozwolone tylko "
                            + "liczby całkowite!");
                }
            }
            
            else if(o==barName[i]){
             
             barLabel[i] = new JLabel("");
             String name = barName[i].getText();
              
                try{
                  
                    barPanels[i].remove(barName[i]);
                    barLabel[i].setText(name);
                    barPanels[i].add(barLabel[i], BorderLayout.SOUTH);
               
                    repaint(); revalidate(); 
                }
                catch(Exception ex){
                  
                   JOptionPane.showMessageDialog(null, "Nazwa nie może być pusta!");
                }
              }
        
            }
        }
    }
