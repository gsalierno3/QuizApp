package com.example.android.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    private HashMap<String,String> stateAbbreviation = null; // holds the pair state-abbreviation
    private HashMap<String,String> stateCapitals = null; // holds the pair state-capital
    private String[] states; //holds an array with all the states.
    private String firstTargetState=""; //holds a state name selected at random within the white section
    private String firstTargetCapital=""; // holds the capital of firstTargetState
    private String targetCapital=null;  //holds the state capital
    private int whiteAttempts = 0; //holds number of attempts for guessing the white section

    //variables for light blue section (middle quiz)
    private String [] capitals= new String[4];
    private String secondTargetState;
    private String secondTargetCapital;
    private int lightBlueAttempts = 0;  //holds number of attempts for guessing the light blue section
    private int selection=-1;
    private int guessLocation=0; //holds the radio button with the right answer.

    private String[] majorCities;
    private String[] cities = new String[4];

    private boolean[] cityIsCapital;
    private int darkBlueAttempts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFacts();
        whiteQuiz();
        setMidQuiz();
        setBlueQuiz();
    }

    /**
     * sets the dictionaries and array containing state names, capitals and abbreviations
     */
    private void setFacts()
    {
        stateCapitals = new HashMap<String, String>();

        stateCapitals.put("Alabama","Montgomery");
        stateCapitals.put("Alaska","Juneau");
        stateCapitals.put("Arizona","Phoenix");
        stateCapitals.put("Arkansas","Little Rock");
        stateCapitals.put("California","Sacramento");
        stateCapitals.put("Colorado","Denver");
        stateCapitals.put("Connecticut","Hartford");
        stateCapitals.put("Delaware","Dover");
        stateCapitals.put("Florida","Tallahassee");
        stateCapitals.put("Georgia","Atlanta");
        stateCapitals.put("Hawaii","Honolulu");
        stateCapitals.put("Idaho","Boise");
        stateCapitals.put("Illinois","Springfield");
        stateCapitals.put("Indiana","Indianapolis");
        stateCapitals.put("Iowa","Des Moines");
        stateCapitals.put("Kansas","Topeka");
        stateCapitals.put("Kentucky","Frankfort");
        stateCapitals.put("Louisiana","Baton Rouge");
        stateCapitals.put("Maine","Augusta");
        stateCapitals.put("Maryland","Annapolis");
        stateCapitals.put("Massachusetts","Boston");
        stateCapitals.put("Michigan","Lansing");
        stateCapitals.put("Minnesota","St. Paul");
        stateCapitals.put("Mississippi","Jackson");
        stateCapitals.put("Missouri","Jefferson City");
        stateCapitals.put("Montana","Helena");
        stateCapitals.put("Nebraska","Lincoln");
        stateCapitals.put("Nevada","Carson City");
        stateCapitals.put("New Hampshire","Concord");
        stateCapitals.put("New Jersey","Trenton");
        stateCapitals.put("New Mexico","Santa Fe");
        stateCapitals.put("New York","Albany");
        stateCapitals.put("North Carolina","Raleigh");
        stateCapitals.put("North Dakota","Bismarck");
        stateCapitals.put("Ohio","Columbus");
        stateCapitals.put("Oklahoma","Oklahoma City");
        stateCapitals.put("Oregon","Salem");
        stateCapitals.put("Pennsylvania","Harrisburg");
        stateCapitals.put("Rhode Island","Providence");
        stateCapitals.put("South Carolina","Columbia");
        stateCapitals.put("South Dakota","Pierre");
        stateCapitals.put("Tennessee","Nashville");
        stateCapitals.put("Texas","Austin");
        stateCapitals.put("Utah","Salt Lake City");
        stateCapitals.put("Vermont","Montpelier");
        stateCapitals.put("Virginia","Richmond");
        stateCapitals.put("Washington","Olympia");
        stateCapitals.put("West Virginia","Charleston");
        stateCapitals.put("Wisconsin","Madison");
        stateCapitals.put("Wyoming","Cheyenne");


        states = new String[]{
                "Alabama",
                "Alaska",
                "Arizona",
                "Arkansas",
                "California",
                "Colorado",
                "Connecticut",
                "Delaware",
                "Florida",
                "Georgia",
                "Hawaii",
                "Idaho",
                "Illinois",
                "Indiana",
                "Iowa",
                "Kansas",
                "Kentucky",
                "Louisiana",
                "Maine",
                "Maryland",
                "Massachusetts",
                "Michigan",
                "Minnesota",
                "Mississippi",
                "Missouri",
                "Montana",
                "Nebraska",
                "Nevada",
                "New Hampshire",
                "New Jersey",
                "New Mexico",
                "New York",
                "North Carolina",
                "North Dakota",
                "Ohio",
                "Oklahoma",
                "Oregon",
                "Pennsylvania",
                "Rhode Island",
                "South Carolina",
                "South Dakota",
                "Tennessee",
                "Texas",
                "Utah",
                "Vermont",
                "Virginia",
                "Washington",
                "West Virginia",
                "Wisconsin",
                "Wyoming"};


        majorCities = new String[]{
                "New York",
                "Los Angeles",
                "Chicago",
                "Houston",
                "Philadelphia",
                "Phoenix",
                "San Antonio",
                "San Diego",
                "Dallas",
                "San Jose",
                "Austin",
                "Jacksonville",
                "San Francisco",
                "Indianapolis",
                "Columbus",
                "Fort Worth",
                "Charlotte",
                "Seattle",
                "Denver",
                "El Paso",
                "Detroit",
                "Washington",
                "Boston",
                "Memphis",
                "Nashville",
                "Portland",
                "Oklahoma City",
                "Las Vegas",
                "Baltimore",
                "Louisville",
                "Milwaukee",
                "Albuquerque",
                "Tucson",
                "Fresno",
                "Sacramento",
                "Kansas City",
                "Long Beach",
                "Mesa",
                "Atlanta",
                "Colorado Springs",
                "Virginia Beach",
                "Raleigh",
                "Omaha",
                "Miami",
                "Oakland",
                "Minneapolis",
                "Tulsa",
                "Wichita",
                "New Orleans",
                "Arlington",
                "Cleveland",
                "Bakersfield",
                "Tampa",
                "Aurora",
                "Honolulu",
                "Anaheim",
                "Santa Ana",
                "Corpus Christi",
                "Riverside",
                "St. Louis",
                "Lexington",
                "Stockton",
                "Pittsburgh",
                "Saint Paul",
                "Anchorage",
                "Cincinnati",
                "Henderson",
                "Greensboro",
                "Plano",
                "Newark",
                "Toledo",
                "Lincoln",
                "Orlando",
                "Chula Vista",
                "Jersey City",
                "Chandler",
                "Fort Wayne",
                "Buffalo",
                "Durham",
                "St. Petersburg",
                "Irvine",
                "Laredo",
                "Lubbock",
                "Madison",
                "Gilbert",
                "Norfolk",
                "Reno",
                "Winston–Salem",
                "Glendale",
                "Hialeah",
                "Garland",
                "Scottsdale",
                "Irving",
                "Chesapeake",
                "North Las Vegas",
                "Fremont",
                "Baton Rouge",
                "Richmond",
                "Boise",
                "San Bernardino",
                "Spokane",
                "Birmingham",
                "Modesto",
                "Des Moines",
                "Rochester",
                "Tacoma",
                "Fontana",
                "Oxnard",
                "Moreno Valley",
                "Fayetteville",
                "Huntington Beach",
                "Yonkers",
                "Glendale",
                "Aurora",
                "Montgomery",
                "Columbus",
                "Amarillo",
                "Little Rock",
                "Akron",
                "Shreveport",
                "Augusta",
                "Grand Rapids",
                "Mobile",
                "Salt Lake City",
                "Huntsville",
                "Tallahassee",
                "Grand Prairie",
                "Overland Park",
                "Knoxville",
                "Worcester",
                "Brownsville",
                "Newport News",
                "Santa Clarita",
                "Port St. Lucie",
                "Providence",
                "Fort Lauderdale",
                "Chattanooga",
                "Tempe",
                "Oceanside",
                "Garden Grove",
                "Cape Coral",
                "Santa Rosa",
                "Vancouver",
                "Sioux Falls",
                "Jackson",
                "Springfield",
                "Pembroke Pines",
                "Salem",
                "Corona",
                "McKinney",
                "Fort Collins",
                "Lancaster",
                "Springfield",
                "Alexandria",
                "Pomona",
                "Kansas City",
                "Bridgeport",
                "Savannah",
                "Pasadena",
                "Dayton",
                "Columbia",
                "Charleston",
                "New Haven",
                "Stamford",
                "Concord",
                "Hartford",
                "Athens",
                "Victorville",
                "Abilene",
                "Vallejo",
                "Berkeley",
                "Norman",
                "Allentown",
                "Columbia",
                "Beaumont",
                "Independence",
                "Springfield",
                "Round Rock",
                "Wilmington",
                "Costa Mesa",
                "Miami Gardens",
                "Westminster",
                "Clearwater",
                "Fairfield",
                "Rochester",
                "Elgin",
                "Temecula",
                "West Jordan",
                "Inglewood",
                "Richardson",
                "Lowell",
                "Gresham",
                "Antioch",
                "Cambridge",
                "High Point",
                "Billings",
                "Manchester",
                "Murrieta",
                "Centennial",
                "Richmond",
                "Boulder",
                "Norwalk",
                "Davenport",
                "Montgomery",
                "Juneau",
                "Phoenix",
                "Little Rock",
                "Sacramento",
                "Denver",
                "Hartford",
                "Dover",
                "Tallahassee",
                "Atlanta",
                "Honolulu",
                "Boise",
                "Springfield",
                "Indianapolis",
                "Des Moines",
                "Topeka",
                "Frankfort",
                "Baton Rouge",
                "Augusta",
                "Annapolis",
                "Boston",
                "Lansing",
                "St. Paul",
                "Jackson",
                "Jefferson City",
                "Helena",
                "Lincoln",
                "Carson City",
                "Concord",
                "Trenton",
                "Santa Fe",
                "Albany",
                "Raleigh",
                "Bismarck",
                "Columbus",
                "Oklahoma City",
                "Salem",
                "Harrisburg",
                "Providence",
                "Columbia",
                "Pierre",
                "Nashville",
                "Austin",
                "Salt Lake City",
                "Montpelier",
                "Richmond",
                "Olympia",
                "Charleston",
                "Madison",
                "Cheyenne"};

    }

    public void whiteQuiz()
    {
        Random rnd = new Random();
        int stateNumber = rnd.nextInt(50);

        firstTargetState = states[stateNumber];
        TextView tv = (TextView) findViewById(R.id.state_name);
        tv.setText(firstTargetState);

        firstTargetCapital=stateCapitals.get(firstTargetState);

        EditText et = (EditText) findViewById(R.id.state_capital);
        et.setText("");

    }

    public void newState(View v)
    {
        whiteQuiz();
    }

    /**
     * Resets white section
     * @param v
     */
    public void resetWhite(View v)
    {
        //resets answer
        EditText et = (EditText) findViewById(R.id.state_capital);
        et.setText("");

        // resets state
        TextView tv = (TextView) findViewById(R.id.state_name);
        tv.setText("state");

    }

    /**
     * gets user input from the capital EditText object and compares against the firstTargetCapital
     * @param v
     */
    public void checkCapital(View v)
    {
        EditText et = (EditText) findViewById(R.id.state_capital);
        String newGuess = et.getText().toString();

        if (firstTargetState.equals(""))
        {
            Toast.makeText(this,"Press the new button to randomly select a text", Toast.LENGTH_LONG).show();
        }
        else
        {
            if(firstTargetCapital.equalsIgnoreCase(newGuess))
            {
                if(whiteAttempts>=0)
                {
                    Toast.makeText(this,"YOU GOT IT!!", Toast.LENGTH_SHORT).show();
                    whiteAttempts = -100;
                }

                return;
            }
            else
            {
                whiteAttempts ++;
                if (whiteAttempts<6)
                {
                    Toast.makeText(this,"Try Again!!", Toast.LENGTH_SHORT).show();
                    et.setText("");
                    return;
                }
                et.setText(firstTargetCapital);
                return;
            }
        }

    } // end checkCapital

    /**
     * returns a random capital
     * @return
     */
    private String selectRandomCapital()
    {
        Random rnd = new Random();
        int stateNumber = rnd.nextInt(50);
        String randomState = states[stateNumber];
        return stateCapitals.get(randomState);
    }

    public void setMiddleQuiz(View v)
    {
        setMidQuiz();
    }

    private void setMidQuiz()
    {
        selection=-1;
        lightBlueAttempts=0;

        //initializes all capitals to ""
        for(int i=0;i<capitals.length; i++){
            capitals[i]="";
        }
        secondTargetState="";
        secondTargetCapital="";

        //selects a state at random
        Random rnd = new Random();
        int stateNumber = rnd.nextInt(50);
        secondTargetState = states[stateNumber];

        //write the state name at the top of screen
        TextView tv = (TextView) findViewById(R.id.second_target_state);
        tv.setText(secondTargetState);

        //looks up the city capital of the randomly selected state
        secondTargetCapital=stateCapitals.get(secondTargetState);

        //assigns this city capital a random location - to be used on the radiogroup
        guessLocation = rnd.nextInt(4);
        capitals[guessLocation]=secondTargetCapital;
        String randomCapital="";

        int i=0;
        while (i<capitals.length)
        {

            //if check if space is available.  if it is not, it means
            //that it has the right answer and we should skip to the
            //next position
            if(isNullOrEmpty(capitals[i]))
            {
                // checks that this random capital is not the same as
                //the target capital
                boolean sameCity = true;
                while(sameCity)
                {
                    randomCapital = selectRandomCapital();
                    if(randomCapital.equals(secondTargetCapital))
                    {
                        sameCity = true;
                    }
                    else
                    {
                        sameCity = false;
                    }
                }// end while

                //check that this capital has not been previously selected
                sameCity = false;
                for(int j=0; j<i; j++)
                {
                    if(randomCapital.equals(capitals[j]))sameCity = true;
                }
                if(!sameCity)
                {
                    capitals[i]=randomCapital;
                    i++;
                }
            }
            else
            {
                i++;
            }
        }// end while

        //assign each random capital to a radio button
        RadioButton rb0 = (RadioButton) findViewById(R.id.capital0);
        RadioButton rb1 = (RadioButton) findViewById(R.id.capital1);
        RadioButton rb2 = (RadioButton) findViewById(R.id.capital2);
        RadioButton rb3 = (RadioButton) findViewById(R.id.capital3);
        rb0.setText(capitals[0]);
        rb1.setText(capitals[1]);
        rb2.setText(capitals[2]);
        rb3.setText(capitals[3]);

        rb0.setChecked(false);
        rb1.setChecked(false);
        rb2.setChecked(false);
        rb3.setChecked(false);
    }//end setMiddleQuiz

    public void answerCapital(View v)
    {
        RadioButton rb0 = (RadioButton) findViewById(R.id.capital0);
        RadioButton rb1 = (RadioButton) findViewById(R.id.capital1);
        RadioButton rb2 = (RadioButton) findViewById(R.id.capital2);
        RadioButton rb3 = (RadioButton) findViewById(R.id.capital3);


        if(rb0.isChecked()) selection = 0;
        if(rb1.isChecked()) selection = 1;
        if(rb2.isChecked()) selection = 2;
        if(rb3.isChecked()) selection = 3;
        String guess = "";

        switch (selection)
        {
            case 0:
                guess=rb0.getText().toString();
                break;
            case 1:
                guess=rb1.getText().toString();
                break;
            case 2:
                guess=rb2.getText().toString();
                break;
            case 3:
                guess=rb3.getText().toString();
                break;
            default:
                Toast.makeText(this,"Please make a selection", Toast.LENGTH_SHORT).show();
        }

        if(guess.equals(secondTargetCapital))
        {
            Toast.makeText(this,"YOU GOT IT!!", Toast.LENGTH_LONG).show();
            lightBlueAttempts=0;
            selection = -1;
            return;
        }
        lightBlueAttempts++;
        if(lightBlueAttempts<6)
        {
            Toast.makeText(this,"Try Again!!", Toast.LENGTH_SHORT).show();
            return;
        }

        switch (guessLocation)
        {
            case 0:
                rb0.setChecked(true);
                break;
            case 1:
                rb1.setChecked(true);
                break;
            case 2:
                rb2.setChecked(true);
                break;
            case 3:
                rb3.setChecked(true);
                break;
        }
        Toast.makeText(this,"Check the right answer", Toast.LENGTH_LONG).show();
    }


    private String selectRandomCity()
    {
        Random rnd = new Random();
        int cityNumber = rnd.nextInt(majorCities.length);
        return majorCities[cityNumber];
    }

    public void setLowerQuiz(View v)
    {
        setBlueQuiz();
    }

    public void setBlueQuiz()
    {
        String randomCapital = selectRandomCapital();
        cityIsCapital = new boolean[4];
        darkBlueAttempts=0;
        for(int i=0; i<cities.length; i++)
        {
            cities[i]=selectRandomCity();
            cityIsCapital[i]=false;
        }

        CheckBox cb0 = (CheckBox) findViewById(R.id.city0);
        CheckBox cb1 = (CheckBox) findViewById(R.id.city1);
        CheckBox cb2 = (CheckBox) findViewById(R.id.city2);
        CheckBox cb3 = (CheckBox) findViewById(R.id.city3);

        Random rnd = new Random();
        int index = rnd.nextInt(4);

        cb0.setText(cities[0]);
        cb1.setText(cities[1]);
        cb2.setText(cities[2]);
        cb3.setText(cities[3]);

        switch(index)
        {
            case 0:
                cb0.setText(randomCapital);
                break;
            case 1:
                cb1.setText(randomCapital);
                break;
            case 2:
                cb2.setText(randomCapital);
                break;
            case 3:
                cb3.setText(randomCapital);
                break;
        }

        cb0.setChecked(false);
        cb1.setChecked(false);
        cb2.setChecked(false);
        cb3.setChecked(false);

        //check if city is a state capital
        if(stateCapitals.containsValue(cb0.getText().toString())) cityIsCapital[0]=true;
        if(stateCapitals.containsValue(cb1.getText().toString())) cityIsCapital[1]=true;
        if(stateCapitals.containsValue(cb2.getText().toString())) cityIsCapital[2]=true;
        if(stateCapitals.containsValue(cb3.getText().toString())) cityIsCapital[3]=true;

    }

    public void answerBlueQuiz(View v)
    {

        CheckBox cb0 = (CheckBox) findViewById(R.id.city0);
        CheckBox cb1 = (CheckBox) findViewById(R.id.city1);
        CheckBox cb2 = (CheckBox) findViewById(R.id.city2);
        CheckBox cb3 = (CheckBox) findViewById(R.id.city3);

        //verify that at least one box is checked
        boolean atLeastOne = false;
        if( cb0.isChecked())atLeastOne=true;
        if( cb1.isChecked())atLeastOne=true;
        if( cb2.isChecked())atLeastOne=true;
        if( cb3.isChecked())atLeastOne=true;
        if(!atLeastOne)
        {
            Toast.makeText(this,"Select at least one city",Toast.LENGTH_SHORT).show();
            return;
        }

        //compare each checkbox with its corresponding cityIsCapital array.
        boolean[] userAnsweredRight = new boolean[4];
        for(int i=0; i<userAnsweredRight.length; i++)
        {
            userAnsweredRight[i]=false;
        }

        boolean noRightAnswer=true;

        if (cb0.isChecked() == cityIsCapital[0]) userAnsweredRight[0]=true;
        if (cb1.isChecked() == cityIsCapital[1]) userAnsweredRight[1]=true;
        if (cb2.isChecked() == cityIsCapital[2]) userAnsweredRight[2]=true;
        if (cb3.isChecked() == cityIsCapital[3]) userAnsweredRight[3]=true;

        for(int i=0; i<userAnsweredRight.length; i++)
        {
            if(userAnsweredRight[i])
            {
                Toast.makeText(this,"Check Box No: "+ (i+1) + " is RIGHT", Toast.LENGTH_LONG).show();
                noRightAnswer=false;
            }
        }

        if(!noRightAnswer) return;

        darkBlueAttempts++;
        if(darkBlueAttempts<6)
        {
            Toast.makeText(this,"Try Again!!", Toast.LENGTH_SHORT).show();
            return;
        }
        cb0.setChecked(cityIsCapital[0]);
        cb1.setChecked(cityIsCapital[1]);
        cb2.setChecked(cityIsCapital[2]);
        cb3.setChecked(cityIsCapital[3]);
        Toast.makeText(this,"Please note the right answer", Toast.LENGTH_LONG).show();

    }


    /**
     * Checks if a string is null or empty
     * @param s
     * @return
     */
    private boolean isNullOrEmpty(String s)
    {
        Log.v("inside isNullOrEmply", s);
        boolean b = false;
        if (s == null || s.equals("")) b = true;
        return b;
    }

}
