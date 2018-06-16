package app.doing.com.doing.utils.GlobalVariable;

/**
 * Created by cherry on 18-6-15.
 * 存放全局变量和常量
 */

public interface GlobalVariable {
    //服务器url前缀
    public final static String BASE_URL = "http://47.94.0.163:8080/fitness/";

    public final static String GYM_URL = "GetAllGYMServlet";

    public final static String SELECTED_GYM_URL = "GetGYMRecommendServlet";

    /*
        获取图片url
     */
    //场馆图片
    public final static String GYM_PHOTO = "GetGymPhotoServlet";

}
