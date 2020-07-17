package com.runjing.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;

/**
 * @Created by xiaoyu on 2017/1/6.
 * @Describe：升级
 * @Review by：
 * @Modify by：
 * @Version : $ v_1.0 on 2017/1/6.
 * @Remark:
 */
public class Util {
	public static Bitmap createBitmapThumbnail(Bitmap bitMap) {
		int width = bitMap.getWidth();
		int height = bitMap.getHeight();
		// 设置想要的大小
		int newWidth = 99;
		int newHeight = 99;
		// 计算缩放比例
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// 取得想要缩放的matrix参数
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		// 得到新的图片
		Bitmap newBitMap = Bitmap.createBitmap(bitMap, 0, 0, width, height,
				matrix, true);
		return newBitMap;
	}
	
	
	public static final int INSTALLED = 0; // 应用程序安装状态
	public static final int UNINSTALL = 1;// 应用程序安装状态
	public static final int UPDATE = 2;// 应用程序安装状态

	/**
	 * 检测网络
	 * 
	 * @param context
	 *            Context
	 * @return true 网络可用 false otherwise
	 */
	public static boolean checkNetWork(Context context) {

		ConnectivityManager connM = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = connM.getActiveNetworkInfo();
		if (info != null && info.isConnected()) {
			return true;
		}
		return false;
	}

	/**
	 * 检测sd卡是否存在
	 * 
	 * @return true 存在 false otherwise
	 */
	public static boolean isExistSD() {
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
	}

	/**
	 * 换算文件大小
	 * 
	 * @param fileSize
	 *            文件大小
	 * @return 带参数的大小
	 */
	public static String formatFileSize(long fileSize) {
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileSize < 1024) {
			fileSizeString = fileSize + "B";
		} else if (fileSize < 1048576) {
			fileSizeString = df.format((double) fileSize / 1024) + "K";
		} else if (fileSize < 1073741824) {
			fileSizeString = df.format((double) fileSize / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileSize / 1073741824) + "G";
		}
		return fileSizeString;
	}

	/**
	 * 打开文件进行安装
	 * 
	 * @param f
	 */
	public static void openFile(File f, Activity activity) {
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(Intent.ACTION_VIEW);
		// 获得下载好的文件类型
		String type = getMIMEType(f);
		// 打开各种类型文件
		intent.setDataAndType(Uri.fromFile(f), type);
		// 安装
		activity.startActivity(intent);
	}

	/**
	 * 获得下载文件的类型
	 * 
	 * @param f
	 *            文件名称
	 * @return 文件类型
	 */
	private static String getMIMEType(File f) {
		String type = "";
		// 获得文件名称
		String fName = f.getName();
		// 获得文件扩展名
		String end = fName
				.substring(fName.lastIndexOf(".") + 1, fName.length())
				.toLowerCase();
		if (end.equals("m4a") || end.equals("mp3") || end.equals("mid")
				|| end.equals("xmf") || end.equals("ogg") || end.equals("wav")) {
			type = "audio";
		} else if (end.equals("3gp") || end.equals("mp4")) {
			type = "video";
		} else if (end.equals("jpg") || end.equals("gif") || end.equals("png")
				|| end.equals("jpeg") || end.equals("bmp")) {
			type = "image";
		} else if (end.equals("apk")) {
			type = "application/vnd.android.package-archive";
		} else {
			type = "*";
		}
		if (end.equals("apk")) {
		} else {
			type += "/*";
		}
		return type;
	}

	/**
	 * 
	 * 判断该应用在手机中的安装情况
	 * 
	 * @param pm
	 *            PackageManager
	 *            要判断应用的包名
	 *            要判断应用的版本号
	 * @return INSTALLED：UPDATE(已安装 可升级)：UNINSTALL
	 */

	public static int checkInstalled(PackageManager pm, String strPackageName,
                                     int newVersionCode) {
		List<PackageInfo> pakageinfos = pm
				.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
		for (PackageInfo pi : pakageinfos) {
			String pi_packageName = pi.packageName;
			int pi_versionCode = pi.versionCode;
			// 如果这个包名在系统已经安装过的应用中存在
			if (strPackageName.endsWith(pi_packageName)) {
				// Log.i("test","此应用安装过了");
				if (newVersionCode == pi_versionCode) {
					Log.i("test", "已经安装，不用更新，可以卸载该应用");
					return INSTALLED;
				} else if (newVersionCode > pi_versionCode) {
					return UPDATE;
				}

			}
		}
		Log.i("util", "未安装该应用，可以安装");
		return UNINSTALL;
	}
	
}
