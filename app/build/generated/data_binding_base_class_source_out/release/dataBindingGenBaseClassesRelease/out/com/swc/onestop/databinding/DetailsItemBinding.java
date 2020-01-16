package com.swc.onestop.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class DetailsItemBinding extends ViewDataBinding {
  @NonNull
  public final TextView tvText;

  @NonNull
  public final TextView tvTitle;

  protected DetailsItemBinding(Object _bindingComponent, View _root, int _localFieldCount,
      TextView tvText, TextView tvTitle) {
    super(_bindingComponent, _root, _localFieldCount);
    this.tvText = tvText;
    this.tvTitle = tvTitle;
  }

  @NonNull
  public static DetailsItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.details_item, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static DetailsItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<DetailsItemBinding>inflateInternal(inflater, com.swc.onestop.R.layout.details_item, root, attachToRoot, component);
  }

  @NonNull
  public static DetailsItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.details_item, null, false, component)
   */
  @NonNull
  @Deprecated
  public static DetailsItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<DetailsItemBinding>inflateInternal(inflater, com.swc.onestop.R.layout.details_item, null, false, component);
  }

  public static DetailsItemBinding bind(@NonNull View view) {
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
  public static DetailsItemBinding bind(@NonNull View view, @Nullable Object component) {
    return (DetailsItemBinding)bind(component, view, com.swc.onestop.R.layout.details_item);
  }
}
