package com.talhaatif.financeapk
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.talhaatif.financeapk.databinding.FragmentProfileBinding
import com.talhaatif.financeapk.dialog.CustomProgressDialog
import com.talhaatif.financeapk.viewmodel.ProfileViewModel

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var profileViewModel: ProfileViewModel
    private var imgChange = false
    private lateinit var progressDialog: CustomProgressDialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressDialog = CustomProgressDialog(requireContext())
        progressDialog.setMessage("Perfil Actualizado...")

        profileViewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[ProfileViewModel::class.java]


        binding.logout.setOnClickListener {
            profileViewModel.logout()
            Toast.makeText(requireContext(), "Sesión Cerrada", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            // to clear previous all activities from the stack
            intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            requireActivity().finish()
        }


        profileViewModel.fetchUserProfile()
        observeViewModel()

        binding.update.setOnClickListener {
            val name = binding.name.text.toString()
            var currency = binding.currencySelector.text.toString()

            currency = if(currency.equals("PKR",true)){
                "Rs"
            } else if(currency.equals("INR",true)){
                "₹"
            } else if(currency.equals("EUR",true)) {
                "€"
            } else if(currency.equals("COP",true)){
                "$"
            } else{
                "$"
            }

            val bitmap = null

            progressDialog.setMessage("Actualizando Perfil..")
            progressDialog.setCancelable(false) // Evitar que el usuario cierre el cuadro de diálogo
            progressDialog.show()


            profileViewModel.updateUserProfile(name, currency, bitmap, imgChange)
        }
    }

    private fun observeViewModel() {
        profileViewModel.profileData.observe(viewLifecycleOwner, Observer { data ->
            binding.name.setText(data["name"])
            val currencies = listOf("USD", "EUR", "PKR", "INR", "COP").toMutableList()
            binding.currencySelector.setAdapter(
                ArrayAdapter(requireContext(), R.layout.dropdown_menu_popup_item, currencies))

            var selectedCurrency = profileViewModel.getCurrency()
            if(selectedCurrency .equals("Rs")){
                selectedCurrency  = "PKR"
            }
            else if(selectedCurrency .equals("₹")){
                selectedCurrency  = "INR"
            }
            else if(selectedCurrency .equals("€")){
                selectedCurrency = "EUR"
            }
            else if(selectedCurrency .equals("$")){
                selectedCurrency  = "COP"
            }
            else {
                selectedCurrency = "USD"
            }

            binding.currencySelector.setText(selectedCurrency, false)
            
        })

        profileViewModel.updateSuccess.observe(viewLifecycleOwner, Observer { success ->
            progressDialog.dismiss()
            Toast.makeText(requireContext(), if (success) "Perfil actualizado" else "Error al actualizar", Toast.LENGTH_SHORT).show()
        })
    }
}
