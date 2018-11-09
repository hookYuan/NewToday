package lovcreate.com.newtoday.spannable;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import lovcreate.com.newtoday.R;
import lovcreate.com.newtoday.application.BaseApplication;

/**
 * @author yiw
 * @Description:
 * @date 16/1/2 16:32
 */
public abstract class SpannableClickable extends ClickableSpan implements View.OnClickListener {

    private int DEFAULT_COLOR_ID = R.color.textBlackNormal;
    /**
     * text颜色
     */
    private int textColor ;

    public SpannableClickable() {
        this.textColor = BaseApplication.getApplication().getResources().getColor(DEFAULT_COLOR_ID);
    }

    public SpannableClickable(int textColor){
        this.textColor = textColor;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);

        ds.setColor(textColor);
        ds.setUnderlineText(false);
        ds.clearShadowLayer();
    }
}
