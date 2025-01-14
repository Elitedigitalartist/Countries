//David Sagoua Period: 7
//This run the game
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class Main 
{

  // array of 10 Country objects
  private Country[] countryArray = new Country[10];  
  // index of current shown country
  private int index = 0;

  // GUI elements
  private JFrame jFrame = new JFrame("Countries");
  private ImageIcon img;
  private JLabel imageLabel;
  private JLabel outputLabel;
  private JTextField userInput;
  private String[] questions = {"What country is this?","What is the capital of this country?","What is the primary language of this country?"};
  private String gameQuestion;
  
  public static void main(String[] args) {
    // Create the GUI
    Main gui = new Main();
    gui.loadCountries();
    gui.showCountry();
  }

  /* loadCountries() reads in the data from the countries-data.csv file and fills in the countryArray with data. You need to add the loop that reads in the country data into the array. */
  public void loadCountries() 
  {
    // Open the data file - do not change
    File file = new File("/workspaces/Countries/workspace/countries-data.csv");
    Scanner scan = null;
    try {
      scan = new Scanner(file);
    } catch(FileNotFoundException e) { 
        System.out.println("File not found");     
    }
    
    // Write a for loop that goes through the countryArray.
    // for(int i ....) {
    // Do the following inside the loop
    for (int i = 0; i < countryArray.length; i++) {
      String input = scan.nextLine();
      String[] data = input.split(",");
      System.out.println("Read in " + data[0]);
      // inside the loop, create a new Country using your constructor with 3 arguments and pass in data[0], data[1], data[2], data[3] as arguments.
      Country nation = new Country(data[0], data[1], data[2], data[3]);
     // inside the loop, set countryArray[i] to the created Country object
     countryArray[i] = nation;
    }
     
    
  }

  /* showCountry() will show the image associated with the current country. It should get the country at index from the countryArray. It should use its get method to get its image file name and use the code below to put the image in the GUI.
  */
  public void showCountry() {
    // Get the country at index from countryArray
    Country c = countryArray[index];

    
    // Use its get method to get the its image file name and save it into imagefile variable below instead of worldmap.jpg.
    String imagefile = c.getImagefile();
    // Use the following code to create an new Image Icon and put it into the GUI
    img = new ImageIcon("/workspaces/Countries/workspace/"+imagefile);
    imageLabel.setIcon(img);
  }
  
  /* nextButton should increment index. If the index is greater than 9, reset it back to 0. Clear the outputLabel to empty string using setText, and call showCountry();*/
  public void nextButtonClick()
  {
    if (index > 8)  {
      index = 0;
    }
    else {
      index++;
    }
    outputLabel.setText(null);
    showCountry();
  }
  
  /* reviewButton should get the country at index from the countryArray, call its toString() method and save the result, print it out with System.out.println and as an argument to outputLabel.setText( text to print out ); */
  public void reviewButtonClick()
  {
    String savedResult = countryArray[index].toString();
    System.out.println(savedResult);
    outputLabel.setText(savedResult);

  }

  /* quizButton should clear the outputLabel (outputLabel.setText to empty string), get the country at index from countryArray, print out a question about it like What country is this? and/or What's this country's capital?. Get the user's answer using scan.nextLine() and check if it is equal to the country's data using its get methods and print out correct or incorrect.
  */
  public void quizButtonClick()
  {
    gameQuestion = questions[(int)(Math.random() * 3)];
    outputLabel.setText(gameQuestion);
    System.out.println("\n" + gameQuestion);
    
  }
  public void checkButtonClick()
  {
    System.out.println(userInput.getText());
    Country questionCountry = countryArray[index];
    if ((gameQuestion.equals(questions[0]) && userInput.getText().equals(questionCountry.getName()) || userInput.getText().equals(questionCountry.getName().toLowerCase()) || userInput.getText().equals(questionCountry.getName().toUpperCase())) || (gameQuestion.equals(questions[1]) && userInput.getText().equals(questionCountry.getCapital()) || userInput.getText().equals(questionCountry.getCapital().toLowerCase()) || userInput.getText().equals(questionCountry.getCapital().toUpperCase())) || (gameQuestion.equals(questions[2]) && userInput.getText().equals(questionCountry.getLanguage()) || userInput.getText().equals(questionCountry.getLanguage().toLowerCase()) || userInput.getText().equals(questionCountry.getLanguage().toUpperCase()))) {
      System.out.println("\nCorrect!!!");
      outputLabel.setText("Correct!!!"); 
    }
    else {
      System.out.println("Incorrect!!!");
      outputLabel.setText("Incorrect!!!"); 
    }
  }




  /* Do NOT change anything below here */
  /* The Main() constructor is finished and will construct the GUI */
public Main() {
    jFrame.setLayout(new FlowLayout());
    jFrame.setSize(500, 360);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // buttons at the top
        JButton reviewButton = new JButton("Review");
        JButton quizButton = new JButton("Quiz");
        JButton checkButton = new JButton("Check Answer");
        JButton newButton = new JButton("Next");
        jFrame.add(reviewButton);
        jFrame.add(quizButton);
        jFrame.add(checkButton);
        jFrame.add(newButton);
        
        // create a new image icon
        img = new ImageIcon("worldmap.jpg");
        // create a label to display image
        imageLabel = new JLabel(img);
        // and one for output
        outputLabel = new JLabel();
        jFrame.add(imageLabel);
        jFrame.add(outputLabel);
        userInput = new JTextField(20);
        jFrame.add(userInput);
        jFrame.setVisible(true);
        // add event listener for button click
        reviewButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) 
    {
      reviewButtonClick();
    }
        });
    quizButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) 
    {
      quizButtonClick();
    }
    });
    checkButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) 
      {
        checkButtonClick();
      }
      });
   
   newButton.addActionListener(new ActionListener()  {
    public void actionPerformed(ActionEvent e) 
    {
      nextButtonClick();
    }
   });
}
  

}
