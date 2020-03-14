package com.android.mymaterialdesign


import android.os.Bundle
import android.transition.TransitionInflater
import android.view.*
import androidx.core.app.SharedElementCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.mymaterialdesign.adapter.ProductAdapter
import com.android.mymaterialdesign.model.Product
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_product.*
import kotlinx.android.synthetic.main.item_product.view.*

/**
 * A simple [Fragment] subclass.
 */
//TODO 3: CREATE FRAGMENTS
class HomeFragment : Fragment(), ProductAdapter.OnProductClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnProfile.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }

        //TODO 16: setup product list
        rvProduct.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
            val productAdapter = ProductAdapter(getProductDummies(), this@HomeFragment)
            adapter = productAdapter
        }

        setExitSharedElementCallback(object:SharedElementCallback(){
            override fun onMapSharedElements(
                names: MutableList<String>?,
                sharedElements: MutableMap<String, View>?
            ) {
                val selectedViewHolder:RecyclerView.ViewHolder? = rvProduct.findViewHolderForAdapterPosition(0)
                if (selectedViewHolder?.itemView == null){
                    return
                }

                sharedElements?.put(names?.get(0).orEmpty(), selectedViewHolder.itemView.findViewById(R.id.productImage))
            }
        })

        exitTransition = TransitionInflater.from(context).inflateTransition(R.transition.transition_detail_exit)

        rvProduct.addOnLayoutChangeListener(object: View.OnLayoutChangeListener{
            override fun onLayoutChange(
                p0: View?,
                p1: Int,
                p2: Int,
                p3: Int,
                p4: Int,
                p5: Int,
                p6: Int,
                p7: Int,
                p8: Int
            ) {
                rvProduct.removeOnLayoutChangeListener(this)
                val layoutManager = rvProduct.layoutManager
                val viewAtPosition = layoutManager?.findViewByPosition(0)
                if (viewAtPosition == null || layoutManager.isViewPartiallyVisible(viewAtPosition, false, true)){
                    rvProduct.post {
                        layoutManager?.scrollToPosition(0)
                    }
                }
            }

        })
    }

    fun getProductDummies(): MutableList<Product> {
        val products = mutableListOf<Product>()
        for (i in 1..15) {
            products.add(
                Product(
                    getString(R.string.sample_image_url),
                    getString(R.string.sample_title),
                    getString(R.string.sample_description)
                )
            )
        }
        return products
    }

    //TODO 15: Create appbarmenu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.appbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onProductClicked(view: View, product: Product) {
        //TODO 20: map transition
        val extras = FragmentNavigatorExtras(view.productImage to view.productImage.transitionName)
        val data = Bundle()
        data.putParcelable(getString(R.string.label_product_detail), product)
        findNavController().navigate(R.id.action_homeFragment_to_productDetailFragment, data, null, extras)
    }


}
