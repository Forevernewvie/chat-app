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
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.jaebin.what.Extension
import com.jaebin.what.Extension.bitMapToString
import com.jaebin.what.Extension.getSAF
import com.jaebin.what.KeyVariable
import com.jaebin.what.KeyVariable.sharedPreferencesImgKey
import com.jaebin.what.KeyVariable.sharedPreferencesKey
import com.jaebin.what.R
import com.jaebin.what.preferenceUtil.SPF
import com.jaebin.what.databinding.FragmentCreateProfileBinding

class CreateProfile : Fragment() {
    private lateinit var binding :FragmentCreateProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateProfileBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnProfileOK.setOnClickListener {
            getNickName()
            it.findNavController().navigate(R.id.action_createProfile_to_fragment_home)
        }

        binding.profileImg.setOnClickListener {
            filterActivityLauncher.launch( getSAF())
        }
    }

    private fun getNickName(){
        val data = binding.messengerTextView.text.toString()
        SPF.prefs.setString(sharedPreferencesKey,data)
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
                            binding.profileImg.setImageBitmap(tempBitmap)
                            binding.profileImg.scaleType = ImageView.ScaleType.CENTER_CROP
                            SPF.prefs.setString(sharedPreferencesImgKey,bitMapToString(tempBitmap))
                        }
                        else {
                            val source = ImageDecoder.createSource(context.contentResolver, currentImageUri)
                            tempBitmap = ImageDecoder.decodeBitmap(source)
                            binding.profileImg.setImageBitmap(tempBitmap)
                            binding.profileImg.scaleType = ImageView.ScaleType.CENTER_CROP
                            SPF.prefs.setString(sharedPreferencesImgKey,bitMapToString(tempBitmap))
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