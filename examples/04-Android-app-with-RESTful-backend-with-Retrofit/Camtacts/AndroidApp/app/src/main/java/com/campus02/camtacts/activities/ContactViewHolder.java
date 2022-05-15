package com.campus02.camtacts.activities;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.campus02.camtacts.R;

public class ContactViewHolder extends RecyclerView.ViewHolder {

  TextView fullName;
  TextView phoneNumber;

  public ContactViewHolder(@NonNull View itemView) {
    super(itemView);

    fullName = itemView.findViewById(R.id.tvFullName);
    phoneNumber = itemView.findViewById(R.id.tvPhoneNumber);
  }
}
