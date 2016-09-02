package com.loopeer.android.librarys.horizontalverticalscrollview.utils;

/**
 * html工具类
 * Created by laiyingtang on 2016/9/1.
 */
public class HtmlUtils {
    /**
     * 为给定的字符串添加HTML红色标记，当使用Html.fromHtml()方式显示到TextView 的时候其将是红色的
     * @param string 给定的字符串
     * @return
     */
    public static String addHtmlRedFlag(String string){
        return "<font color=\"red\">"+string+"</font>";
    }

    /**
     * 将给定的字符串中所有给定的关键字标红
     * @param sourceString 给定的字符串
     * @param keyword 给定的关键字
     * @return 返回的是带Html标签的字符串，在使用时要通过Html.fromHtml()转换为Spanned对象再传递给TextView对象
     */
    public static String keywordMadeRed(String sourceString, String keyword){
        String result = "";
        if(sourceString != null && !"".equals(sourceString.trim())){
            if(keyword != null && !"".equals(keyword.trim())){
                result = sourceString.replaceAll(keyword, "<font color=\"red\">"+keyword+"</font>");
            }else{
                result = sourceString;
            }
        }
        return result;
    }
}
