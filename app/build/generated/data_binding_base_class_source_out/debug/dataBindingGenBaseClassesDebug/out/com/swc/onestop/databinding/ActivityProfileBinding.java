package com.swc.onestop.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityProfileBinding extends ViewDataBinding {
  @NonNull
  public final AppBarLayout appBar;

  @NonNull
  public final ImageView avatar;

  @NonNull
  public final FrameLayout avatarBorder;

  @NonNull
  public final ImageView headerImage;

  @NonNull
  public final FrameLayout headerInfo;

  @NonNull
  public final RecyclerView recyclerView;

  @NonNull
  public final LinearLayout texts;

  @NonNull
  public final Toolbar toolbar;

  @NonNull
  public final CollapsingToolbarLayout toolbarLayout;

  @NonNull
  public final TextView tvInfo;

  @NonNull
  public final TextView tvName;

  @NonNull
  public final TextView tvStatus;

  @NonNull
  public final TextView tvTitle;

  protected ActivityProfileBinding(Object _bindingComponent, View _root, int _localFieldCount,
      AppBarLayout appBar, ImageView avatar, FrameLayout avatarBorder, ImageView headerImage,
      FrameLayout headerInfo, RecyclerView recyclerView, LinearLayout texts, Toolbar toolbar,
      CollapsingToolbarLayout toolbarLayout, TextView tvInfo, TextView tvName, TextView tvStatus,
      TextView tvTitle) {
    super(_bindingComponent, _root, _localFieldCount);
    this.appBar = appBar;
    this.avatar = avatar;
    this.avatarBorder = avatarBorder;
    this.headerImage = headerImage;
    this.headerInfo = headerInfo;
    this.recyclerView = recyclerView;
    this.texts = texts;
    this.toolbar = toolbar;
    this.toolbarLayout = toolbarLayout;
    this.tvInfo = tvInfo;
    this.tvName = tvName;
    this.tvStatus = tvStatus;
    this.tvTitle = tvTitle;
  }

  @NonNull
  public static ActivityProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_profile, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityProfileBinding>inflateInternal(inflater, com.swc.onestop.R.layout.activity_profile, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityProfileBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_profile, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityProfileBinding>inflateInternal(inflater, com.swc.onestop.R.layout.activity_profile, null, false, component);
  }

  public static ActivityProfileBinding bind(@NonNull View view) {
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
  public static ActivityProfileBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivityProfileBinding)bind(component, view, com.swc.onestop.R.layout.activity_profile);
  }
}
