package com.smtlink.transferprotocoldemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    /**
     * 搜索本地文件
     */
    public static List<FileInfo> searchFile(Context context) {
        //DIAL_FILE_PATH == Android/data/com.android.application/test_dial/
        String DIAL_FILE_PATH = context.getExternalFilesDir(null).getAbsolutePath() + "/test_dial/";
        Log.d("gy", "searchFile path : " + DIAL_FILE_PATH);
        List<FileInfo> fileList = new ArrayList<>();
        try {
            File file = new File(DIAL_FILE_PATH);// SD卡中获取
            if (file.exists()) {
                Log.d("gy", "searchFile 2-1 : ");
                File[] listFiles = file.listFiles();
                for (File f : listFiles) {
                    if (f.getName().endsWith(".data")) {
                        FileInfo fileInfo = new FileInfo();
                        fileInfo.setFilePath(f.getAbsolutePath());
                        fileInfo.setFileName(f.getName());
                        fileList.add(fileInfo);
                    }
                }
            } else {
                file.mkdir();
                Log.i("gy", "searchFile file.exists == false");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("gy", "searchFile fileList.size: " + fileList.size());
        return fileList;
    }

    /**
     * 将图片,文件路径，转化为字节数组
     * @param path 图片,文件路径
     * @return
     */
    public static byte[] getByteFromPath(String path) {
        byte[] data = null;
        byte[] buffer = new byte[1024];
        int len = -1;
        try {
            // 读取图片字节数组
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            //InputStream inStream = new FileInputStream(outputFile + path);
            InputStream inStream = new FileInputStream(path);
            while ((len = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            data = outStream.toByteArray();
            outStream.close();
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 获取Assets中文件内容
     * @param inputStream
     * @return String
     */
    public static String getAssetsFile(InputStream inputStream) {
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            String str = "", s = "";
            int c = 0;
            byte[] buf = new byte[64];
            while (true) {
                try {
                    c = bis.read(buf);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (c == -1)
                    break;
                else {
                    try {
                        s = new String(buf, 0, c, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    str += s;
                }
            }
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

    /**
     * 获取Assets中文件
     * @param context
     * @param path
     * @return byte[]
     */
    public static byte[] byteArraysFromAssets(Context context, String path) {
        byte[] buffer = null;
        try {
            InputStream inputStream = null;
            inputStream = context.getAssets().open(path);
            int length = inputStream.available();
            buffer = new byte[length];
            inputStream.read(buffer);
        } catch (Exception e) {
            Log.e("gy", "byteArraysFromAssets Exception: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
        return buffer;
    }

    /**
     * 字节数组换为Bitmap
     *
     * @param bytes
     * @return Bitmap
     */
    public static Bitmap getBitmapFromBytes(byte[] bytes) {
        if (bytes != null) {
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        }
        return null;
    }

}
