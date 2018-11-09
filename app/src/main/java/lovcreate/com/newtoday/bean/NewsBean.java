package lovcreate.com.newtoday.bean;

/**
 * 作者：yuanYe创建于2016/9/21
 * QQ：962851730
 */

public class NewsBean {


    /**
     * type ： 1.纯文字样式
     *         2.下方三张图样式
     *         3.右边一张图样式
     *         4.下面一张图（可能是视频）
     *
     */
    int type;
    String title;
    int url_organization; //发布的机构头像
    String mechanism_name;//发布的机构名称
    int comment_count;//评论的数量
    long comment_time; //发布的时间
    int url_content_01;//图片1
    int url_content_02;//图片2
    int url_content_03;//图片3

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    public boolean is_hot() {
        return is_hot;
    }

    public void setIs_hot(boolean is_hot) {
        this.is_hot = is_hot;
    }

    boolean is_hot;//是否为热点


    public int getUrl_content_02() {
        return url_content_02;
    }

    public void setUrl_content_02(int url_content_02) {
        this.url_content_02 = url_content_02;
    }

    public int getUrl_content_03() {
        return url_content_03;
    }

    public void setUrl_content_03(int url_content_03) {
        this.url_content_03 = url_content_03;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUrl_organization() {
        return url_organization;
    }

    public void setUrl_organization(int url_organization) {
        this.url_organization = url_organization;
    }

    public String getMechanism_name() {
        return mechanism_name;
    }

    public void setMechanism_name(String mechanism_name) {
        this.mechanism_name = mechanism_name;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public long getComment_time() {
        return comment_time;
    }

    public void setComment_time(long comment_time) {
        this.comment_time = comment_time;
    }

    public int getUrl_content_01() {
        return url_content_01;
    }

    public void setUrl_content_01(int url_content_01) {
        this.url_content_01 = url_content_01;
    }


}
