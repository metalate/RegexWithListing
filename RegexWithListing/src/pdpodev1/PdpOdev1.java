/**
 * @author Enes Uğur enes.ugur@ogr.sakarya.edu.tr
 * @author Mertcan İlidi mertcan.ilidi@ogr.sakarya.edu.tr
 * 
 *	<p> 
 *	dosyadan okuyarak içerisinde bulunan text'in kelime, cümle, sesli harf,
 *      email ve web sitesi sayılarını listeleyen programdır.
 * 
 *      it is the program that lists the words, sentences, vowels, email,
 *      and website numbers of text that is included in the file.
 *	</p> 
 */
package pdpodev1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PdpOdev1 {
    
    //--------------------------------------------------------------------------
    // DOSYA OKUMA KISMI / FILE READING PART
    //--------------------------------------------------------------------------
    
    /**
     * Bu fonksiyonda kaynak dosyasından verilerileri BufferedReader sınıfı 
     * yardımıyla okuyarak lines string değişkenine aktarılarak geri döndürülür.
     * 
     * This function returns data from the source file to the lines string
     * variable by reading it using the BufferedReader class.
     */
    public static String readFile() {
        String filename = "icerik.txt";
        
        BufferedReader reader = null;
        String lines = null;
        String line = null;
        File file = null; 
        try {
            file = new File(filename);
            reader = new BufferedReader(new FileReader(file));
            line = reader.readLine();
            lines = line;
            while (line != null) {
                line = reader.readLine();
                if(line == null) break;
                lines += (line + "\n");
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage() + " - "+file.getAbsolutePath());
        }
        return lines;
    }
    
    //--------------------------------------------------------------------------
    // TANIMLAMA VE LISTELEME KISMI / IDENTIFICATION AND LISTING SECTION
    //--------------------------------------------------------------------------
    
    /**
     * Bu fonksiyonda kaynak dosyasından alınan verilerilerin regex yardımıyla
     * istenilen tanımların yapılmasını ve bunların miktarlarının ekrana 
     * aktarılmasını sağlar. Veri geri döndürmez.
     * 
     * This function allows data from the source file to be made using regex
     * to make the required definitions and to transfer the quantities of them
     * to the screen. Data does not return.
     */
    public static void listing(String filename){
        int mailCounter = 0;
        int webCounter = 0;
        int cumleCounter = 0;
        int sesliCounter = 0;
        
        String EMAIL_PATTERN = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";
        String WEB_PATTERN = "\\b(https?://)?(www\\.)?[A-Za-z0-9]+\\.(com|org|edu|net)(\\.tr)?\\b";
        String CUMLE_PATTERN = "[A-Za-z0-9._%+-][\\.|\\?|\\:|\\!|(\\.\\.\\.)](\\u0020)";
        String CUMLE_PATTERN_2 = "[A-Za-z0-9._%+-][\\.|\\?|\\:|\\!|(\\.\\.\\.)](\\z)";
        String SESLI_PATTERN = "[aeıioöuüAEIİOÖUÜ]";
        
        //----------------------------------------------------------------------
        // EMAIL SAYISI HESAPLAMA KISMI / NUMBER OF EMAILS CALCULATION PART
        //----------------------------------------------------------------------
        
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(filename);
        
        while(matcher.find())
            mailCounter++;
        
        //----------------------------------------------------------------------
        // WEB ADRESI SAYISI HESAPLAMA KISMI / NUMBER OF WEB ADDRESSES CALCULATION PART
        //----------------------------------------------------------------------
        
        pattern = Pattern.compile(WEB_PATTERN);
        matcher = pattern.matcher(filename);
        
        while(matcher.find())
            webCounter++;
        
        //----------------------------------------------------------------------
        // CUMLE SAYISI HESAPLAMA KISMI / NUMBER OF SENTENCES CALCULATION PART
        //----------------------------------------------------------------------
        
        pattern = Pattern.compile(CUMLE_PATTERN);
        matcher = pattern.matcher(filename);
        
        while(matcher.find())
            cumleCounter++;
        
        pattern = Pattern.compile(CUMLE_PATTERN_2);
        matcher = pattern.matcher(filename);
        
        while(matcher.find())
            cumleCounter++;
        
        //----------------------------------------------------------------------
        // KELIME SAYISI HESAPLAMA KISMI / NUMBER OF WORDS CALCULATION PART
        //----------------------------------------------------------------------
        
        String[] words = filename.split(" ");
        int kelimeSayisi = words.length;
        
        //----------------------------------------------------------------------
        // SESLI HARF SAYISI HESAPLAMA KISMI / NUMBER OF VOWELS CALCULATION PART
        //----------------------------------------------------------------------
        
        pattern = Pattern.compile(SESLI_PATTERN);
        matcher = pattern.matcher(filename);
        
        while(matcher.find())
            sesliCounter++;
        
        //----------------------------------------------------------------------
        // SONUCLARI EKRANA YAZDIRMA KISMI / PRINT RESULTS ON SCREEN SECTION
        //----------------------------------------------------------------------
        
        System.out.println("Toplam Sesli Harf Sayisi : " + sesliCounter);
        System.out.println("Toplam Kelime Sayisi     : " + kelimeSayisi);
        System.out.println("Toplam Cumle Sayisi      : " + cumleCounter);
        System.out.println("Toplam Mail Sayisi       : " + mailCounter);
        System.out.println("Toplam Web Sitesi Sayisi : " + (webCounter - mailCounter));
    }
    
    public static void main(String[] args) {
        
        listing(readFile());
        
    }
    
}
