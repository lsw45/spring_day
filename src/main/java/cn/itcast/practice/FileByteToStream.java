package cn.itcast.practice;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.platform.commons.util.StringUtils;

/**
 * InputStreamReader和BufferedReader是Reader类的子类，FileReader类是InputStreamReader的子类
 * FileInputStream是InputStream类的子类
 *  FileInputStream：以字节流方式读取；FileReader：把文件转换为字符流读入；
 *  InputStream提供的是字节流的读取，而非文本读取，这是和Reader类的根本区别。用Reader读取出来的是char数组或者String ，使用InputStream读取出来的是byte数组。
 *  Reader类及其子类提供的字符流的读取char（16位,unicode编码），InputStream及其子类提供字节流的读取byte（8位）
 *  所以FileReader类是将文件按字符流的方式读取，FileInputStream则按字节流的方式读取文件；而InputStreamReader可以将读如stream转换成字符流方式，是reader和stream之间的桥梁
 *  最初Java是不支持对文本文件的处理的，为了弥补这个缺憾而引入了Reader和Writer两个类。
 *  FileInputStream类以二进制输入/输出，I/O速度快且效率搞，但是它的read（）方法读到的是一个字节（二进制数据），很不利于人们阅读。
 *  而FileReader类弥补了这个缺陷，可以以文本格式输入/输出，非常方便；比如可以使用while((ch = filereader.read())!=-1 )循环来读取文件；也可以使用BufferedReader的readLine()方法一行一行的读取文本。
 *  当我们读写文本文件的时候，采用Reader是非常方便的，比如FileReader， InputStreamReader和BufferedReader。其中最重要的类是InputStreamReader，它是字节转换为字符的桥梁。 你可以在构造器重指定编码的方式，如果不指定的话将采用底层操作系统的默认编码方式，例如GBK等。
 *  FileReader与InputStreamReader涉及编码转换(指定编码方式或者采用os默认编码)，可能在不同的平台上出现乱码现象！而FileInputStream以二进制方式处理，不会出现乱码现象.
 */

// 字节流的每一行数据以换行符分割

/**
 * 字符串List，读取出文件流
*  List<String> codeList;
*  StringBuffer buffer = new StringBuffer();
*  codeList.forEach(e -> buffer.append(e).append("\n"));
*  byte[] codeBytes = buffer.toString().getBytes();
*  System.out.println(codeBytes);
 */
public class FileByteToStream {

    public static void main(String[] args) throws IOException {
        String filePath = "C:/Users/Administrator/Desktop/天猫活动商家自由券.txt";

        File file = new File(filePath);
        FileInputStream in1=new FileInputStream(file);
        in1.close();

        File file2 = new File ("hello.txt");
        FileInputStream in2=new FileInputStream(file2);
        InputStreamReader inReader=new InputStreamReader(in2,"GBK");
        BufferedReader bufReader2=new BufferedReader(inReader);
        in2.close();

        File file3 = new File ("hello.txt");
        FileReader fileReader=new FileReader(file3);
        BufferedReader bufReader3=new BufferedReader(fileReader);
    }

    // 读取excel除第一列（第一行除外）
    private static List<String> getCodeList(InputStream in) {
        List<String> numberList = new LinkedList<>();
        /*
         * 使用xlsx-streamer而不适用poi,因为poi读取大量数据的excel会导致内存溢出的问题,
         * 且xlsx-streamer读取速度更快
         * 缓存到内存中的行数100，默认是10
         * 读取资源时，缓存到内存的字节大小4096，默认是1024
         * 打开资源，必须，可以是InputStream或者是File，注意：只能打开XLSX格式的文件
         */
        Workbook wk = StreamingReader.builder().rowCacheSize(100).bufferSize(4096).open(in);
        Sheet sheet = wk.getSheetAt(0);
        int index = 0;
        for (Row row : sheet) {
            if (index++ == 0) {
                continue;
            }
            Cell cell = row.getCell(0);
            if (cell == null) {
                continue;
            }
            String number = cell.getStringCellValue();
            if (StringUtils.isBlank(number)) {
                continue;
            }
            numberList.add(number.trim());
        }
        return numberList;

    }
}
