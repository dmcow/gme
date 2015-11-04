
package com.slm.gme.common;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.PutPolicy;
import com.qiniu.api.rsf.ListItem;
import com.qiniu.api.rsf.ListPrefixRet;
import com.qiniu.api.rsf.RSFClient;

/**
 * 
 * @ClassName: QiniuUtil.java
 * @Description:七牛云储存的帮助类
 * @author: zhangziwen
 * @Date: 2015年10月10日 下午3:04:45
 */
public class QiniuUtil
{
    public static final String IMG_TYPE = ".jpg|.jepg|.gif|.png|.bmp";

    public static final String ALL_TYPE = ".jpg|.jepg|.gif|.png|.bmp|.gz|.rar|.zip|.pdf|.txt|.swf|.wmv";

    //获取七牛的云存储的空间名称
    public static final String QINIU_BUCKET = "zhixinbao";

    //IConfigHelper.getConfig("gme.common.properties", false).getString("qiniu.bucket", "symlich");

    //获取七牛的云存储空间对应的域名
    public static final String QINIU_URL = "http://7xls5d.com2.z0.glb.qiniucdn.com/";
    //IConfigHelper.getConfig("gme.common.properties", false).getString("qiniu.bucketurl",
    //"http://7te96c.com1.z0.glb.clouddn.com/");

    // 配置七牛的key
    static
    {
        Config.ACCESS_KEY = "bYNiEfBSWa9RNWcHVJbasXouQW3HB9f2UQQeTnqf";
        //IConfigHelper.getConfig("gme.common.properties", false).getString("qiniu.accesskey",
        //"PjZGrOtn8Hkj1_t8yM7o0K6aGMlXynI0SjuFUKlg");

        Config.SECRET_KEY = "mDyh4O-hpRL1nUiTKu1ic2jJ2axPBbN_PYNzoHWg";
        //IConfigHelper.getConfig("gme.common.properties", false).getString("qiniu.secretkey",
        //"gFb8yY-Z5odAX_ps4NYnUOo8Kfh0pkAoOCzeyzz5");
    }

    /**
     * 获取文件类型
     * @param @param fileName
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public static String getFileType(String fileName)
    {
        return fileName.substring(fileName.lastIndexOf("."), fileName.length());
    }

    /**
     * 检查文件类型
     * @param @param fileName
     * @param @param isImg
     * @param @return    设定文件
     * @return boolean    返回类型
     * @throws
     */
    public static boolean checkFileType(String fileName , boolean isImg)
    {
        String fileType = getFileType(fileName);
        if (isImg)
        {
            return IMG_TYPE.indexOf(fileType.toLowerCase()) == -1;
        }
        else
        {
            return ALL_TYPE.indexOf(fileType.toLowerCase()) == -1;
        }
    }

    /**
     * 上传文件
     * @param file
     * @param fileName
     * @return
     * @throws JSONException 
     * @throws AuthException 
     */
    private static String upload(String filePath , String newName) throws AuthException, JSONException
    {
        Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
        PutPolicy putPolicy = new PutPolicy(QINIU_BUCKET);
        String uptoken = putPolicy.token(mac);
        PutExtra extra = new PutExtra();
        PutRet ret = IoApi.putFile(uptoken, newName, filePath, extra);
        String key = ret.getKey();
        if (CommonUtil.isEmpty(key))
        {
            throw new AuthException("用户信息获取失败！");
        }
        return key;
    }

    /**
     * 上传文件
     * @param file
     * @param fileName
     * @return
     * @throws JSONException 
     * @throws AuthException 
     */
    public static String upload(InputStream fileInputStream , String newName) throws AuthException, JSONException
    {
        Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
        PutPolicy putPolicy = new PutPolicy(QINIU_BUCKET);
        String uptoken = putPolicy.token(mac);
        PutExtra extra = new PutExtra();
        PutRet ret = IoApi.Put(uptoken, newName, fileInputStream, extra);
        //PutRet ret = IoApi.putFile(uptoken, newName, filePath, extra);
        String key = ret.getKey();
        if (CommonUtil.isEmpty(key))
        {
            throw new AuthException("用户信息获取失败！");
        }
        return key;
    }

    /**
     * 上传文件，为管理图片而抽出来了
     * @param filePath
     * @param fileName
     * @return
     * @throws AuthException
     * @throws JSONException
     */
    public static String uploadHttpFile(String filePath , String fileName) throws AuthException, JSONException
    {
        String fileType = getFileType(fileName);
        String newName = System.currentTimeMillis() + fileType;
        return QINIU_URL + upload(filePath, newName);
    }

    /**
     * 上传文件，为管理图片而抽出来了
     * @param filePath
     * @param fileName
     * @return
     * @throws AuthException
     * @throws JSONException
     */
    public static String uploadHttpFile(InputStream fileInputStream , String fileName) throws AuthException,
            JSONException
    {
        String fileType = getFileType(fileName);
        String newName = System.currentTimeMillis() + fileType;
        return QINIU_URL + upload(fileInputStream, newName);
    }

    /**
     * 上传文件，为管理图片而抽出来了
     * @param filePath
     * @param fileName
     * @return
     * @throws AuthException
     * @throws JSONException
     */
    public static String uploadFile(String filePath , String fileName) throws AuthException, JSONException
    {
        String fileType = getFileType(fileName);
        String newName = System.currentTimeMillis() + fileType;
        return upload(filePath, newName);
    }

    /**
     * 列出所有文件
     * @param count
     * @return
     */
    public static Map<String, Object> listObject(int count)
    {
        Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
        RSFClient client = new RSFClient(mac);
        ListPrefixRet list = client.listPrifix(QINIU_BUCKET, "", "", count);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("moveup_dir_path", "");
        result.put("current_dir_path", "");
        result.put("current_url", QINIU_URL);
        result.put("total_count", list.results.size());
        List<Map<String, Object>> fileList = new ArrayList<Map<String, Object>>();
        for (ListItem item : list.results)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("is_dir", false);
            map.put("has_file", false);
            map.put("filesize", item.fsize);
            map.put("dir_path", "");
            String fileName = item.key;
            String fileType = getFileType(fileName);
            map.put("is_photo", !checkFileType(fileName, true));
            map.put("filetype", fileType.substring(1, fileType.length()));
            map.put("filename", fileName);

            map.put("datetime", CommonUtil.formatDate(new Date(item.putTime), "yyyy-MM-dd HH:mm"));
            fileList.add(map);
        }
        result.put("file_list", fileList);
        return result;
    }

    public static void main(String[] ss)
    {
        try
        {
            System.out.println(uploadFile("F:/otherpic/ot/7845c42ed0701407dee34a.jpg", "psbCAXH8EO5.jpg"));
        }
        catch (AuthException e)
        {
            e.printStackTrace();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
