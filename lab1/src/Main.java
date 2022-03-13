import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        String pathIn = "src/source.txt";
        String pathOut= "src/result.txt";
        
        Lexer lexer=new Lexer();
        FileIO io=new FileIO();

        try{
            io.getFile(pathIn);
            ArrayList<String> words=io.getWords();
            StringBuffer outputbuffer=lexer.lexicalAnalyse(words);
            io.Output(pathOut,outputbuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}