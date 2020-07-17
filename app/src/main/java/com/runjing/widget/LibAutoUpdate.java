package com.runjing.widget;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.URLUtil;
import android.widget.Toast;


import com.runjing.common.Appconfig;
import com.runjing.common.Util;

import org.runjing.rjframe.ui.ViewInject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 版本升级工具类
 * 
 * @author zm
 * 
 */
public class LibAutoUpdate {

	// 当前版本号
	private int versionCode;
	// 调用升级的activity
	private Activity activity;
	// 当前版本名称
	private String versionName;
	private static final String TAG = "AutoUpdate";
	// 获得文件扩展名字符串
	private String fileEx = "";
	// 获得文件名字符串
	private String fileNa = "";
	// 应用的包名
	private String strPackageName;

	// 新版本下载地址
	private String downloadUrl;
	// 最新版本号
	private int newVersionCode;
	// 新版本描述
	private String newVersionDescribe;
	// 新版本名称
	private String newVersionName;
	// 安装包文件临时路径
	private String currentTempFilePath = "";

	private DefaultDialog progressDialog;// 进度条dialog

	// 是否可停止下载
	private boolean canStop = false;
	private String isForce;
	// 是否启动安装器
	private boolean isInstallStart = false;
	// 中止下载
	private boolean isStopDownload = false;

	private String dialogToastTxt = "下载失败";// 下载失败dialog提示信息

	/**
	 * 类的构造方法
	 * @param canStop
	 *            是否可以中止下载
	 */
	public LibAutoUpdate(Activity homePageActivity, String loadUrl,
                         int tipIndex, String newVersion, String des, String force, String strPackageName, boolean canStop) {
		super();
		this.activity = homePageActivity;
		this.downloadUrl = loadUrl;
		this.newVersionDescribe = des;
		this.newVersionName = newVersion;
		this.strPackageName = strPackageName;
		this.canStop = canStop;
		this.isForce = force;
		getCurrentVersion(strPackageName);

	}

	/**
	 * 检查 更新
	 *            是否强制升级
	 */
	public void checkUp() {
		if (!Util.checkNetWork(activity)) {
			Toast.makeText(activity, "请检查网络", Toast.LENGTH_SHORT).show();
		} else {
			if (newVersionCode > versionCode) {
				DefaultDialog dialog = new DefaultDialog(activity);
				dialog.setTitleValue("新版本");
				dialog.showTextDialog(newVersionName, newVersionDescribe);

				dialog.setRightButtonListener(new DefaultDialog.OnRightListener() {

					@Override
					public void onRightListener() {
						if (Util.isExistSD()) {
							new UpgradeDownloadTask().execute(downloadUrl);
						}
					}
				});
				
				dialog.show();
				dialog.setCanceledOnTouchOutside(false);
			}
		}
	}

	/**
	 * 检查 更新以versionName判断
	 */
	public void checkUpByVersionName() {
		if (!Util.checkNetWork(activity)) {
			ViewInject.toast("请检查网络");
		} else {
			if (!newVersionName.equals(versionName)) {
				DefaultDialog dialog = new DefaultDialog(activity);
				dialog.setTitleValue("新版本");
				String des = "";
				if(newVersionDescribe.contains("\\n")){
					des = newVersionDescribe.replace("\\n", "\n");
				}else{
					des = newVersionDescribe;
				}
				if(!isForce.equals("F")){
					dialog.btnAlone();
				}
				dialog.showTextDialog(newVersionName, des);
				dialog.setRightButtonListener(new DefaultDialog.OnRightListener() {

					@Override
					public void onRightListener() {
						if (Util.isExistSD()) {
							new UpgradeDownloadTask().execute(downloadUrl);

						}else{
							ViewInject.toast("请检查SD卡是否安装");
						}
					}
				});
				dialog.setLeftButtonLintener(new DefaultDialog.OnLeftListener() {

					@Override
					public void onLeftListener() {
						
					}
				});
			

				dialog.show();
				   dialog.setCanceledOnTouchOutside(false);
			}
		}
	}

	/**
	 * 获取当前的版本信息
	 * 
	 * @param strPackageName
	 *            应用的包名 
	 */
	public void getCurrentVersion(String strPackageName) {
		try {
			PackageInfo info = activity.getPackageManager().getPackageInfo(
					strPackageName, 0);
			this.versionCode = info.versionCode;
			this.versionName = info.versionName;
			Log.d(TAG, versionCode + "versionCode");
			Log.d(TAG, versionName + "versionName");
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 检查是否成功安装升级版本，成功则删除安装包 【需在activity的onResume()】
	 * 
	 * @return true installed and file delected false uninstall
	 */
	public boolean checkFinishInstallFileDel() {
		if (isInstallStart) {
			if (Util.checkInstalled(activity.getPackageManager(),
					strPackageName, newVersionCode) == Util.INSTALLED) {
				delFile();
				return true;
			}
		}
		return false;
	}

	/**
	 * 弹出 progressbar dialog
	 */
	public void showProgressDialog() {
		progressDialog = new DefaultDialog(activity);
		progressDialog.setTitleValue("正在下载安装包,请等待......");
		if(isForce.equals("F")){
			progressDialog.setLeftBtnValue("取消下载");
			progressDialog.setLeftButtonLintener(new DefaultDialog.OnLeftListener() {

				@Override
				public void onLeftListener() {
					System.out.println("dialog");
					delFile();
					isStopDownload = true;

				}
			});
			progressDialog.setRightBtnValue("后台下载");
		} else {
			progressDialog.btnAlone();
			progressDialog.rightbtnAlone();
		}
		progressDialog.setCanceledOnTouchOutside(false);	
		progressDialog.show();
		Appconfig.isDownLoad = "T";
	}

	/**
	 * 删除临时路径里的安装包
	 */
	public void delFile() {
		Log.i(TAG, "The TempFile(" + currentTempFilePath + ") was deleted.");
		File myFile = new File(currentTempFilePath);
		if (myFile.exists()) {
			myFile.delete();
		}
	}

	/**
	 * 下载文件
	 * 
	 * @author zm
	 * 
	 */
	private class UpgradeDownloadTask extends AsyncTask<String, String, File> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// 获得文件扩展名字符串
			fileEx = downloadUrl.substring(downloadUrl.lastIndexOf(".") + 1,
					downloadUrl.length()).toLowerCase();
			// 获得文件文件名字符串
			fileNa = downloadUrl.substring(downloadUrl.lastIndexOf("/") + 1,
					downloadUrl.lastIndexOf("."));
			showProgressDialog();
		}

		@Override
		protected void onPostExecute(File result) {
			if (result != null) {
				Util.openFile(result, activity);
				progressDialog.dismiss();
				isInstallStart = true;
			} else {
				progressDialog.setTitleValue("提示：");
				progressDialog.changeTextDialog(dialogToastTxt);
				progressDialog.btnAlone();
				progressDialog.setRightBtnValue("关闭");
				progressDialog.OnlyButton();
				delFile();
			}
			super.onPostExecute(result);
		}

		@Override
		protected void onProgressUpdate(String... values) {
			super.onProgressUpdate(values);
			if (values.length > 0) {
				progressDialog.setProgressBarValue(Integer.valueOf(values[0]));
				progressDialog.setProgressTvValue(values[1]);
				progressDialog.setFileSizeDownload(values[2]);
			}
		}

		@Override
		protected File doInBackground(String... params) {
			String strPath = params[0];
			Log.i(TAG, "getDataSource()");
			File myTempFile = null;
			// 判断strPath是否为网络地址
			if (!URLUtil.isNetworkUrl(strPath)) {
				Log.i(TAG, "服务器地址错误！");
			} else {
				URL myURL;
				try {
					myURL = new URL(strPath);
				} catch (MalformedURLException e) {
					e.printStackTrace();
					return null;
				}
				URLConnection conn;
				InputStream is = null;
				try {
					conn = myURL.openConnection();
					conn.connect();
					is = conn.getInputStream();
					if (is == null) {
						return null;
					}
					long fileLength = conn.getContentLength();
					if (fileLength < 1) {
						return null;
					}

					File sdFile = new File("sdcard");
					if (!sdFile.exists()) {
						// sd卡不存在
						dialogToastTxt = "请检查SD卡";
						return null;
					}

					myTempFile = new File("sdcard/" + fileNa + "." + fileEx);
					if (!myTempFile.getParentFile().exists()) {
						myTempFile.getParentFile().mkdirs();
						Log.d("test", myTempFile.getParentFile()
								.getAbsolutePath());
					}
					if (!myTempFile.exists()) {
						boolean isCreated = myTempFile.createNewFile();
						if (!isCreated) {
							dialogToastTxt = "请检查SD卡";
							return null;
						}
					}
					// 安装包文件临时路径
					currentTempFilePath = myTempFile.getAbsolutePath();
					System.out.println(currentTempFilePath + "===============");
					FileOutputStream fos = new FileOutputStream(myTempFile);
					byte buf[] = new byte[1024];
					int readLen = 0, loopCount = 0;
					long downloadSize = 0;
					while ((readLen = is.read(buf)) != -1) {
						if (isStopDownload) {
							return null;
						}
						loopCount++;
						downloadSize += readLen;
						fos.write(buf, 0, readLen);
						if (loopCount == 1 || loopCount % 100 == 0) {
							publishProgress(new String[] {
									(downloadSize * 100 / fileLength) + "",
									(downloadSize * 100 / fileLength) + "%",
									Util.formatFileSize(downloadSize) + "/"
											+ Util.formatFileSize(fileLength) });
						}
					}
					publishProgress("100", "100",
							Util.formatFileSize(fileLength));
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				} finally {
					try {
						if (is != null) {
							is.close();
						}
					} catch (Exception ex) {
					}
				}
				
			}
			return myTempFile;
		}

	}
	
}
