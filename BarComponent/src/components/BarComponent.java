//Bar Diagram Component
//Programowanie Komponentowe
//Tomasz Patrzałek U-14819
//Mateusz Orelik   U-14814
//Informatyka, sem. VI
//Studia stacjonarne

package components;
 
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.ComponentListener;

/**
 * 
 * Main class of bar diagram component. 
 * It uses get and set methods to change components properties.
 * Component is a JPanel containing all necessary sub-components to build
 * a bar diagram.
 * 
 * @author Tomasz Patrzałek U-14819, Mateusz Orelik U-14814
 */

public class BarComponent extends JPanel implements ComponentListener {
   
   //Properties
   private int barsNumber;
   private Color barsColor;
   private Color barDiagramFontColor;
   private int barsMinValue;
   private int barsMaxValue;
   private Border barDiagramBorder;
   private Border barsBorder;
   private boolean isReady;
   private boolean isBarValueShown;
   private int[] barValues;
   private String[] barsNames;
   
   //Support variables
   private int number;
   private int min=0;
   private int max=100;
   
   //Main container
   private final JPanel mainPanel = new JPanel();
   
   //Main container components
   private JPanel infoPanel;
   private JLabel minText;
   private JLabel maxText;
   private JLabel minLabel;
   private JLabel maxLabel;
 
   //Arrays for components
   private JPanel[] barPanels;
   private JProgressBar[] progressBars;
   private JLabel[] barsLabels;
   
   /**
    * Constructor of component class.
    */
   
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

    //Getters
    /**
     * Getter method for Border of bar diagram.
     * @return Border which is set for whole bar diagram. 
     */
    public Border getBarDiagramBorder() {
        return barDiagramBorder;
    }
    /**
     * Getter method for Color of bar diagram font.
     * @return Color which is set for bar diagram font. 
     */
    public Color getBarDiagramFontColor() {
        return barDiagramFontColor;
    }
    /**
     * Getter method for a number of bars in bar diagram.
     * @return Integer set for numbet of bar diagram bars.
     */
    public int getBarsNumber() {
        return barsNumber;
    }
    /**
     * Getter method for Color of all bars in bar diagram.
     * @return Color set for all bars in bar diagram.
     */
    public Color getBarsColor() {
        return barsColor;
    }
    /**
     * Getter method for minimum value of bars in bar diagram.
     * @return Integer set for minimum value of lower range for bars in bar diagram.
     */
    public int getBarsMinValue() {
        return barsMinValue;
    }
    /**
     * Getter method for maximum value of bars in bar diagram.
     * @return Integer set for maximum value of upper range for bars in bar diagram.
     */
    public int getBarsMaxValue() {
        return barsMaxValue;
    }
    /**
     * Getter method for array of Integers set as values for bars in bar diagram.
     * @return Array of Integers with values for bars in bar diagram.
     */
    public int[] getBarValues() {
        return barValues;
    }
    /**
     * Getter method for array of Strings set as names of bars in bar diagram.
     * @return Array of String with names of bars in bar diagram.
     */
    public String[] getBarsNames() {
        return barsNames;
    }
    /**
     * Getter method for Border of bars in bar diagram.
     * @return Border of bars in bar diagram.
     */
    public Border getBarsBorder() {
        return barsBorder;
    }
    /**
     * Getter method for boolean value saying if the value of bar is shown inside the bar.
     * @return Boolean value saying if value of bar is shown.
     */
    public boolean isIsBarValueShown() {
        return isBarValueShown;
    }
    /**
     * Getter method for boolean value saying if all properties of component are set
     * and component is ready for using in application.
     * @return Boolean value saying if component is ready for using.
     */
    public boolean isIsReady() {
        return isReady;
    }
    
    //Setters
    /**
     * Setter method to set Border for whole bar diagram.
     * @param barDiagramBorder Border property for whole bar diagram.
     */
    public void setBarDiagramBorder(Border barDiagramBorder) {
        this.barDiagramBorder = barDiagramBorder;
    }
    
    /**
     * Setter method to set Color for bar diagram font.
     * @param barDiagramFontColor Color property for bar diagram font.
     */
    public void setBarDiagramFontColor(Color barDiagramFontColor) {
        this.barDiagramFontColor = barDiagramFontColor;
        minLabel.setForeground(barDiagramFontColor);
        maxLabel.setForeground(barDiagramFontColor);
        minText.setForeground(barDiagramFontColor);
        maxText.setForeground(barDiagramFontColor);
        
        for(int i=0; i<number;i++)
        {
            barsLabels[i].setForeground(barDiagramFontColor);
        }
    }
    /**
     * Setter method to set Integer for number of bars in bar diagram.
     * It also creates all sub-comopnents of bar diagram depending on the number
     * of bars, and adds all necessary listeners for component.
     * @param barsNumber Integer number of bars in bar diagram and all necessary
     * sub-components.
     */
    public void setBarsNumber(int barsNumber) {
        this.barsNumber = barsNumber;
        //Removes old bars, when typed new bar number
        mainPanel.removeAll();
        number=barsNumber;
        //Arrays initialize according to value set in barsNumber property
        barPanels = new JPanel[barsNumber];
        progressBars = new JProgressBar[barsNumber];
        barsLabels = new JLabel[barsNumber];
       
         for(int i=0; i<barPanels.length; i++)
         {
                        //Sub-panels for labels,textfields and bars
                        barPanels[i] = new JPanel();
                        barPanels[i].setLayout(new BorderLayout());
                   
                        //Bars
                        progressBars[i] = new JProgressBar(0,100);
                        progressBars[i].setOrientation(SwingConstants.VERTICAL);
                        progressBars[i].addMouseListener(new MouseAdapter() {
                            public void mouseClicked(MouseEvent e){
                                Object source = e.getSource();
                                for(int i = 0; i<barsNumber; i++){
                                if(source==progressBars[i]){
                                Color pickedColor = JColorChooser.showDialog(null, "Wybierz kolor słupka", barsColor);
                                progressBars[i].setForeground(pickedColor);
                                }
                            }
                        }
                    });
                        
                        //Adding all components to sub-panels
                        barPanels[i].add(progressBars[i], BorderLayout.CENTER);
                        barsLabels[i] = new JLabel("Label: "+(i+1), SwingConstants.CENTER);
                        barPanels[i].add(barsLabels[i], BorderLayout.SOUTH);
                        
                       
                        //Adding sub-panels to main container
                        mainPanel.add(barPanels[i]);
                        mainPanel.addComponentListener(this);
         }
         infoPanel.add(minLabel);
         infoPanel.add(maxLabel);
    }
    /**
     * Setter method to set Color for all bars in bar diagram.
     * @param barsColor Color property for alla bars in bar diagram.
     */
    public void setBarsColor(Color barsColor) {
        this.barsColor = barsColor;
    }
    /**
     * Setter method to set minimum value of lower range for bars in bar diagram.
     * @param barsMinValue Integer number for lower range for bars in bar diragram.
     */
    public void setBarsMinValue(int barsMinValue) {
        this.barsMinValue = barsMinValue;
        minText.setText(String.valueOf(barsMinValue));
        min = Integer.parseInt(minText.getText());
        minLabel.setText("Minimum: " + minText.getText());
        
        for(int i=0; i<barsNumber; i++)
        {
            progressBars[i].setMinimum(min);
        }
    }
    /**
     * Setter method to set maximum value of upper range for bars in bar diagram.
     * @param barsMaxValue Integer number for upper range for bars in bar diagram.
     */
    public void setBarsMaxValue(int barsMaxValue) {
        this.barsMaxValue = barsMaxValue;
        maxText.setText(String.valueOf(barsMaxValue));
        max = Integer.parseInt(maxText.getText());
        maxLabel.setText("Maksimum: " + maxText.getText());
        
        for(int i=0; i<barsNumber; i++)
        {
            progressBars[i].setMaximum(max);
        }
    }
    /**
     * Setter method to set array of values for bars in bar diagram.
     * @param barValues Array of values for bars in bar diagram.
     */
    public void setBarValues(int[] barValues) {
        this.barValues = barValues;
    }
    /**
     * Setter method to set array of Strings used as names for bars in bar diagram.
     * @param barsNames Array of String used as names for bars in bar diagram.
     */
    public void setBarsNames(String[] barsNames) {
        this.barsNames = barsNames;
    }
    
    /**
     * Setter method to set Border of all bars in bar diagram.
     * @param barsBorder Border for all bars in bar diagram.
     */
    public void setBarsBorder(Border barsBorder){
        this.barsBorder = barsBorder;
    }
    /**
     * Setter method to set if the value of bar is shown inside of it.
     * @param isBarValueShown Boolean saying if the value is shown.
     */
    public void setIsBarValueShown(boolean isBarValueShown) {
        this.isBarValueShown = isBarValueShown;
        
        if(isBarValueShown)
        {
            for(int i=0; i<number; i++)
            {
            progressBars[i].setStringPainted(true); 
            }
            
        }
        else if(!isBarValueShown)
        {
            for(int i=0; i<number; i++)
            progressBars[i].setStringPainted(false);
        }
    }
    /**
     * Setter method to set if all properties are already set, 
     * and component is ready for use in application.
     * It also updates changed properties.
     * @param isReady Boolean saying if the component is ready to use.
     */
    public void setIsReady(boolean isReady) {
        this.isReady = isReady;
        for(int i=0; i<number; i++)
        {
            progressBars[i].setValue(barValues[i]);
            barsLabels[i].setText(barsNames[i]);
            barsLabels[i].setForeground(barDiagramFontColor);
            progressBars[i].setForeground(barsColor);
            progressBars[i].setBorder(barsBorder);
            progressBars[i].setMinimum(min);
            progressBars[i].setMaximum(max);
            mainPanel.setBorder(barDiagramBorder);
            
            if(isBarValueShown)
            progressBars[i].setString(String.valueOf(progressBars[i].getValue()));
        }
    }

    /**
     * Method used for changing size of sub-components when bar diagram component
     * is resized.
     * @param e Event fired when the size of component is changed.
     */
    @Override
    public void componentResized(ComponentEvent e) {
        for(int i=0; i<barsNumber; i++){
            barPanels[i].setPreferredSize(new Dimension((mainPanel.getWidth()/barsNumber)-20, (int) ((mainPanel.getHeight()*0.8))));
            mainPanel.revalidate();
            mainPanel.repaint();
            }
    }
    
    /**
     * Method needed to implemented with ComponentListener,
     * not used in bar diagram component.
     * @param e Event fired when the component is moved.
     */
    @Override
    public void componentMoved(ComponentEvent e) {
        
    }

    /**
     * Method needed to implemented with ComponentListener,
     * not used in bar diagram component.
     * @param e Event fired when the component is shown.
     */
    @Override
    public void componentShown(ComponentEvent e) {
        
    }

    /**
     * Method needed to implemented with ComponentListener,
     * not used in bar diagram component.
     * @param e Event fired when the component is hidden.
     */
    @Override
    public void componentHidden(ComponentEvent e) {
        
    }
}
