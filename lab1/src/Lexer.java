import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Lexer {
    private static Map<String, Integer> keyCode;
    private static int ERRORNUM; // 记录词法分析错误的个数

    static{
        String[] keys = {"void","char","int","short","long","float","double","static", "const",
                        "for","do","while","continue","break","return","switch","case",
                        "default","if","else","struct","sizeof","union","enum","typedef"};
        String[] tokens = {"=","==",">","<",">=","<=","+","-","*","/","(",")","[","]","{","}",",",":",";"};
        keyCode =new HashMap<>();
        ERRORNUM=0;
        int i=1;
        for(String key:keys){
            keyCode.put(key,i++);
        }
        keyCode.put("id",26);
        keyCode.put("num",30);
        i=41;
        for(String token:tokens){
            keyCode.put(token,i++);
        }
    }

    private ArrayList<Pair> classifyWords(ArrayList<String> words){
        ArrayList<Pair> results=new ArrayList<>();
        //逐个判断
        for(String word:words){
            //在种别码中，是关键字key或标识符token
            if(keyCode.containsKey(word)){
                results.add(new Pair(keyCode.get(word),word));
            }else{
                //在种别码中，是标识符
                if(Character.isLetter(word.charAt(0))||word.charAt(0)=='_'){
                    results.add(new Pair(SpeciesCode.ID,word));
                }
                //在种别码中，是数字
                else if(Character.isDigit(word.charAt(0))){
                    results.add(new Pair(SpeciesCode.NUM,word));
                }
                //不在种别码中，不在C语言词法范围内
                else{
                    results.add(new Pair(SpeciesCode.ERROR,word));
                    ERRORNUM++;
                }
            }
        }
        return results;
    }

    public StringBuffer lexicalAnalyse(ArrayList<String> words){
        ArrayList<Pair> result=classifyWords(words);

        System.out.println("词法分析结果如下：\n<单词种别码，单词>          //所属类别");
        StringBuffer outputBuffer = new StringBuffer("词法分析结果如下：\n<单词种别码，单词>          //所属类别\n");

        outputBuffer.append("词法分析结束！共" + ERRORNUM + "个无法识别的符号\n");
        System.out.println("词法分析结束！共" + ERRORNUM + "个无法识别的符号");

        for(Pair pair:result){
            outputBuffer.append("< "+pair.key+" , "+pair.value+">    ");
            System.out.print("< "+pair.key+" , "+pair.value+">    ");
            if(pair.key>0 &&pair.key<SpeciesCode.ID){
                outputBuffer.append("/*关键字*/\n");
                System.out.println("/*关键字*/");
            }else if(pair.key==SpeciesCode.ID){
                outputBuffer.append("/*标识符*/\n");
                System.out.println("/*标识符*/");
            }else if(pair.key==SpeciesCode.NUM){
                outputBuffer.append("/*常量*/\n");
                System.out.println("/*常量*/");
            }else if(pair.key>=SpeciesCode.AS && pair.key<=SpeciesCode.DIV){
                outputBuffer.append("/*运算符*/\n");
                System.out.println("/*运算符*/");
            }else if(pair.key>=SpeciesCode.LP && pair.key<=SpeciesCode.SEM){
                outputBuffer.append("/*界限种别码*/\n");
                System.out.println("/*界限种别码*/");
            }else if(pair.key==SpeciesCode.ERROR){
                outputBuffer.append("/*无法识别的符号*/\n");
                System.out.println("!!!无法识别的符号!!!");
            }
        }
        return outputBuffer;
    }


}
