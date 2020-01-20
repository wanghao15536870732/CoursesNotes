package util;

import bean.OrderDAO;

import javax.swing.*;
import java.awt.*;
import java.io.*;

//文件工具类
public class FileUtil {

    //表格导出文件
    public static void tableToFile(JFrame main, JTable table) {
        FileDialog fd = new FileDialog(main, "保存成绩到本地", FileDialog.SAVE);
        fd.setIconImage(Toolkit.getDefaultToolkit().getImage("./src/pictures/file.png"));
        fd.setFont(new Font("楷体", Font.BOLD, 18));
        fd.setLocation(800, 500);
        fd.setVisible(true);
        String stringFile = fd.getDirectory() + fd.getFile() + ".xls";
        OrderDAO oDao = new OrderDAO();
        oDao.exportTable(table, new File(stringFile));
    }

    //数据库备份
    public static void dbBackup(JFrame main) {
        FileDialog dialog = new FileDialog(main, "数据库备份", FileDialog.SAVE);
        dialog.setFont(new Font("楷体", Font.BOLD, 20));
        dialog.setLocation(800, 500);
        dialog.setVisible(true);
        String filePath = dialog.getDirectory() + dialog.getFile() + ".sql";
        //测试备份
        String[] execCMD = new String[]{"D:\\mysqldump", "-u" + "root", "-p","student",
                    ">" + filePath};
        boolean result = backup(execCMD, filePath);
        if(result){
            System.out.println("备份成功");
        }else {
            System.out.println("备份失败");
        }
    }

    private static boolean backup(String[] command, String savePath) {
        boolean flag;
        // 获得与当前应用程序关联的Runtime对象
        Runtime runtime = Runtime.getRuntime();
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            // 在单独的进程中执行指定的字符串命令
            Process p = runtime.exec(command);
            // 获得连接到进程正常输出的输入流，该输入流从该Process对象表示的进程的标准输出中获取数据
            InputStream is = p.getInputStream();
            // InputStreamReader是从字节流到字符流的桥梁：它读取字节，并使用指定的charset将其解码为字符
            InputStreamReader isr = new InputStreamReader(is, "utf-8");
            //BufferedReader从字符输入流读取文本，缓冲字符，提供字符，数组和行的高效读取
            br = new BufferedReader(isr);
            String s;
            StringBuffer sb = new StringBuffer("");
            // 组装字符串
            while ((s = br.readLine()) != null) {
                sb.append(s + System.lineSeparator());
            }
            s = sb.toString();
            // 创建文件输出流
            FileOutputStream fos = new FileOutputStream(savePath);
            // OutputStreamWriter是从字符流到字节流的桥梁，它使用指定的charset将写入的字符编码为字节
            OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
            // BufferedWriter将文本写入字符输出流，缓冲字符，以提供单个字符，数组和字符串的高效写入
            bw = new BufferedWriter(osw);
            bw.write(s);
            bw.flush();
            flag = true;
        } catch (IOException e) {
            flag = false;
            e.printStackTrace();
        } finally {
            //由于输入输出流使用的是装饰器模式，所以在关闭流时只需要调用外层装饰类的close()方法即可
            try {
                if (null != bw) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (null != br) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }
}
