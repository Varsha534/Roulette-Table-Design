public class ProbabilityCalculations 
{
    
    // Winning (= 1), Losing (= 0), or Neutral (1000) status of the player
    int winLoss;

    // Possible sets of numbers that the ball can land on
    // int[] setStraight = { 00, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
    //                       25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36 };

    int[] setRed = { 1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36 };

    int[] setBlack = { 2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35 };

    //int[] setCombo1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18 };

    //int[] setCombo2 = { 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36 };


    // Getting checkbox values from the display file to use for calculations
    public double calculateWinnings(int randomNumber, double credits, double betAmount, boolean oddChecked, boolean evenChecked,
            boolean lowChecked, boolean highChecked, boolean redChecked, boolean blackChecked,
            boolean doubleZeroChecked) 
    {

        // Define the payout multipliers for each betting combination
        double payoutStraight = 35.0;
        double payoutRedBlackOddEven = 1.0;
        double payout1to18 = 1.0;
        double payout19to36 = 1.0;
        //double payoutDoubleZero = 2.0;


        // Check if the bet was placed on a straight number
        if (randomNumber >= 0 && randomNumber <= 36) 
        {
            if (oddChecked && (randomNumber % 2 != 0)) 
            { // Odd
                winLoss = 1;
                credits = credits + (betAmount * payoutRedBlackOddEven);
            } 
            else if (evenChecked && (randomNumber % 2 == 0)) 
            { // Even
                winLoss = 1;
                credits = credits + (betAmount * payoutRedBlackOddEven);
            } 
            else if (lowChecked && (randomNumber >= 1) && (randomNumber <= 18)) 
            { // 1-18
                winLoss = 1;
                credits = credits + (betAmount * payout1to18);
            } 
            else if (highChecked && (randomNumber >= 19) && (randomNumber <= 36)) 
            { // 19-36
                winLoss = 1;
                credits = credits + (betAmount * payout19to36);
            } 
            else if (redChecked && isRed(randomNumber)) 
            { // Red
                winLoss = 1;
                credits = credits + (betAmount * payoutRedBlackOddEven);
            } 
            else if (blackChecked && isBlack(randomNumber)) 
            { // Black
                winLoss = 1;
                credits = credits + (betAmount * payoutRedBlackOddEven);
            } 
            else if (doubleZeroChecked && (randomNumber == 0)) 
            { // 00
                winLoss = 1;
                credits = credits + (betAmount * payoutStraight);
            } 
            else 
            {
                winLoss = 0;
                credits = credits - betAmount;
            }
        } 
        else if (randomNumber == 38) 
        { // Check if the bet was placed on a straight number
            winLoss = 1;
            credits = credits + (betAmount * payoutStraight);
        } 
        else 
        {
            credits = credits - betAmount;
            winLoss = 1000; // Neutral status when an unexpected situation occurs
        }

        return credits;
    }

    
    // returning the win/loss status from the calculations performed at the backend to the user in the frontend
    public int winStatus() 
    {
        return winLoss;
    }

    // Helper method to check if a number is red
    private boolean isRed(int number) 
    {
        return presentInArray(setRed, number);
    }

    // Helper method to check if a number is black
    private boolean isBlack(int number) 
    {
        return presentInArray(setBlack, number);
    }

    // Helper method to check if a number is present in the defined array
    private boolean presentInArray(int[] arr, int n) 
    {
        for (int i : arr) 
        {
            if (n == i) 
            {
                return true;
            }
        }
        return false;
    }
}
