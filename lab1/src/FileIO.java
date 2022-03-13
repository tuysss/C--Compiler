import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileIO {
    private static StringBuffer sb;//getfile和getwords共用一个缓冲区

    public void getFile(String filepath) throws IOException {
        InputStream is=new FileInputStream(filepath);
        String line;
        BufferedReader br=new BufferedReader(new InputStreamReader(is));//字符流节点
        sb=new StringBuffer();

        line=br.readLine();
        while(line!=null){
            sb.append(line+"\n");
            //System.out.println(line);
            line=br.readLine();
        }
        br.close();
        is.close();
        String fileStr=sb.toString();
        System.out.println("源文件内容如下"+"\n"+fileStr);
    }

    public ArrayList<String> getWords(){
        ArrayList<String> words=new ArrayList<>();
        //匹配关键字，标识符，常量
        String regex = "[a-zA-Z_]\\w*|([0-9]+)(\\.[0-9]+)?|=+|<=*|>=*|\\++|\\-+|\\*+|\\/+|;|:|,|\\(|\\)|\\[|\\]|\\{|\\}";//匹配关键字，标识符，常量
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(sb.toString());
        while(matcher.find()){
            words.add(matcher.group());
        }
        return words;
    }


    public void Output(String filepath,StringBuffer buffer){
        File outputFile=new File(filepath);
        if(outputFile.exists()) outputFile.delete();
        PrintWriter writer=null;
        try {
            outputFile.createNewFile();
            writer=new PrintWriter(new FileOutputStream(outputFile));
            writer.write(buffer.toString());
            //System.out.println(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(writer!=null)
            writer.close();
    }

}
