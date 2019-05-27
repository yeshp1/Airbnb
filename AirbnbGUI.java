import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.imageio.*;
import java.io.*;
import java.lang.Exception;

import java.io.File;
import java.util.*;
import java.lang.Object;
import java.lang.Number;
import java.lang.Integer;

/**
* The AirbnbGUI class creates the main user interface
*
* @author Yeshvanth Prabakar, Arnav Ratan, Hardik Agrawal, Shreyas Solanki
* @version 30-03-18
*/
public class AirbnbGUI implements ActionListener
{
    // variables to create GUI components.
    private JFrame welcomeFrame, mapFrame, statisticsFrame, displayFrame, detailsFrame; 
    
    public static String selectedBorough="";
   
    
    private JPanel welcomeContentPane, toolbar, bottombar, topbar, detailsPane, statisticsContentPane, mapContentPane, detailsPanel;
    private JPanel namePanel, emailPanel, phonePanel, reasonPanel;
    
     JLabel labelInfo;
    
    private HashMap<String,Integer> nameToXs;
    private HashMap<Integer,Integer> coordinates;
    
    private int selectedItem, selectedItem2;
    
    private JLabel label, label1, label2, imgLabel;
    private JLabel statsLabel;
    
    private JButton nextButton, previousButton; //Buttons for the welcome page  
    private JButton nextButton1, previousButton1; //Buttons for the map page    
    private JButton nextButton2, previousButton2; //Buttons for the statistics panel 
    private JButton previousButton3; //Buttons for the buy panel 
    
    private JComboBox comboBox1, comboBox2;
    
    static String[] elements = new String[33];
    
    private JLabel label5;
    
    private Statistics statistic;
    //Creates the grid for the statistics
    JPanel statisticsGrid = new JPanel(new GridLayout(2,2));
    
    private String title, nextTitle, previousTitle;
    
    private ArrayList<String> nameEntries = new ArrayList();
    private ArrayList<String> phoneEntries = new ArrayList();
    private ArrayList<String> emailEntries = new ArrayList();
    
    /**
     * Constructor for objects of class AirbnbGUI
     */
    public AirbnbGUI() 
    {
        makeFrame();
        createArray();
        nameToXs = new HashMap<String,Integer>();
        coordinates = new HashMap<Integer,Integer>();
        statistic = new Statistics();
        addBoroughXCoordinates();
        addStatisticsFrame();
    }
    
    /**
     * 
     */
    public void actionPerformed(ActionEvent e)
    {
        
    }   
    
    /**
     * Makes the whole welcome frame
     */
    public void makeFrame()
    
    {
        // Initialising welcomeFrames and components.
        welcomeFrame = new JFrame("Welcome to Air BnB");
        welcomeFrame.setMinimumSize(new Dimension(600,600));
        welcomeFrame.setMaximumSize(new Dimension(600,600));
        welcomeContentPane = (JPanel)welcomeFrame.getContentPane();
        welcomeContentPane.setBorder(new EmptyBorder(8, 8, 8, 8));
        welcomeContentPane.setLayout(new BorderLayout(8, 8));
   
        nextButton = new JButton("Next");
        previousButton = new JButton("Previous");
        comboBox1 = new JComboBox();
        comboBox2 = new JComboBox();
        label = new JLabel("Welcome to the home screen of Airbnb. Select price range from the Drop-down menu above. Press next to look at the Map and Statistics");
        label1 = new JLabel("From: ");
        label2 = new JLabel("To: ");
        
        ImageIcon logoImage = new ImageIcon("air.jpg");
        label.setIcon(logoImage);
        toolbar = new JPanel();
        bottombar = new JPanel();
        topbar = new JPanel();
                
        //setting panel layouts.
        topbar.setLayout(new FlowLayout(FlowLayout.TRAILING));
        bottombar.setLayout(new BorderLayout (6,6));
        toolbar.setLayout(new GridLayout(0, 1));
        
                
        addFrameComponents();
        addComboBoxItems();
        nextButton.setEnabled(false);
        
        nextButton.addActionListener(e->
        {
            addMapFrame();
            welcomeFrame.dispose();
        }
        );
        
        previousButton.addActionListener(e->{JOptionPane.showMessageDialog(welcomeFrame, "Cant go back");});
        
        comboBox1.addActionListener(e->{selectedItem= Integer.parseInt( (String) comboBox1.getSelectedItem() );}); 
        
        comboBox2.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent event) 
            {
                selectedItem2= Integer.parseInt( (String) comboBox2.getSelectedItem() );
                if(selectedItem<selectedItem2)
                {
                    nextButton.setEnabled(true);
                }
                else 
                nextButton.addActionListener(e->{JOptionPane.showMessageDialog(welcomeFrame, "Input a higher value");});
            }
        }
        );
        //putting the welcomeFrame in the centr of the screen.
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        welcomeFrame.setLocation(d.width/2 - welcomeFrame.getWidth()/2, d.height/2 - welcomeFrame.getHeight()/2);
        welcomeFrame.setVisible(true);
        welcomeFrame.pack();
        welcomeFrame.setVisible(true);
    }
    
    /**
     * Add Bars or Components to the frame
     */
    public void addFrameComponents()
    {
        //adding buttons.
        toolbar.add(nextButton);
        toolbar.add(previousButton);
        
        bottombar.add(nextButton, BorderLayout.EAST);
        bottombar.add(previousButton, BorderLayout.WEST);
        
        //adding labels.
        topbar.add(label1);
        topbar.add(comboBox1);
        topbar.add(label2);
        topbar.add(comboBox2);
        
        //adding subwelcomeFrames.
        welcomeContentPane.add(bottombar, BorderLayout.SOUTH);
        welcomeContentPane.add(topbar, BorderLayout.NORTH);
        
        //adding labels.
        welcomeContentPane.add(label);
    }
    
    /**
     * Adds items to the ComboBox 
     */
    public void addComboBoxItems()
    {
        comboBox1.addItem("Select Minimum Price");
        comboBox1.addItem("500");
        comboBox1.addItem("1000");
        comboBox1.addItem("1500");
        comboBox1.addItem("2000");
        
        comboBox2.addItem("Select Maximum Price");
        comboBox2.addItem("1000");
        comboBox2.addItem("1500");
        comboBox2.addItem("2000");
        comboBox2.addItem("2500");
    }
     
    /**
     * Makes the statistics frame
     */
    public void addStatisticsFrame()
    {
        //Intitialising statisticsFrame and components.
        statisticsFrame = new JFrame("Statistics");
        statisticsFrame.setMinimumSize(new Dimension(600,600));
        statisticsFrame.setMaximumSize(new Dimension(600,600));
        
        nextButton1 = new JButton("Next");
        previousButton1 = new JButton("Previous");
        JPanel buttonPane = new JPanel (new BorderLayout());
        buttonPane.add(previousButton1, BorderLayout.WEST);
        buttonPane.add(nextButton1, BorderLayout.EAST);
        
        statisticsContentPane = (JPanel)statisticsFrame.getContentPane();
        statisticsContentPane.setBorder(new EmptyBorder(8, 8, 8, 8));
        statisticsContentPane.setLayout(new BorderLayout(8, 8));
        statisticsContentPane.add(buttonPane,BorderLayout.SOUTH);
        statisticsContentPane.add(statisticsGrid, BorderLayout.CENTER);

        previousButton1.addActionListener(e->{addMapFrame(); statisticsFrame.dispose();});
        
        nextButton1.addActionListener(e->
        {
            addPersonalDetailsFrame();
            detailsFrame.setVisible(true);
            statisticsFrame.dispose();
        }
        );
        
        statisticsPage();
        statsLabel = new JLabel("Statistics");

        Dimension d1 = Toolkit.getDefaultToolkit().getScreenSize();
        statisticsFrame.setLocation(d1.width/2 - welcomeFrame.getWidth()/2, d1.height/2 - welcomeFrame.getHeight()/2);
        statisticsFrame.pack();
        statisticsFrame.setVisible(false);
    }
    /**
     * 
     */
    public void addBoroughXCoordinates()
    {
        //linking boroughs and their x-coordinates.
        nameToXs.put("Westminster", 533); 
        nameToXs.put("Harrow", 250);
        nameToXs.put("Brent", 358);
        nameToXs.put("Ealing", 263);
        nameToXs.put("Hillingdon", 103);
        nameToXs.put("Hounslow", 190); 
        nameToXs.put("Richmond upon Thames", 278);
        nameToXs.put("Kingston upon Thames", 398);
        nameToXs.put("Wandsworth", 526);
        nameToXs.put("Lambeth", 587);
        nameToXs.put("Merton", 471);
        nameToXs.put("Sutton", 531);
        nameToXs.put("Croydon", 635);
        nameToXs.put("Bromley", 848);
        nameToXs.put("Lewisham", 740);
        nameToXs.put("Southwark", 657);
        nameToXs.put("Greenwich", 833);
        nameToXs.put("Bexley", 975);
        nameToXs.put("Havering", 1094);
        nameToXs.put("Barking and Dagenham", 960);
        nameToXs.put("Newham", 791);
        nameToXs.put("Redbridge", 874);
        nameToXs.put("Waltham Forest", 712);
        nameToXs.put("Tower Hamlets", 709);
        nameToXs.put("Hackney", 670);
        nameToXs.put("Islington", 603);
        nameToXs.put("City of London", 627);
        nameToXs.put("Haringey", 606);
        nameToXs.put("Enfield", 623);
        nameToXs.put("Barnet", 454);
        nameToXs.put("Camden", 513);
        nameToXs.put("Kensington and Chelsea", 483);
        nameToXs.put("Hammersmith and Fullham", 428);
        
        //linking boroughs x-nameToXs to their respective coordinates.
        coordinates.put(533, 420);
        coordinates.put(250, 261);
        coordinates.put(358, 340);
        coordinates.put(263, 383);
        coordinates.put(103, 415);
        coordinates.put(190, 559);
        coordinates.put(278, 622);
        coordinates.put(398, 657);
        coordinates.put(526, 595);
        coordinates.put(587, 567);
        coordinates.put(471, 649);
        coordinates.put(531, 767);
        coordinates.put(635, 807);
        coordinates.put(848, 757);
        coordinates.put(740, 549);
        coordinates.put(657, 540);
        coordinates.put(833, 536);
        coordinates.put(975, 495);
        coordinates.put(1094, 334);
        coordinates.put(960, 320);
        coordinates.put(791, 363);
        coordinates.put(874, 304);
        coordinates.put(712, 255);
        coordinates.put(709, 406);
        coordinates.put(670, 340);
        coordinates.put(603, 347);
        coordinates.put(627, 420);
        coordinates.put(606, 239);
        coordinates.put(623, 162);
        coordinates.put(454, 248);
        coordinates.put(513, 327);
        coordinates.put(483, 454);
        coordinates.put(428, 442);
    }
    /**
     * 
     */
    public void selectedBoroughDetails()
    {
        displayFrame = new JFrame("Display of the borough");
        displayFrame.setMinimumSize(new Dimension(1274,961));
        displayFrame.setMaximumSize(new Dimension(1274,961)); 
        
        labelInfo = new JLabel();        
        JPanel panel = new JPanel();
        
        JPanel container = new JPanel();
        container.add(panel);
        JScrollPane jsp = new JScrollPane(container);
        displayFrame.add(jsp);
        
        String x="";
        statistic.specificListing(selectedBorough);
        
        for(AirbnbListing property : Statistics.currentListings)
        {
            x = x + "<html> <br/>" + property.getDetails() + "<html/>";
        }
        
        labelInfo.setText(x);
        panel.add(labelInfo);
        displayFrame.add(jsp);
        Dimension d3 = Toolkit.getDefaultToolkit().getScreenSize();
        displayFrame.setLocation(d3.width/2 - displayFrame.getWidth()/2, d3.height/2 - displayFrame.getHeight()/2);
    }
    /**
     * Adds the map frame with proper layout of house icons
     */
    public void addMapFrame()
    {
        mapFrame = new JFrame("Map");
        mapFrame.setMinimumSize(new Dimension(1274,961));
        mapFrame.setMaximumSize(new Dimension(1274,961));           
        
        nextButton2 = new JButton("Next");
        JPanel buttonPane2 = new JPanel (new BorderLayout());
        previousButton2 = new JButton("Previous");
        buttonPane2.add(previousButton2, BorderLayout.WEST);
        buttonPane2.add(nextButton2, BorderLayout.EAST);
        
        mapContentPane = (JPanel)mapFrame.getContentPane();
        mapContentPane.setBorder(new EmptyBorder(8, 8, 8, 8));
        mapContentPane.setLayout(new BorderLayout(8, 8));
        mapContentPane.add(buttonPane2,BorderLayout.SOUTH);
        try{
            BufferedImage image = ImageIO.read(new File("boroughmap1.jpg"));
            ImageIcon icon = new ImageIcon(image);
            JLabel label = new JLabel(icon);
            mapContentPane.add(label);
            for(int i=0;i<33;i++)
            {    
                JButton house = new JButton();
                String iconSize = "";
                int x = 0, y = 0; 
                int noOfPr = count(elements[i],selectedItem, selectedItem2);
                if(statistic.countProperties(elements[i]) < 1000){
                    iconSize = "small";
                    x = y = 10;
                    house.setIcon(new ImageIcon("image1small.jpg"));
                }
                else if(statistic.countProperties(elements[i]) > 1000 && statistic.countProperties(elements[i]) < 1500)
                {
                    iconSize = "medium";
                    x = y = 30;
                    house.setIcon(new ImageIcon("image1medium.jpg"));
                }
                else if(statistic.countProperties(elements[i]) > 1500){
                    iconSize = "large";
                    x = y = 50;
                    house.setIcon(new ImageIcon("image1large.jpg"));
                }
                String temporary = elements[i];
                house.addActionListener(e->
                { 
                    selectedBorough = temporary; 
                    selectedBoroughDetails();
                    displayFrame.setVisible(true);
                }
                );
                nameToXs.get(elements[i]);
                coordinates.get(nameToXs.get(elements[i]));
                house.setBounds(nameToXs.get(elements[i]), coordinates.get(nameToXs.get(elements[i])), x, y);
                label.add(house); 
            }      
        }
        catch(IOException e) 
        {
            return;
        }

        nextButton2.addActionListener(e->
        {
            mapFrame.dispose();
            statisticsFrame.setVisible(true);
            //mapFrame.dispose();
            
        }
        );
        
        previousButton2.addActionListener(e->
        {
            makeFrame();
            mapFrame.dispose();
        }
        );
        
        Dimension d1 = Toolkit.getDefaultToolkit().getScreenSize();
        mapFrame.setLocation(d1.width/2 - welcomeFrame.getWidth()/2, d1.height/2 - welcomeFrame.getHeight()/2);
        mapFrame.setVisible(true);
        mapFrame.pack();
        
    }
    /**
     * creates an array storing the boroughs of London
     */
    public void createArray()
    {
        elements[0]="Westminster"; 
        elements[1]="Harrow";
        elements[2]="Brent";
        elements[3]="Ealing";
        elements[4]="Hillingdon";
        elements[5]="Hounslow"; 
        elements[6]="Richmond upon Thames";
        elements[7]="Kingston upon Thames";
        elements[8]="Wandsworth";
        elements[9]="Lambeth";
        elements[10]="Merton";
        elements[11]="Sutton";
        elements[12]="Croydon";
        elements[13]="Bromley";
        elements[14]="Lewisham";
        elements[15]="Southwark";
        elements[16]="Greenwich";
        elements[17]="Bexley";
        elements[18]="Havering";
        elements[19]="Barking and Dagenham";
        elements[20]="Newham";
        elements[21]="Redbridge";
        elements[22]="Waltham Forest";
        elements[23]="Tower Hamlets";
        elements[24]="Hackney";
        elements[25]="Islington";
        elements[26]="City of London";
        elements[27]="Haringey";
        elements[28]="Enfield";
        elements[29]="Barnet";
        elements[30]="Camden";
        elements[31]="Kensington and Chelsea";
        elements[32]="Hammersmith and Fullham";
    }
    
    /**
     * Makes Panels,grids, buttons and Labels in Stats panel
     */
    private void statisticsPage()
    {
        
        //Creates the panel for each statistic
        JPanel statistic1 = new JPanel(new BorderLayout());
        JPanel statistic2 = new JPanel(new BorderLayout());
        JPanel statistic3 = new JPanel(new BorderLayout());
        JPanel statistic4 = new JPanel(new BorderLayout());
        
        //CReates the label for each statistic
        JLabel statisticTitle1 = new JLabel(""); //Creates a label to show the statistic title
        statistic1.add(statisticTitle1, BorderLayout.NORTH);  //Aligns the statistic title in the north
        statisticTitle1.setHorizontalAlignment(JLabel.CENTER);  //Aligns the statistic title in the center
        title = statistic.nextStatisticTitle();
        statisticTitle1.setText(title);
        JLabel statisticLabel1 = new JLabel("");  //Creates a label to show the statistic Value
        statisticLabel1.setHorizontalAlignment(JLabel.CENTER);
        statisticLabel1.setText(statistic.statisticData(title));
        
        JLabel statisticTitle2 = new JLabel(""); //Creates a label to show the statistic title
        statistic2.add(statisticTitle2, BorderLayout.NORTH);  //Aligns the statistic title in the north
        statisticTitle2.setHorizontalAlignment(JLabel.CENTER);  //Aligns the statistic title in the center
        title = statistic.nextStatisticTitle();
        statisticTitle2.setText(title);
        JLabel statisticLabel2 = new JLabel();  //Creates a label to show the statistic Value
        statisticLabel2.setHorizontalAlignment(JLabel.CENTER);
        statisticLabel2.setText(statistic.statisticData(title));
        
        JLabel statisticTitle3 = new JLabel(""); //Creates a label to show the statistic title
        statistic3.add(statisticTitle3, BorderLayout.NORTH);  //Aligns the statistic title in the north
        statisticTitle3.setHorizontalAlignment(JLabel.CENTER);  //Aligns the statistic title in the center
        title = statistic.nextStatisticTitle();
        statisticTitle3.setText(title);
        JLabel statisticLabel3 = new JLabel();  //Creates a label to show the statistic Value
        statisticLabel3.setHorizontalAlignment(JLabel.CENTER);
        statisticLabel3.setText(statistic.statisticData(title));
        
        JLabel statisticTitle4 = new JLabel(""); //Creates a label to show the statistic title
        statistic4.add(statisticTitle4, BorderLayout.NORTH);  //Aligns the statistic title in the north
        statisticTitle4.setHorizontalAlignment(JLabel.CENTER);  //Aligns the statistic title in the center
        title = statistic.nextStatisticTitle();
        statisticTitle4.setText(title);
        JLabel statisticLabel4 = new JLabel();  //Creates a label to show the statistic Value
        statisticLabel4.setHorizontalAlignment(JLabel.CENTER);
        statisticLabel4.setText(statistic.statisticData(title));
        
        //Adds the label to the panel 
        statistic1.add(statisticLabel1, BorderLayout.CENTER);
        statistic2.add(statisticLabel2, BorderLayout.CENTER);
        statistic3.add(statisticLabel3, BorderLayout.CENTER);
        statistic4.add(statisticLabel4, BorderLayout.CENTER);
   
        //Adds the panel to the grid layout 
        statisticsGrid.add(statistic1);
        statisticsGrid.add(statistic2);
        statisticsGrid.add(statistic3);
        statisticsGrid.add(statistic4);
        
        //Creates the next and previous buttons for the panel 
        JButton previousStat1 = new JButton("<");
        JButton nextStat1 = new JButton(">");
        nextStat1.addActionListener(e->
        {
            nextTitle = statistic.nextStatisticTitle();
            statisticTitle1.setText(nextTitle);
            statisticLabel1.setText(statistic.statisticData(nextTitle));
        }
        );
        previousStat1.addActionListener(e->
        {
            previousTitle = statistic.previousStatisticTitle();
            statisticTitle1.setText(previousTitle);
            statisticLabel1.setText(statistic.statisticData(previousTitle));
        }
        );
        
        JButton previousStat2 = new JButton("<");
        JButton nextStat2 = new JButton(">");
        nextStat2.addActionListener(e->
        {
            nextTitle = statistic.nextStatisticTitle();
            statisticTitle2.setText(nextTitle);
            statisticLabel2.setText(statistic.statisticData(nextTitle));
        }
        );
        previousStat2.addActionListener(e->
        {
            previousTitle = statistic.previousStatisticTitle();
            statisticTitle2.setText(previousTitle);
            statisticLabel2.setText(statistic.statisticData(previousTitle));
        }
        );
        
        JButton previousStat3 = new JButton("<");
        JButton nextStat3 = new JButton(">");
        nextStat3.addActionListener(e->
        {
            nextTitle = statistic.nextStatisticTitle();
            statisticTitle3.setText(nextTitle);
            statisticLabel3.setText(statistic.statisticData(nextTitle));
        }
        );
        previousStat3.addActionListener(e->
        {
            previousTitle = statistic.previousStatisticTitle();
            statisticTitle3.setText(previousTitle);
            statisticLabel3.setText(statistic.statisticData(previousTitle));
        }
        );
        
        JButton previousStat4 = new JButton("<");
        JButton nextStat4 = new JButton(">");
        nextStat4.addActionListener(e->
        {
            nextTitle = statistic.nextStatisticTitle();
            statisticTitle4.setText(nextTitle);
            statisticLabel4.setText(statistic.statisticData(nextTitle));
        }
        );
        previousStat4.addActionListener(e->
        {
            previousTitle = statistic.previousStatisticTitle();
            statisticTitle4.setText(previousTitle);
            statisticLabel4.setText(statistic.statisticData(previousTitle));
        }
        );
        //Adds the buttons to the panel
        statistic1.add(previousStat1, BorderLayout.WEST);
        statistic1.add(nextStat1, BorderLayout.EAST);
        
        statistic2.add(previousStat2, BorderLayout.WEST);
        statistic2.add(nextStat2, BorderLayout.EAST);
        
        statistic3.add(previousStat3, BorderLayout.WEST);
        statistic3.add(nextStat3, BorderLayout.EAST);
        
        statistic4.add(previousStat4, BorderLayout.WEST);
        statistic4.add(nextStat4, BorderLayout.EAST);
        //Shows the statistics grid
        statisticsGrid.setVisible(true);
    }
    /**
     * Counts the number of neighbourhoods between the price range. 
     */
     public int count(String neighbourhood, int lowerPrice , int upperPrice)
    {
       int count=0;
       AirbnbDataLoader loader = new AirbnbDataLoader();
       
       for (AirbnbListing property : loader.load()){
          if(property.getNeighbourhood().equalsIgnoreCase(neighbourhood) && property.getPrice()>=lowerPrice && property.getPrice()<=upperPrice){
            count = count + 1;
         }
       }
        return count;
    }
   
    /**
     * Challenge task: creates a panel to store Buyer details.
     */
    public void addPersonalDetailsFrame()
    {
        detailsFrame = new JFrame("Buyer Details Panel");
        detailsFrame.setMinimumSize(new Dimension(800,800));
        detailsFrame.setMaximumSize(new Dimension(800,800));
        
        detailsPane = new JPanel();
        detailsPane = (JPanel)detailsFrame.getContentPane();
        detailsPane.setLayout(new GridLayout(5, 0));
        
        namePanel = new JPanel();
        emailPanel = new JPanel();
        phonePanel = new JPanel();
        
        namePanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        phonePanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        emailPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        
        JLabel nameLabel = new JLabel("Name : ");
        JLabel phoneLabel = new JLabel("Phone : ");
        JLabel emailLabel = new JLabel("Email : ");

        JTextField nameField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField emailField = new JTextField();
        
        nameField.setPreferredSize(new Dimension(300,25));
        phoneField.setPreferredSize(new Dimension(300,25));
        emailField.setPreferredSize(new Dimension(300,25));
        
        namePanel.add(nameLabel);
        namePanel.add(nameField);
        
        phonePanel.add(phoneLabel);
        phonePanel.add(phoneField);
        
        emailPanel.add(emailLabel);
        emailPanel.add(emailField);
        
        
        previousButton3 = new JButton("Previous");
        previousButton3.setPreferredSize(new Dimension(100,50));
        previousButton3.addActionListener(e->
        {
            statisticsFrame.setVisible(true);
            detailsFrame.dispose();
        }
        );
        
        JButton addDetailsButton = new JButton("Add Details");
        addDetailsButton.setPreferredSize(new Dimension(100,50));
        addDetailsButton.addActionListener(e->
        {
            if(nameField.getText().equals("") || phoneField.getText().equals("") || emailField.getText().equals("")){
                JOptionPane.showMessageDialog(detailsFrame, "Input appropriate details.");
            } else {
                nameEntries.add(nameField.getText());
                phoneEntries.add(nameField.getText());
                emailEntries.add(nameField.getText());
                JOptionPane.showMessageDialog(detailsFrame, "Details added to database.");
            }
        }
        );
        
        detailsPane.add(namePanel);
        detailsPane.add(phonePanel);
        detailsPane.add(emailPanel);
        detailsPane.add(previousButton3);
        detailsPane.add(addDetailsButton);
        
        detailsFrame.pack();
        detailsFrame.setVisible(false);
        
        Dimension d2 = Toolkit.getDefaultToolkit().getScreenSize();
        detailsFrame.setLocation(d2.width/2 - welcomeFrame.getWidth()/2, d2.height/2 - welcomeFrame.getHeight()/2);
    }
}
