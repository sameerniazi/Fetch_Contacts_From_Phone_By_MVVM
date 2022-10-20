package com.sameer.call.sameerproject.ModelClass

import android.widget.ImageView
import androidx.databinding.BaseObservable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class Contact :BaseObservable() {

    private var name: String? = null
    private var phoneNumber: String? = null
    private var photoUri: String? = null


    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }



    fun getPhoneNumber(): String? {
        return phoneNumber
    }

    fun setPhoneNumber(phoneNumber: String?) {
        this.phoneNumber = phoneNumber
    }


    fun getPhotoUri(): String? {
        return photoUri
    }

    fun setPhotoUri(photoUri: String?) {
        this.photoUri = photoUri
    }

    companion object {

    fun loadImage(view: ImageView, imageUrl: String?) {
        Glide.with(view.context)
            .load(imageUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(view)
    }
}
 }
