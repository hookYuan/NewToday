package lovcreate.com.newtoday.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import lovcreate.com.newtoday.R;
import lovcreate.com.newtoday.bean.NewsBean;
import lovcreate.com.newtoday.util.DateUtils;

/**
 * 作者：yuanYe创建于2016/9/21
 * QQ：962851730
 * 首页新闻列表Adapter
 */

public class NewsAdapter extends BaseAdapter {

    List<NewsBean> data;

    public NewsAdapter(List<NewsBean> data) {
        this.data = data;
        Log.i("yuanye", "data is Empty = " + (data == null));
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getType()-1;
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        NewsBean bean = data.get(position);
        if (convertView == null) {
            holder = new ViewHolder();
            switch (bean.getType()) {
                case 1:
                    convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_type_1, null);
                    break;
                case 2:
                    convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_type_2, null);
                    break;
                case 3:
                    convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_type_3, null);
                    break;
                case 4:
                    convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_type_4, null);
                    break;
            }
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.iv_organization = (ImageView) convertView.findViewById(R.id.iv_organization);
            holder.tv_mechanism = (TextView) convertView.findViewById(R.id.tv_mechanism);
            holder.tv_comment = (TextView) convertView.findViewById(R.id.tv_comment);
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            holder.tv_is_hot = (TextView) convertView.findViewById(R.id.tv_is_hot);
            holder.iv_content1 = (ImageView)convertView.findViewById(R.id.iv_content_01);
            holder.iv_content2 = (ImageView)convertView.findViewById(R.id.iv_content_02);
            holder.iv_content3 = (ImageView)convertView.findViewById(R.id.iv_content_03);
            convertView.setTag(holder); //切记设置Tag，否则不能重用
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_title.setText(bean.getTitle());
        holder.tv_mechanism.setText(bean.getMechanism_name());
        holder.tv_comment.setText(bean.getComment_count() + "条评论");
        holder.tv_time.setText(DateUtils.pareTime(bean.getComment_time()));
        switch (bean.getType()){
            case 2:
                Glide.with(parent.getContext()).load(bean.getUrl_content_01()).into(holder.iv_content1);
                Glide.with(parent.getContext()).load(bean.getUrl_content_02()).into(holder.iv_content2);
                Glide.with(parent.getContext()).load(bean.getUrl_content_03()).into(holder.iv_content3);
                break;
            case 3:
                Glide.with(parent.getContext()).load(bean.getUrl_content_01()).into(holder.iv_content1);
                break;
            case 4:
                Glide.with(parent.getContext()).load(bean.getUrl_content_01()).into(holder.iv_content1);
                break;
            default:
                break;
        }
        return convertView;
    }

    class ViewHolder {
        TextView tv_title; //标题
        ImageView iv_content1, iv_content2, iv_content3; //新闻图片
        ImageView iv_organization;//发布机构头像
        TextView tv_mechanism;//发布机构名称
        TextView tv_comment;//评论数量
        TextView tv_time;//发布时间
        TextView tv_is_hot;//是否为热点
    }
}





























