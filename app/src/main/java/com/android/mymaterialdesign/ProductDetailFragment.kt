package com.android.mymaterialdesign


import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.SharedElementCallback
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.android.mymaterialdesign.model.Product
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_product_detail.*

/**
 * A simple [Fragment] subclass.
 */
class ProductDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val transition = TransitionInflater.from(context).inflateTransition(R.transition.transition_list_to_detail)
        sharedElementEnterTransition = transition

        setEnterSharedElementCallback(object :SharedElementCallback(){
            override fun onMapSharedElements(
                names: MutableList<String>?,
                sharedElements: MutableMap<String, View>?
            ) {
                sharedElements?.put(names?.get(0).orEmpty(), view.findViewById(R.id.imgProduct))
            }
        })

        val data:Product? = arguments?.getParcelable(getString(R.string.label_product_detail))
        context?.let {
            Glide.with(it)
                .load(data?.imageUrl)
                .into(imgProduct)
        }
    }


}
