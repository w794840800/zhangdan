package com.beidou.ybz.accountbook.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author Supreme- <activity_main_drawer_idtext href="977594142@qq.com">Supreme-</activity_main_drawer_idtext>
 * @since 2017/6/20
 */

public class SimpleUtils {

    /**
     * 将 Bitmap 保存到SD卡
     *
     * @param context
     * @param mybitmap
     * @param name
     * @return
     */
    private static String test = "default";

    public static String saveBitmapToSdCard(Context context, Bitmap mybitmap, String name) {
        String path;

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            path = Environment.getExternalStorageDirectory().getPath() + "/images/";
        } else {
            path = Environment.getRootDirectory().getPath() + "/images";

        }
        File sd = new File(path);
        if (!sd.exists()) {
            sd.mkdirs();
        }
        File file = new File(path + name + ".jpg");
        FileOutputStream fileOutputStream = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(file);
            mybitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            test = "jacks";
            //update gallery
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Uri uri = Uri.fromFile(file);
            intent.setData(uri);
            context.sendBroadcast(intent);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show();
        return test;
    }

    public static String saveBitmapToSdCard1(Context context, Bitmap mybitmap, String name) {
        String path;

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            path = Environment.getExternalStorageDirectory().getPath() + "/images/";
        } else {
            path = Environment.getRootDirectory().getPath() + "/images";

        }
        File sd = new File(path);
        if (!sd.exists()) {
            sd.mkdirs();
        }
        File file = new File(path + name + ".jpg");
        FileOutputStream fileOutputStream = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(file);
            mybitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            test = "jacks";
            //update gallery
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Uri uri = Uri.fromFile(file);
            intent.setData(uri);
            context.sendBroadcast(intent);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show();
        return test;
    }


    /**
     * 手动测量摆放View
     * 对于手动 inflate 或者其他方式代码生成加载的View进行测量，避免该View无尺寸
     *
     * @param v
     * @param width
     * @param height
     */
    public static void layoutView(View v, int width, int height) {
        // validate view.width and view.height
        v.layout(0, 0, width, height);
        int measuredWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        int measuredHeight = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);

        // validate view.measurewidth and view.measureheight
        v.measure(measuredWidth, measuredHeight);
        v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
    }


    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取一个 View 的缓存视图
     * (前提是这个View已经渲染完成显示在页面上)
     *
     * @param view
     * @return
     */
    public static Bitmap getCacheBitmapFromView(View view) {
        final boolean drawingCacheEnabled = true;
        view.setDrawingCacheEnabled(drawingCacheEnabled);
        view.buildDrawingCache(drawingCacheEnabled);
        final Bitmap drawingCache = view.getDrawingCache();
        Bitmap bitmap;
        if (drawingCache != null) {
            bitmap = Bitmap.createBitmap(drawingCache);
            view.setDrawingCacheEnabled(false);
            LogUtils.loge("-----保存成功------");
        } else {
            bitmap = null;
            LogUtils.loge("-----保存失败------");
        }
        return bitmap;
    }

    public static Bitmap getViewBitmap(View v) {
        v.clearFocus();
        v.setPressed(false);

        boolean willNotCache = v.willNotCacheDrawing();
        v.setWillNotCacheDrawing(false);

        // Reset the drawing cache background color to fully transparent
        // for the duration of this operation
        int color = v.getDrawingCacheBackgroundColor();
        v.setDrawingCacheBackgroundColor(0);

        if (color != 0) {
            v.destroyDrawingCache();
        }
        v.buildDrawingCache();
        Bitmap cacheBitmap = v.getDrawingCache();
        if (cacheBitmap == null) {
            return null;
        }

        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);

        // Restore the view
        v.destroyDrawingCache();
        v.setWillNotCacheDrawing(willNotCache);
        v.setDrawingCacheBackgroundColor(color);

        return bitmap;
    }


    /**
     * 判断SDCard是否可用
     *
     * @return
     */
    public static boolean isSDCardEnable() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取SD卡路径
     *
     * @return
     */
    public static String getSDCardPath() {
        if (isSDCardEnable())
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        else
            return getRootDirectoryPath();
    }

    /**
     * 获取系统存储路径
     *
     * @return
     */
    public static String getRootDirectoryPath() {
        return Environment.getRootDirectory().getAbsolutePath();
    }
//    private static final String FILE_PROVIDER_AUTHORITY = UIUtils.getPackageName() + ".fileprovider";
//    /**
//     * 将 Bitmap 保存到SD卡
//     * @param context
//     * @param mybitmap
//     * @param name
//     * @return
//     */
//    public static boolean saveBitmapToSdCard(Context context, Bitmap mybitmap, String name){
//        boolean result = false;
//        //创建位图保存目录
//        String path = Environment.getExternalStorageDirectory() + "/1000ttt/";
//        File sd = new File(path);
//        if (!sd.exists()){
//            sd.mkdirs();
//        }
//        File file = new File(path+name+".jpg");
//        FileOutputStream fileOutputStream = null;
//        if (!file.exists()){
//            try {
//                // 判断SD卡是否存在，并且是否具有读写权限
//                if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
//                    fileOutputStream = new FileOutputStream(file);
//                    mybitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
//                    fileOutputStream.flush();
//                    fileOutputStream.close();
//
//                    //update gallery
//                    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
////                    Uri uri = Uri.fromFile(file);
//                    Uri uri = FileProvider7.getUriForFile(context, file);
//                    intent.setData(uri);
//                    context.sendBroadcast(intent);
//                    Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show();
//                    result = true;
//                }
//                else{
//                    Toast.makeText(context, "不能读取到SD卡", Toast.LENGTH_SHORT).show();
//                }
//
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return result;
//    }
//
//    public static boolean saveBitmapToSdCard1(Context context, Bitmap mybitmap, String name){
//        boolean result = false;
//        //创建位图保存目录
//        String path = Environment.getExternalStorageDirectory() + "/1000ttt/";
//        File sd = new File(path);
//        if (!sd.exists()){
//            sd.mkdir();
//        }
//        File file = new File(path+name+".jpg");
//        FileOutputStream fileOutputStream = null;
//        if (!file.exists()){
//            try {
//                // 判断SD卡是否存在，并且是否具有读写权限
//                if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
//                    fileOutputStream = new FileOutputStream(file);
//                    mybitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
//                    fileOutputStream.flush();
//                    fileOutputStream.close();
//
//                    //update gallery
//                    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//                    Uri uri = Uri.fromFile(file);
//                    intent.setData(uri);
//                    context.sendBroadcast(intent);
//                    result = true;
//                }
//                else{
//                    Toast.makeText(context, "不能读取到SD卡", Toast.LENGTH_SHORT).show();
//                }
//
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return result;
//    }
//
//
//    /**
//     * 手动测量摆放View
//     * 对于手动 inflate 或者其他方式代码生成加载的View进行测量，避免该View无尺寸
//     * @param v
//     * @param width
//     * @param height
//     */
//    public static void layoutView(View v, int width, int height) {
//        // validate view.width and view.height
//        v.layout(0, 0, width, height);
//        int measuredWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
//        int measuredHeight = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);
//
//        // validate view.measurewidth and view.measureheight
//        v.measure(measuredWidth, measuredHeight);
//        v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
//    }
//
//
//    public static int px2dip(Context context, float pxValue) {
//        final float scale = context.getResources().getDisplayMetrics().density;
//        return (int) (pxValue / scale + 0.5f);
//    }
//
//    /**
//     * 获取一个 View 的缓存视图
//     *  (前提是这个View已经渲染完成显示在页面上)
//     * @param view
//     * @return
//     */
//    public static Bitmap getCacheBitmapFromView(View view) {
//        final boolean drawingCacheEnabled = true;
//        view.setDrawingCacheEnabled(drawingCacheEnabled);
//        view.buildDrawingCache(drawingCacheEnabled);
//        final Bitmap drawingCache = view.getDrawingCache();
//        Bitmap bitmap;
//        if (drawingCache != null) {
//            bitmap = Bitmap.createBitmap(drawingCache);
//            view.setDrawingCacheEnabled(false);
//        } else {
//            bitmap = null;
//        }
//        return bitmap;
//    }
//
//
//
//    /**
//     * 判断SDCard是否可用
//     * @return
//     */
//    public static boolean isSDCardEnable() {
//        return Environment.getExternalStorageState().equals(
//                Environment.MEDIA_MOUNTED);
//    }
//
//    /**
//     * 获取SD卡路径
//     * @return
//     */
//    public static String getSDCardPath() {
//        if (isSDCardEnable())
//            return Environment.getExternalStorageDirectory().getAbsolutePath();
//        else
//            return getRootDirectoryPath();
//    }
//
//    /**
//     * 获取系统存储路径
//     * @return
//     */
//    public static String getRootDirectoryPath() {
//        return Environment.getRootDirectory().getAbsolutePath();
//    }
}
