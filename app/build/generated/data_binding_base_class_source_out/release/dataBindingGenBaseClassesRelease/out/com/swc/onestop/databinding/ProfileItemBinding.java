package com.swc.onestop.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ProfileItemBinding extends ViewDataBinding {
  @NonNull
  public final TextView freshersLinkId;

  @NonNull
  public final ImageView freshersLinkImageId;

  @NonNull
  public final View line;

  @NonNull
  public final LinearLayout linearLayout;

  @NonNull
  public final LinearLayout linearLayoutLinkId;

  @NonNull
  public final TextView tvText;

  @NonNull
  public final TextView tvTitle;

  protected ProfileItemBinding(Object _bindingComponent, View _root, int _localFieldCount,
      TextView freshersLinkId, ImageView freshersLinkImageId, View line, LinearLayout linearLayout,
      LinearLayout linearLayoutLinkId, TextView tvText, TextView tvTitle) {
    super(_bindingComponent, _root, _localFieldCount);
    this.freshersLinkId = freshersLinkId;
    this.freshersLinkImageId = freshersLinkImageId;
    this.line = line;
    this.linearLayout = linearLayout;
    this.linearLayoutLinkId = linearLayoutLinkId;
    this.tvText = tvText;
    this.tvTitle = tvTitle;
  }

  @NonNull
  public static ProfileItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.profile_item, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ProfileItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ProfileItemBinding>inflateInternal(inflater, com.swc.onestop.R.layout.profile_item, root, attachToRoot, component);
  }

  @NonNull
  public static ProfileItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.profile_item, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ProfileItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ProfileItemBinding>inflateInternal(inflater, com.swc.onestop.R.layout.profile_item, null, false, component);
  }

  public static ProfileItemBinding bind(@NonNull View view) {
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
  public static ProfileItemBinding bind(@NonNull View view, @Nullable Object component) {
    return (ProfileItemBinding)bind(component, view, com.swc.onestop.R.layout.profile_item);
  }
}
