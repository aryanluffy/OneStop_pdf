package com.swc.onestop.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class InnerItemBinding extends ViewDataBinding {
  @NonNull
  public final ImageView avatar;

  @NonNull
  public final FrameLayout avatarBorder;

  @NonNull
  public final TextView tvAddress;

  @NonNull
  public final TextView tvHeader;

  @NonNull
  public final TextView tvName;

  protected InnerItemBinding(Object _bindingComponent, View _root, int _localFieldCount,
      ImageView avatar, FrameLayout avatarBorder, TextView tvAddress, TextView tvHeader,
      TextView tvName) {
    super(_bindingComponent, _root, _localFieldCount);
    this.avatar = avatar;
    this.avatarBorder = avatarBorder;
    this.tvAddress = tvAddress;
    this.tvHeader = tvHeader;
    this.tvName = tvName;
  }

  @NonNull
  public static InnerItemBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root,
      boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.inner_item, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static InnerItemBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root,
      boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<InnerItemBinding>inflateInternal(inflater, com.swc.onestop.R.layout.inner_item, root, attachToRoot, component);
  }

  @NonNull
  public static InnerItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.inner_item, null, false, component)
   */
  @NonNull
  @Deprecated
  public static InnerItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<InnerItemBinding>inflateInternal(inflater, com.swc.onestop.R.layout.inner_item, null, false, component);
  }

  public static InnerItemBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static InnerItemBinding bind(@NonNull View view, @Nullable Object component) {
    return (InnerItemBinding)bind(component, view, com.swc.onestop.R.layout.inner_item);
  }
}
