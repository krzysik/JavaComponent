//Bar Diagram Component
//Programowanie Komponentowe
//Tomasz Patrzałek U-14819
//Mateusz Orelik   U-14814
//Informatyka, sem. VI
//Studia stacjonarne

package components;
 
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;

import javax.swing.border.*;
 
public class BarComponent extends JPanel implements ActionListener {
   
   //Properties
   private int barsNumber;
   private Color barsColor;
   private String barsDiagramTitle;
   private int minValue;
   private int maxValue;

   private JPanel infoPanel;
   private JLabel minText;
   private JLabel maxText;
   private JLabel minLabel;
   private JLabel maxLabel;
   
   private int number;
   private int min=0;
   private int max=100;
   
   //Main container
   private final JPanel mainPanel = new JPanel();
 
   //Arrays for components
   private JTextField[] valueTexts;
   private JPanel[] barPanels;
   private JProgressBar[] progressBars;
   private JLabel[] barsLabels;
   
   private int[] barValues;
   private int[] values;

   private String[] barsNames;
   private String[] names;
   
   
   
   

   
   
   //Component constructor
   public BarComponent(){
       setLayout(new BorderLayout());
       
       infoPanel = new JPanel(new FlowLayout());
       minLabel = new JLabel("Minimum: " + min);
       maxLabel = new JLabel("Maksimum: " + max);
       minText = new JLabel();
       maxText = new JLabel();
       
       
       mainPanel.setLayout(new FlowLayout());
       add(infoPanel, BorderLayout.NORTH);
       add(mainPanel, BorderLayout.CENTER);
      
   }

        public String[] getBarsNames() {
        return barsNames;
    }

        public void setBarsNames(String[] barsNames) {
        this.barsNames = barsNames;
        names = new String[number];
        names = barsNames.clone();
        
        for(int i=0; i<number; i++)
        {
            barsLabels[i].setText(names[i]);
        }
        
    }
   
    //Getters
    public int getBarsNumber() {
        return barsNumber;
    }
    public String getBarsDiagramTitle() {
        return barsDiagramTitle;
    }
    public Color getBarsColor() {
        return barsColor;
    }
    public int getMinValue() {
        return minValue;
    }
    public int getMaxValue() {
        return maxValue;
    }
    
    //Setters
    public void setBarsNumber(int barsNumber) {
        this.barsNumber = barsNumber;
        //Removes old bars, when typed new bar number
        mainPanel.removeAll();
        number=barsNumber;
        //Arrays initialize according to value set in barsNumber property
        barPanels = new JPanel[barsNumber];
        valueTexts = new JTextField[barsNumber];
        progressBars = new JProgressBar[barsNumber];
        barsLabels = new JLabel[barsNumber];
        names = new String[barsNumber];
       
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
                        barsLabels[i] = new JLabel("Label: "+(i+1));
                        names[i] = barsLabels[i].getText();
                        barPanels[i].add(barsLabels[i], BorderLayout.SOUTH);
                        barPanels[i].add(valueTexts[i], BorderLayout.NORTH);
                       
                        //Adding sub-panels to main container
                        mainPanel.add(barPanels[i]);
         }
         infoPanel.add(minLabel);
         infoPanel.add(maxLabel);
    }
    public void setBarsColor(Color barsColor) {
        this.barsColor = barsColor;
        UIManager.put("ProgressBar.foreground", barsColor);
        
    }
    public void setBarsDiagramTitle(String barsDiagramTitle) {
        this.barsDiagramTitle = barsDiagramTitle;
        
         TitledBorder border = BorderFactory.createTitledBorder(barsDiagramTitle);
         border.setTitleJustification(TitledBorder.CENTER);
      
        mainPanel.setBorder(border);
    }
    public void setMinValue(int minValue) {
        this.minValue = minValue;
        minText.setText(String.valueOf(minValue));
        min = Integer.parseInt(minText.getText());
        minLabel.setText("Minimum: " + minText.getText());
        
        for(int i=0; i<barsNumber; i++)
        {
            progressBars[i].setMinimum(min);
        }
    }
    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
        maxText.setText(String.valueOf(maxValue));
        max = Integer.parseInt(maxText.getText());
        maxLabel.setText("Maksimum: " + maxText.getText());
        
        for(int i=0; i<barsNumber; i++)
        {
            progressBars[i].setMaximum(max);
        }
    }

//    public int[] getBarValues() {
//        return barValues;
//    }
//
//    public void setBarValues(int[] barValues) {
//        this.barValues = barValues;
//        
//        for(int i=0; i<barsNumber; i++)
//        {
//            values[i] = barValues[i];
//            progressBars[i].setValue(values[i]);
//        }
//    }

    
    
    

    
    

    //Events method
    @Override
    public void actionPerformed(ActionEvent e) {
        
        Object o = e.getSource();
        
        for(int i=0; i<barPanels.length; i++){
            if(o==valueTexts[i]){
                
                
                
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
            
//            else if(o==barName[i]){
//             
//             barLabel[i] = new JLabel("");
//             String name = barName[i].getText();
//              
//                try{
//                    barPanels[i].remove(barName[i]);
//                    barLabel[i].setText(name);
//                    barPanels[i].add(barLabel[i], BorderLayout.SOUTH);
//               
//                    repaint(); revalidate(); 
//                }
//                catch(Exception ex){
//                  
//                   JOptionPane.showMessageDialog(null, "Nazwa nie może być pusta!");
//                }
//              }
        
            }
        }
    }
