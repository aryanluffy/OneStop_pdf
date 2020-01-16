package com.swc.onestop;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.swc.onestop.databinding.ActivityProfileBindingImpl;
import com.swc.onestop.databinding.DetailsItemBindingImpl;
import com.swc.onestop.databinding.InnerItemBindingImpl;
import com.swc.onestop.databinding.OuterHeaderBindingImpl;
import com.swc.onestop.databinding.ProfileItemBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYPROFILE = 1;

  private static final int LAYOUT_DETAILSITEM = 2;

  private static final int LAYOUT_INNERITEM = 3;

  private static final int LAYOUT_OUTERHEADER = 4;

  private static final int LAYOUT_PROFILEITEM = 5;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(5);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.swc.onestop.R.layout.activity_profile, LAYOUT_ACTIVITYPROFILE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.swc.onestop.R.layout.details_item, LAYOUT_DETAILSITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.swc.onestop.R.layout.inner_item, LAYOUT_INNERITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.swc.onestop.R.layout.outer_header, LAYOUT_OUTERHEADER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.swc.onestop.R.layout.profile_item, LAYOUT_PROFILEITEM);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYPROFILE: {
          if ("layout/activity_profile_0".equals(tag)) {
            return new ActivityProfileBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_profile is invalid. Received: " + tag);
        }
        case  LAYOUT_DETAILSITEM: {
          if ("layout/details_item_0".equals(tag)) {
            return new DetailsItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for details_item is invalid. Received: " + tag);
        }
        case  LAYOUT_INNERITEM: {
          if ("layout/inner_item_0".equals(tag)) {
            return new InnerItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for inner_item is invalid. Received: " + tag);
        }
        case  LAYOUT_OUTERHEADER: {
          if ("layout/outer_header_0".equals(tag)) {
            return new OuterHeaderBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for outer_header is invalid. Received: " + tag);
        }
        case  LAYOUT_PROFILEITEM: {
          if ("layout/profile_item_0".equals(tag)) {
            return new ProfileItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for profile_item is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(2);

    static {
      sKeys.put(0, "_all");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(5);

    static {
      sKeys.put("layout/activity_profile_0", com.swc.onestop.R.layout.activity_profile);
      sKeys.put("layout/details_item_0", com.swc.onestop.R.layout.details_item);
      sKeys.put("layout/inner_item_0", com.swc.onestop.R.layout.inner_item);
      sKeys.put("layout/outer_header_0", com.swc.onestop.R.layout.outer_header);
      sKeys.put("layout/profile_item_0", com.swc.onestop.R.layout.profile_item);
    }
  }
}
