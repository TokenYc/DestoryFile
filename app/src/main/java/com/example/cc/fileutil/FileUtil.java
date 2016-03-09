package com.example.cc.fileutil;

import android.os.Environment;
import android.util.Xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件工具类
 * Created by .cc on 2016/3/8.
 */
public class FileUtil {

    public static final String APP_NAME = "tencent";
    public static final String SDCARD_PATH = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
    // 客户端文件夹路径
    public static final String APP_FOLDER = SDCARD_PATH + File.separator + APP_NAME;


    public static String getSDPath(){
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED);   //判断sd卡是否存在
        if   (sdCardExist)
        {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        }
        return sdDir.toString();

    }
    /**
     * 判断某个文件夹或文件是否存在
     * @param path
     * @return
     */
    public static boolean isFileExist(String path){
        File file=new File(path);
        if (file.exists()){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 创建目录
     * @param path
     * @return
     */
    public static boolean makeDir(String path) {
        File file = new File(path);
        if (!file.exists()){
            if (file.mkdirs()){
                return true;
            }else{
                return false;
            }
        }else{
            return true;
        }
    }

    /**
     * 将文本写到sd卡中
     * @param fileName
     * @param write_str
     */
    public static void writeTextToSdcard(String fileName,String write_str){
        try {
            FileOutputStream fout=new FileOutputStream(fileName);
            byte[] bytes=write_str.getBytes();
            fout.write(bytes);
            fout.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除单个文件
     * @param   filePath    被删除文件的文件名
     * @return 文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            return file.delete();
        }
        return false;
    }

    /**
     * 删除文件夹以及目录下的文件
     * @param   filePath 被删除目录的文件路径
     * @return  目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String filePath) {
        boolean flag = false;
        //如果filePath不以文件分隔符结尾，自动添加文件分隔符
        if (!filePath.endsWith(File.separator)) {
            filePath = filePath + File.separator;
        }
        File dirFile = new File(filePath);
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        flag = true;

        File[] files = dirFile.listFiles();
        if (files==null){
            return false;
        }
        //遍历删除文件夹下的所有文件(包括子目录)
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                //删除子文件
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } else {
                //删除子目录
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前空目录
        return dirFile.delete();
    }

    /**
     *  根据路径删除指定的目录或文件，无论存在与否
     *@param filePath  要删除的目录或文件
     *@return 删除成功返回 true，否则返回 false。
     */
    public static boolean deleteFolder(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return false;
        } else {
            if (file.isFile()) {
                // 为文件时调用删除文件方法
                return deleteFile(filePath);
            } else {
                // 为目录时调用删除目录方法
                return deleteDirectory(filePath);
            }
        }
    }



}
