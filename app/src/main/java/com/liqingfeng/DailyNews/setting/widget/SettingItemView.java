package com.liqingfeng.DailyNews.setting.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.liqingfeng.DailyNews.R;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/4/4 23:02
 * @DESC: 设置界面自定义View
 * @VERSION: V1.0
 */
public class SettingItemView extends FrameLayout {
    private View contentView;
    private TextView tvTitle;
    private TextView tvContent;
    private SwitchCompat switchCompat;

    private boolean isShowTitle;
    private boolean isShowContent;
    private boolean isShowSwitch;
    private boolean isSwitched;
    private String title;
    private String content;
    private OnClickListener mOnClickListener;
    private OnCheckedChangeListener mOnCheckedChangeListener;

    public SettingItemView(@NonNull Context context) {
        this(context, null);
    }

    public SettingItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SettingItemView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int
            defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUi();
        initListener();
        getAttrsValue(context, attrs, defStyleAttr);

    }

    //初始化布局
    private void initUi() {
        contentView = LayoutInflater.from(getContext()).inflate(R.layout.layout_setting_item,
                this, true);
        tvTitle = (TextView) contentView.findViewById(R.id.tv_title);
        tvContent = (TextView) contentView.findViewById(R.id.tv_content);
        switchCompat = (SwitchCompat) contentView.findViewById(R.id.switch_compat);
    }

    //初始化监听器
    private void initListener() {
        contentView.findViewById(R.id.ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchCompat.getVisibility() == View.VISIBLE) {
                    switchCompat.setChecked(!switchCompat.isChecked());
                }
                if (mOnClickListener != null) {
                    mOnClickListener.onClick();
                }

            }
        });
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mOnCheckedChangeListener != null)
                    mOnCheckedChangeListener.onCheckedChanged(isChecked);
            }
        });
    }

    //获取属性值
    private void getAttrsValue(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable
                .SettingItemView, defStyleAttr, 0);
        isShowTitle = typedArray.getBoolean(R.styleable.SettingItemView_isShowTitle, true);
        isShowContent = typedArray.getBoolean(R.styleable.SettingItemView_isShowContent, true);
        isShowSwitch = typedArray.getBoolean(R.styleable.SettingItemView_isShowSwitch, true);
        isSwitched = typedArray.getBoolean(R.styleable.SettingItemView_isSwitched, false);
        title = typedArray.getString(R.styleable.SettingItemView_settingTitle);
        content = typedArray.getString(R.styleable.SettingItemView_settingContent);
        typedArray.recycle();
        initAttrs();
    }

    //初始化属性给指定的控件
    private void initAttrs() {
        if (!isShowTitle) {
            tvTitle.setVisibility(View.GONE);
        }
        if (!isShowContent) {
            tvContent.setVisibility(View.GONE);
        }
        if (!isShowSwitch) {
            switchCompat.setVisibility(View.GONE);
        }
        switchCompat.setChecked(isSwitched);
        if (title != null)
            tvTitle.setText(title);
        if (content != null)
            tvContent.setText(content);
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        if (title != null)
            tvTitle.setText(title);
    }

    /**
     * 设置内容
     *
     * @param content 设置内容
     */
    public void setContent(String content) {
        if (content != null)
            tvContent.setText(content);
    }

    /**
     * 设置是否被选中
     *
     * @param isChecked
     */
    public void setChecked(boolean isChecked) {
        switchCompat.setChecked(isChecked);
    }


    /**
     * 判断是否被选中
     *
     * @return 返回选中的状态
     */
    public boolean isChecked() {
        return switchCompat.isChecked();
    }

    /**
     * 设置状态监听器
     *
     * @param listener
     */
    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        this.mOnCheckedChangeListener = listener;
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(boolean isChecked);
    }

    /**
     * 设置点击监听器
     *
     * @param onClickListener
     */
    public void setOnClickListener(OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick();
    }

    //保存自定义View的状态
    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.childrenStates = new SparseArray();
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).saveHierarchyState(ss.childrenStates);
        }
        return ss;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).restoreHierarchyState(ss.childrenStates);
        }
    }

    @Override
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        dispatchFreezeSelfOnly(container);
    }

    @Override
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        dispatchThawSelfOnly(container);
    }

    static class SavedState extends BaseSavedState {
        SparseArray childrenStates;

        public SavedState(Parcelable superState) {
            super(superState);
        }

        public SavedState(Parcel source, ClassLoader loader) {
            super(source);
            childrenStates = source.readSparseArray(loader);
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeSparseArray(childrenStates);
        }

        public static final ClassLoaderCreator<SavedState> creator = new ClassLoaderCreator<SavedState>() {
            @Override
            public SavedState createFromParcel(Parcel source, ClassLoader loader) {
                return new SavedState(source, loader);
            }

            @Override
            public SavedState createFromParcel(Parcel source) {
                return createFromParcel(null);
            }

            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }
}
