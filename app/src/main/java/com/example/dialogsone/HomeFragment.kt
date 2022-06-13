package com.example.dialogsone

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.dialogsone.Adapter.SnackbarAdapter
import com.example.dialogsone.CLASS.SClass
import com.example.dialogsone.databinding.FragmentHomeBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    lateinit var snackbarAdapter: SnackbarAdapter
    lateinit var snackbarList: ArrayList<SClass>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        return binding.root

    }

    private fun loadData() {

        snackbarList = ArrayList()

        snackbarList.add(SClass(R.drawable.ic_accessibility, "Artikov"))
        snackbarList.add(SClass(R.drawable.ic_launcher_background, "Silence"))
        snackbarList.add(SClass(R.drawable.ic_accessibility, "Android"))
        snackbarList.add(SClass(R.drawable.ic_launcher_foreground, "Kotlin"))
        snackbarList.add(SClass(R.drawable.ic_accessibility, "hp"))
        snackbarList.add(SClass(R.drawable.ic_accessibility, "Na na na"))


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        loadData()

        snackbarAdapter =
            SnackbarAdapter(snackbarList, object : SnackbarAdapter.OnMyItemClickListener {
                override fun onDeleteItem(snackbarClass: SClass, position: Int, itemView: View) {
                    snackbarList.remove(snackbarClass)
                    snackbarAdapter.notifyItemRemoved(position)
                    snackbarAdapter.notifyItemRangeChanged(position, snackbarList.size)


                    val snackbar = Snackbar.make(
                        itemView,
                        "Foydalanuvchini ortqaga qaytarish",
                        Snackbar.LENGTH_SHORT
                    )

                    snackbar.setAction("Undo") {
                        snackbarList.add(position, snackbarClass)
                        snackbarAdapter.notifyItemInserted(position)
                        snackbarAdapter.notifyItemRangeChanged(position, snackbarList.size)
                    }

                    snackbar.show()

                }

            })

        binding.rv.adapter = snackbarAdapter

        var alertArray = arrayOf("Silence", "Android", "Kotlin", "hp")

        binding.alertDialog.setOnClickListener {

            val alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle("Title")
            //alertDialog.setMessage("Message") //Shunchaki message chiqaradi
            //alertDialog.setCancelable(false) //chetki qismlar bosilishini taqiqlaydi

            alertDialog.setPositiveButton(
                "Positive"
            ) { p0, p1 ->

                Toast.makeText(context, "Positive", Toast.LENGTH_SHORT).show()

            }

            alertDialog.setNegativeButton(
                "Negative"
            ) { p0, p1 ->

                Toast.makeText(context, "Negative", Toast.LENGTH_SHORT).show()

            }

            alertDialog.setNeutralButton("Neutral") { p0, p1 ->

                Toast.makeText(context, "Neutral", Toast.LENGTH_SHORT).show()

            }


/*            alertDialog.setMultiChoiceItems(
                alertArray, BooleanArray(alertArray.size)
            ) { p0, p1, p2 ->
                Toast.makeText(context, "${alertArray[p1]}", Toast.LENGTH_SHORT).show()
            }*/    // barchasini tanlash imkoniyati

            alertDialog.setSingleChoiceItems(
                alertArray, -1
            ) { p0, p1 ->

                Toast.makeText(context, "${alertArray[p1]}", Toast.LENGTH_SHORT).show()
                p0.dismiss() // Dialogni yopadi
            }

            alertDialog.show()

        }

        binding.customDialog.setOnClickListener {

            val customDialog = AlertDialog.Builder(
                context,
                R.style.TranslucentDialog
            ) //style orqa qismini shaffof qoldiradi

            val customDialogCreate = customDialog.create()

            val customView = layoutInflater.inflate(R.layout.custom_dialog_view, null, false)
            customDialogCreate.setView(customView)


            customView.findViewById<Button>(R.id.customShow).setOnClickListener {

                val username = customView.findViewById<EditText>(R.id.usernamaItem).text
                val password = customView.findViewById<EditText>(R.id.passwordItem).text

                Toast.makeText(context, "$username \n $password", Toast.LENGTH_SHORT).show()
                customDialogCreate.dismiss()

            }


            customDialogCreate.show()

        }

        binding.datePickerDialog.setOnClickListener {

            val datePickerDialog = DatePickerDialog(context!!)

            datePickerDialog.setOnDateSetListener { datePicker, i, i2, i3 ->

                Toast.makeText(context, "$i3.${i2 + 1}.$i", Toast.LENGTH_SHORT).show()

            }

            datePickerDialog.show()

        }

        binding.timePickerDialog.setOnClickListener {

            val timePickerDialog = TimePickerDialog(
                context,
                { p0, p1, p2 ->

                    Toast.makeText(context, "$p1 $p2", Toast.LENGTH_SHORT).show()

                }, 24, 60, true
            )

            timePickerDialog.show()


        }

        binding.bottomSheetDialog.setOnClickListener {

            val bottomSheetDialog = BottomSheetDialog(context!!)
            val inflate = layoutInflater.inflate(R.layout.custom_bottom_sheet_dialog, null, false)
            bottomSheetDialog.setContentView(inflate)

            inflate.findViewById<LinearLayout>(R.id.oneId).setOnClickListener {

                Toast.makeText(context, "First", Toast.LENGTH_SHORT).show()

            }

            inflate.findViewById<LinearLayout>(R.id.twoId).setOnClickListener {

                Toast.makeText(context, "Second", Toast.LENGTH_SHORT).show()

            }

            inflate.findViewById<LinearLayout>(R.id.threeId).setOnClickListener {

                Toast.makeText(context, "Third", Toast.LENGTH_SHORT).show()

            }

            bottomSheetDialog.show()

        }

        binding.snackbarDialog.setOnClickListener {

            val snackbar =
                Snackbar.make(it, "Foydalanuvchini ortga qaytarish", Snackbar.LENGTH_SHORT)

            snackbar.setAction(
                "Undo"
            ) {


            }

            snackbar.show()

        }


    }


}