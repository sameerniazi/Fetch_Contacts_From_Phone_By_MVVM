package com.sameer.call.sameerproject.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.sameer.call.sameerproject.R;

import com.sameer.call.sameerproject.ModelClass.Contact;
import com.sameer.call.sameerproject.databinding.ItemContactBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<Contact> contacts;
    private ItemContactBinding binding;
    private Context context;
    private int lastPosition = -1;

    private List<Contact> filtered_icontacts = new ArrayList<>();



    private OnItemClickListener mOnItemClickListener;

    /**
     * The interface On item click listener.
     */
    public interface OnItemClickListener {
        /**
         * On item click.
         *
         * @param view     the view
         * @param obj      the obj
         * @param position the position
         */
        void onItemClick(View view, Contact obj, int position);
    }

    /**
     * Sets on item click listener.
     *
     * @param mItemClickListener the m item click listener
     */
    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }


    ContactAdapter(Context context) {
        this.context = context;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
        this.filtered_icontacts = contacts;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_contact,
                viewGroup, false);
        return new ContactViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder contactViewHolder, int i) {
        contactViewHolder.onBind(filtered_icontacts.get(i));
        setAnimation(contactViewHolder.itemView, i);
    }

    @Override
    public int getItemCount() {
        return filtered_icontacts.size();
    }


    class ContactViewHolder extends RecyclerView.ViewHolder {

        private final ItemContactBinding binding;

        ContactViewHolder(ItemContactBinding itemContactBinding) {
            super(itemContactBinding.getRoot());
            this.binding = itemContactBinding;
        }

        void onBind(Contact contact) {

            binding.contactName.setText(contact.getName());
            binding.contactNumber.setText(contact.getPhoneNumber());
            binding.selectedContactLayout.setOnClickListener(view ->
                    mOnItemClickListener.onItemClick(view, contact, getAdapterPosition()));
            if (contact.getPhotoUri() != null) {
                binding.drawableTextView.setVisibility(View.GONE);
                binding.contactPhoto.setVisibility(View.VISIBLE);
                Contact.Companion.loadImage(binding.contactPhoto, contact.getPhotoUri());
            } else {
                binding.contactPhoto.setVisibility(View.GONE);
                binding.drawableTextView.setVisibility(View.VISIBLE);
                GradientDrawable gradientDrawable = (GradientDrawable) binding.drawableTextView.getBackground();
                gradientDrawable.setColor(getRandomColor());
                String serviceSubString = (contact.getName().substring(0, 1));
                binding.drawableTextView.setText(serviceSubString.toUpperCase());
            }
            binding.executePendingBindings();
        }
    }


    private void setAnimation(View viewToAnimate, int position) {

        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.item_animation_from_right);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    /**
     * @return a random color which is used a background by
     * services initial letters
     */
    private int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }


    /**
     * Gets filter.
     *
     * @return the filter
     */

}
