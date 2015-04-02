package cn.sccl.ilife.android.core.httpclient;

public interface ILifeConstants {
	String CHARSET_UTF8 = "UTF-8"; 
	
	String SAMPLE_CODE_SERVER = "http://182.140.242.17:8001/ydyw/";
	String SAMPLE_CODE_ACCOUNT = "account.ashx";
	String SAMPLE_CODE_LOGIN = SAMPLE_CODE_SERVER + SAMPLE_CODE_ACCOUNT;

    String SAMPLE_CODE_DOUBAN_SERVER = "https://api.douban.com/v2/";
    String SAMPLE_CODE_DOUBAN_BOOK_SEARCH = "book/search";
    String SAMPLE_CODE_DOUBAN_BOOK= SAMPLE_CODE_DOUBAN_SERVER + SAMPLE_CODE_DOUBAN_BOOK_SEARCH;

    /** 被调用app的action*/
    String ILIFE_ACTION_MAIN = "android.intent.action.BIGBANGMAIN";
    String SD_IMAGE_CACHE_DIRECTORY = "/Ilife/ImageCache";
}
