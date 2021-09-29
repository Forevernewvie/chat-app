package com.jaebin.what.ui

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.jaebin.what.ConstantsVal.SHAREDPREFERENCES_IMG_KEY
import com.jaebin.what.ConstantsVal.SHAREDPREFERENCES_KEY
import com.jaebin.what.R
import com.jaebin.what.preferenceUtil.SPF
import com.jaebin.what.databinding.FragmentCreateProfileBinding
import com.jaebin.what.utils.Utils.intentUtils
import com.jaebin.what.utils.Utils.bitMapUtil

class CreateProfileFragMent : Fragment() {
    private lateinit var careProfileBinding :FragmentCreateProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        careProfileBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_create_profile,container,false)
        return careProfileBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        careProfileBinding.btnProfileOK.setOnClickListener {
            getNickName()
            it.findNavController().navigate(R.id.action_createProfile_to_fragment_home)
        }

        careProfileBinding.profileImg.setOnClickListener {
            filterActivityLauncher.launch(intentUtils.getSAF())
        }
    }

    private fun getNickName(){
        val data = careProfileBinding.messengerTextView.text.toString()
        SPF.prefs.setString(SHAREDPREFERENCES_KEY,data)
    }


    private val filterActivityLauncher: ActivityResultLauncher<Intent> =

        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val context = requireContext()
            if(it.resultCode == AppCompatActivity.RESULT_OK && it.data !=null) {
                var currentImageUri = it.data?.data
                val tempBitmap:Bitmap

                try {
                    currentImageUri?.let {
                        if(Build.VERSION.SDK_INT < 28) {
                            tempBitmap = MediaStore.Images.Media.getBitmap(
                                context.contentResolver,
                                currentImageUri
                            )
                            careProfileBinding.profileImg.setImageBitmap(tempBitmap)
                            careProfileBinding.profileImg.scaleType = ImageView.ScaleType.CENTER_CROP
                            SPF.prefs.setString(SHAREDPREFERENCES_IMG_KEY, bitMapUtil.bitMapToString(tempBitmap))
                        }
                        else {
                            val source = ImageDecoder.createSource(context.contentResolver, currentImageUri)
                            tempBitmap = ImageDecoder.decodeBitmap(source)
                            careProfileBinding.profileImg.setImageBitmap(tempBitmap)
                            careProfileBinding.profileImg.scaleType = ImageView.ScaleType.CENTER_CROP
                            SPF.prefs.setString(SHAREDPREFERENCES_IMG_KEY,bitMapUtil.bitMapToString(tempBitmap))
                        }
                    }


                }catch(e:Exception) {
                    e.printStackTrace()
                }
            } else if(it.resultCode == AppCompatActivity.RESULT_CANCELED){
                Toast.makeText(context, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }else{
                Log.d("ActivityResult","something wrong")
            }
        }





}