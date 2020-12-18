package santosh.com.openweathertask.ui.main.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import santosh.com.openweathertask.R

class LandingActivity : AppCompatActivity() {
    lateinit var step1btn: Button
    lateinit var step2btn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)
        step1btn = findViewById(R.id.step1)
        step2btn = findViewById(R.id.step2)
        step1btn.setOnClickListener {
            val intent = Intent(this, CurrentTemparatureDetailsActivity::class.java)
            startActivity(intent)
        }
        step2btn.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this@LandingActivity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) !==
                PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this@LandingActivity,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                ) {
                    ActivityCompat.requestPermissions(
                        this@LandingActivity,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1
                    )
                } else {
                    ActivityCompat.requestPermissions(
                        this@LandingActivity,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1
                    )
                }
            } else {
                gotoCurrentLocationTemparatureDetailsActivity()
            }

        }

    }

    fun gotoCurrentLocationTemparatureDetailsActivity() {
        val intent = Intent(this, CurrentLocationTemparatureDetailsActivity::class.java)
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    if ((ContextCompat.checkSelfPermission(
                            this@LandingActivity,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) ===
                                PackageManager.PERMISSION_GRANTED)
                    ) {
                        gotoCurrentLocationTemparatureDetailsActivity()
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }
}