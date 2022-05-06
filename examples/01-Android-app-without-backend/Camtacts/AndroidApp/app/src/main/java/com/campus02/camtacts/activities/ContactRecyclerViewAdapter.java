package com.campus02.camtacts.activities;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.campus02.camtacts.R;
import com.campus02.camtacts.model.Contact;

import java.util.List;

public class ContactRecyclerViewAdapter extends RecyclerView.Adapter<ContactViewHolder> {

  private final List<Contact> contacts;

  public ContactRecyclerViewAdapter(List<Contact> contacts) { this.contacts = contacts; }

  @NonNull
  @Override
  public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.list_item_contact, parent, false);
    return new ContactViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ContactViewHolder holder, int position) {
    Contact contact = contacts.get(position);

    holder.fullName.setText(contact.getFullName());
    holder.phoneNumber.setText(contact.getPhoneNumber());

    holder.itemView.setTag(contact.getId());

    holder.itemView.setOnClickListener(ContactRecyclerViewAdapter::onItemViewClick);
  }

  @Override
  public int getItemCount() {
    return contacts.size();
  }

  private static void onItemViewClick(View itemView) {
    int contactId = (int) itemView.getTag();

    Intent intent = new Intent(itemView.getContext(), AddOrEditContactActivity.class);
    intent.putExtra(IntentExtras.CONTACT_ID, contactId);

    itemView.getContext().startActivity(intent);
  }
}
