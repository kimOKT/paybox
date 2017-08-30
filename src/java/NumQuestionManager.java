 
import java.text.NumberFormat;


public class NumQuestionManager {
    
    
    public static int listNumAlreadyUsed;
    
    public NumQuestionManager(){
        listNumAlreadyUsed = 0;
    }
    
    public static String getNumQuestion(){
        listNumAlreadyUsed = listNumAlreadyUsed + 1;
        int numberAsInt = listNumAlreadyUsed;
        int size = 10;
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumIntegerDigits(size);
        nf.setGroupingUsed(false);
        String numberAsString = nf.format(numberAsInt);
        return numberAsString;
    }
    
}
