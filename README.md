<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <LinearLayout
        android:id="@+id/phone_layout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="24dp"
        android:gravity="center_horizontal"
        android:orientation="vertical">

        <EditText
            android:id="@+id/phone_number_edit_text"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginStart="16dp"
            android:layout_marginEnd="16dp"
            android:hint="Phone Number"
            android:inputType="phone"
            android:maxLines="1"
            android:textColor="@android:color/black"
            android:textColorHint="@color/colorSecondaryText"
            android:textSize="16sp" />

        <View
            android:layout_width="match_parent"
            android:layout_height="1dp"
            android:background="@color/colorDivider" />

    </LinearLayout>
