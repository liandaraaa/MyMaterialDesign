package com.android.mymaterialdesign


import android.os.Bundle
import android.text.Editable
import android.view.*
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    //TODO 13: setup logic to login
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnNext.setOnClickListener {
            if (isPasswordValid(tilPassword.editText?.text)){
                tilPassword.error = "Password minimal 6 character"
            }else{
                tilPassword.error = null
            }
        }

        tilPassword.setOnKeyListener { view, i, keyEvent ->
            if(isPasswordValid(tilPassword.editText?.text)){
                tilPassword.error = null
            }
            false
        }
    }

    private fun isPasswordValid(text:Editable?):Boolean{
        return text != null && text.length >= 6
    }


}
