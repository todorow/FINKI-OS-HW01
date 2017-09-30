import java.io.File;
import java.io.FilenameFilter;
class Filter implements FilenameFilter{
    private String extension;
    public Filter (String extension){
        this.extension=extension;
    }
    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(extension);

    }
}
public class HW01_1{
    public static double prosek(File [] dir){
       long sum=0;
       long n=dir.length;
        for (File temp:dir){

            sum+=temp.length();

        }
        return sum/n;
    }
    public static void main (String []args){
        String path = new String(String.valueOf(args[0]));
  
    File directory= new File(path);
    FilenameFilter filter = new Filter("txt");
    //System.out.println(directory.getName());
        if(directory.exists()==true){File niza []=directory.listFiles(filter);
            double temp=prosek(niza);
            System.out.println(temp);
        }else{
            System.out.println("nepostoechki direktorium");
        }


    }
}
