
import java.text.NumberFormat;


public class NumQuestionManager {
    
    public static int listNumAlreadyUsed =  0;
    
    public String getNumQuestion(){
        int numberAsInt = listNumAlreadyUsed++;
        int size = 10;
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumIntegerDigits(size);
        nf.setGroupingUsed(false);
        String numberAsString = nf.format(numberAsInt);
        return numberAsString;
    }
}
