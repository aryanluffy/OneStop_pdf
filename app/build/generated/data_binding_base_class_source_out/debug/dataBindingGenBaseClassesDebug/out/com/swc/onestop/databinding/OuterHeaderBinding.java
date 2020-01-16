package com.swc.onestop.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class OuterHeaderBinding extends ViewDataBinding {
  @NonNull
  public final FrameLayout headerFooter;

  @NonNull
  public final RelativeLayout headerMiddle;

  @NonNull
  public final TextView headerText1;

  @NonNull
  public final TextView tvInfo;

  protected OuterHeaderBinding(Object _bindingComponent, View _root, int _localFieldCount,
      FrameLayout headerFooter, RelativeLayout headerMiddle, TextView headerText1,
      TextView tvInfo) {
    super(_bindingComponent, _root, _localFieldCount);
    this.headerFooter = headerFooter;
    this.headerMiddle = headerMiddle;
    this.headerText1 = headerText1;
    this.tvInfo = tvInfo;
  }

  @NonNull
  public static OuterHeaderBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.outer_header, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static OuterHeaderBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<OuterHeaderBinding>inflateInternal(inflater, com.swc.onestop.R.layout.outer_header, root, attachToRoot, component);
  }

  @NonNull
  public static OuterHeaderBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.outer_header, null, false, component)
   */
  @NonNull
  @Deprecated
  public static OuterHeaderBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<OuterHeaderBinding>inflateInternal(inflater, com.swc.onestop.R.layout.outer_header, null, false, component);
  }

  public static OuterHeaderBinding bind(@NonNull View view) {
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
  public static OuterHeaderBinding bind(@NonNull View view, @Nullable Object component) {
    return (OuterHeaderBinding)bind(component, view, com.swc.onestop.R.layout.outer_header);
  }
}
